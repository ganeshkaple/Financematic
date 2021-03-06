package com.scleroid.financematic.fragments.people;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.scleroid.financematic.base.BaseViewModel;
import com.scleroid.financematic.data.local.model.Customer;
import com.scleroid.financematic.data.repo.CustomerRepo;
import com.scleroid.financematic.utils.network.Resource;
import com.scleroid.financematic.viewmodels.CustomerViewModel;

import java.util.List;

import javax.inject.Inject;

/**
 * Copyright (C) 2018
 *
 * @author Ganesh Kaple
 * @since 4/9/18
 */
public class PeopleViewModel extends BaseViewModel<Customer> implements CustomerViewModel {
	private final CustomerRepo customerRepo;
	LiveData<Resource<List<Customer>>> customers = new MutableLiveData<>();


	@Inject
	public PeopleViewModel(CustomerRepo customerRepo) {

		super();
		this.customerRepo = customerRepo;
		//	this.installmentRepo = installmentRepo;

		// installments = ;
//        Timber.d(installments.getValue().data.isEmpty() + "" );
		customers = getItemList();
		//   Timber.d(upcomingInstallments.getValue().isEmpty() + "" );
		//   setUpcomingInstallmentsTransformed(getTransformedUpcomingData());
	}

	@Override
	protected LiveData<Resource<List<Customer>>> updateItemLiveData() {
		customers = customerRepo
				.getCustomersWithLoans();

		return customers;
	}

	@Override
	protected LiveData<Resource<List<Customer>>> getItemList() {

		//TODO Everything is local currently, put it on remote later
		if (customers.getValue() == null || customers.getValue().data.isEmpty()) {
			 /*filterResults(installmentRepo
					.getLocalInstallmentsLab().getItems());*/
			customers = updateItemLiveData();
		}
		return customers;
	}
}
