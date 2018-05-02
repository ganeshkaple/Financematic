package com.scleroid.financematic.data.remote.lab;

import com.scleroid.financematic.data.local.model.Loan;
import com.scleroid.financematic.data.remote.RemoteDataSource;
import com.scleroid.financematic.data.remote.services.jobs.SyncLoanJob;
import com.scleroid.financematic.data.remote.services.jobs.utils.JobManagerFactory;

import io.reactivex.Completable;

/**
 * Copyright (C) 2018
 *
 * @author Ganesh Kaple
 * @since 4/6/18
 */
public class RemoteLoanLab implements RemoteDataSource<Loan> {
	@Override
	public Completable sync(final Loan loan) {
		return Completable.fromAction(() ->
				JobManagerFactory.getJobManager()
						.addJobInBackground(new SyncLoanJob<>(loan)));
	}
}
