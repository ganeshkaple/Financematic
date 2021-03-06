package com.scleroid.financematic.data.local.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Copyright (C) 2018
 *
 * @author Ganesh Kaple
 * @since 4/2/18
 */
@Entity(indices = {@Index(value = "customerId", unique = true)})
public class Customer {


	@PrimaryKey(autoGenerate = false)
	@SerializedName("cid")
	@Expose
	private int customerId;
	@SerializedName("fullname")
	@Expose
	private String name;
	@SerializedName("mobile")
	@Expose
	private String mobileNumber;
	@SerializedName("address")
	@Expose
	private String address;

	@SerializedName("idproofno")
	@Expose
	private String idProofNo;
	@SerializedName("idproof")
	@Expose
	private String idProofType;
	@Ignore
	private List<Loan> loans;
	@Ignore
	@SerializedName("userid")
	@Expose
	private String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

	public Customer(int customerId, String name, String mobileNumber, String address,
	                String idProofNo, String idProofType) {
		this.customerId = customerId;
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.idProofNo = idProofNo;
		this.idProofType = idProofType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}

	public List<Loan> getLoans() {
		return loans;
	}

	public void setLoans(final List<Loan> loans) {
		this.loans = loans;
	}

	@NonNull
	@Override
	public String toString() {
		return "Customer{" +
				"customerId=" + customerId +
				", name='" + name + '\'' +
				", mobileNumber='" + mobileNumber + '\'' +
				", address='" + address + '\'' +

				", idProofNo='" + idProofNo + '\'' +
				", idProofType=" + idProofType +
				'}';
	}


	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdProofNo() {
		return idProofNo;
	}

	public void setIdProofNo(String idProofNo) {
		this.idProofNo = idProofNo;
	}

	public String getIdProofType() {
		return idProofType;
	}

	public void setIdProofType(String idProofType) {

		this.idProofType = idProofType;
	}


}
