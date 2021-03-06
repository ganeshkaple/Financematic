package com.scleroid.financematic.data.local.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.scleroid.financematic.utils.roomconverters.DateConverter;
import com.scleroid.financematic.utils.roomconverters.MoneyConverter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Copyright (C) 2018
 *
 * @author Ganesh Kaple
 * @since 4/9/18
 */
@Entity(foreignKeys = @ForeignKey(entity = Loan.class,
		parentColumns = "accountNo",
		childColumns = "loanAcNo",
		onDelete = CASCADE),
		indices = {@Index(value = "installmentId", unique = true)})
public class Installment implements Serializable {


	@Ignore
	Loan loan;
	@Ignore
	@SerializedName("userid")
	@Expose
	private String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
	@SerializedName("installement_id")
	@PrimaryKey(autoGenerate = false)
	private int installmentId;
	@SerializedName("mydate")
	@TypeConverters(DateConverter.class)
	private Date installmentDate;
	@SerializedName("installement_amount")
	@TypeConverters(MoneyConverter.class)
	private BigDecimal expectedAmt;
	@SerializedName("loan_id")
	private int loanAcNo;
	private String delayReason;

	public Installment(final int installmentId, final Date installmentDate,
	                   final BigDecimal expectedAmt, final int loanAcNo,
	                   final String delayReason) {
		this.installmentId = installmentId;
		this.installmentDate = installmentDate;
		this.expectedAmt = expectedAmt;
		this.loanAcNo = loanAcNo;
		this.delayReason = delayReason;
	}
	@Ignore
	public Installment(final int installmentId, final Date installmentDate,
	                   final BigDecimal expectedAmt, final int loanAcNo) {
		this.installmentId = installmentId;
		this.installmentDate = installmentDate;
		this.expectedAmt = expectedAmt;
		this.loanAcNo = loanAcNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}

	@NonNull
	@Override
	public String toString() {
		return "Installment{" +
				"loan=" + loan +
				", installmentId=" + installmentId +
				", installmentDate=" + installmentDate +
				", expectedAmt=" + expectedAmt.toPlainString() +
				", loanAcNo=" + loanAcNo +
				", delayReason='" + delayReason + '\'' +
				'}';
	}

	public String getDelayReason() {
		return delayReason;
	}

	public void setDelayReason(final String delayReason) {
		this.delayReason = delayReason;
	}

	public int getInstallmentId() {
		return installmentId;
	}

	public void setInstallmentId(final int installmentId) {
		this.installmentId = installmentId;
	}

	public Date getInstallmentDate() {
		return installmentDate;
	}

	public void setInstallmentDate(final Date installmentDate) {
		this.installmentDate = installmentDate;
	}

	public BigDecimal getExpectedAmt() {
		return expectedAmt;
	}

	public void setExpectedAmt(final BigDecimal expectedAmt) {
		this.expectedAmt = expectedAmt;
	}

	public int getLoanAcNo() {
		return loanAcNo;
	}

	public void setLoanAcNo(final int loanAcNo) {
		this.loanAcNo = loanAcNo;
	}


	public Loan getLoan() {
		return loan;
	}

	public void setLoan(final Loan loan) {
		this.loan = loan;
	}
}
