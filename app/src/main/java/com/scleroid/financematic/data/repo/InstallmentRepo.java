package com.scleroid.financematic.data.repo;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.scleroid.financematic.data.local.AppDatabase;
import com.scleroid.financematic.data.local.lab.LocalInstallmentsLab;
import com.scleroid.financematic.data.local.model.Installment;
import com.scleroid.financematic.data.remote.ApiResponse;
import com.scleroid.financematic.data.remote.WebService;
import com.scleroid.financematic.utils.AppExecutors;
import com.scleroid.financematic.utils.NetworkBoundResource;
import com.scleroid.financematic.utils.RateLimiter;
import com.scleroid.financematic.utils.Resource;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Copyright (C) 2018
 *
 * @author Ganesh Kaple
 * @since 4/9/18
 * <p>
 * <p>
 * <p>
 * Concrete implementation to load Installments from the data sources into a cache.
 * <p>
 * For simplicity, this implements a dumb synchronisation between locally persisted data and data
 * obtained from the server, by using the remote data source only if the local database doesn't
 * exist or is empty.
 * <p/>
 * By marking the constructor with {@code @Inject} and the class with {@code @Singleton}, Dagger
 * injects the dependencies required to create an instance of the InstallmentsRespository (if it
 * fails, it emits a compiler error). It uses {@link com.scleroid.financematic.di.RepositoryModule}
 * to do so, and the constructed instance is available in {@link com.scleroid.financematic.di.AppComponent}.
 * <p/>
 * Dagger generated code doesn't require public access to the constructor or class, and therefore,
 * to ensure the developer doesn't instantiate the class manually and bypasses Dagger, it's good
 * practice minimise the visibility of the class/constructor as much as possible.
 */
public class InstallmentRepo implements Repo<Installment> {

    private final AppDatabase db;

	public LocalInstallmentsLab getLocalInstallmentsLab() {
		return localInstallmentsLab;
	}

    private final LocalInstallmentsLab localInstallmentsLab;

    private final WebService webService;

    private final AppExecutors appExecutors;

    private RateLimiter<String> installmentListRateLimit = new RateLimiter<>(10, TimeUnit.MINUTES);

    @Inject
    InstallmentRepo(final AppDatabase db, final LocalInstallmentsLab installmentsLab,
                    final WebService webService, final AppExecutors appExecutors) {
        this.db = db;
        this.localInstallmentsLab = installmentsLab;
        this.webService = webService;
        this.appExecutors = appExecutors;
    }

    public LiveData<Resource<List<Installment>>> loadInstallmentsForLoan(int loanAcNo) {
        return new NetworkBoundResource<List<Installment>, List<Installment>>(appExecutors) {
            @Override
            protected void onFetchFailed() {
                installmentListRateLimit.reset(loanAcNo + "");
            }

            @Override
            protected void saveCallResult(@NonNull List<Installment> item) {
                localInstallmentsLab.addItems(item);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<Installment>>> createCall() {
                return webService.getInstallmentsForLoan(loanAcNo);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Installment> data) {
                return data == null || data.isEmpty() || installmentListRateLimit.shouldFetch(loanAcNo + "");
            }

            @NonNull
            @Override
            protected LiveData<List<Installment>> loadFromDb() {
                return localInstallmentsLab.getItemsForLoan(loanAcNo);
            }


        }.asLiveData();
    }


	@Override
	public LiveData<Resource<List<Installment>>> loadItems() {
        return new NetworkBoundResource<List<Installment>, List<Installment>>(appExecutors) {
            String key = Math.random() + "";

            @Override
            protected void saveCallResult(@NonNull List<Installment> item) {
                localInstallmentsLab.addItems(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Installment> data) {
                return data == null || data.isEmpty() || installmentListRateLimit.shouldFetch(key + "");
            }

            @NonNull
            @Override
            protected LiveData<List<Installment>> loadFromDb() {
                return localInstallmentsLab.getItems();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<Installment>>> createCall() {
                return webService.getInstallments();
            }

            @Override
            protected void onFetchFailed() {
                installmentListRateLimit.reset(key + "");
            }
        }.asLiveData();
    }

	@Override
	public LiveData<Resource<Installment>> loadItem(final int installmentNo) {
        return new NetworkBoundResource<Installment, Installment>(appExecutors) {
            @Override
            protected void saveCallResult(@NonNull Installment item) {
                localInstallmentsLab.saveItem(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable Installment data) {
                return data == null;//TODO Why this ?
            }

            @NonNull
            @Override
            protected LiveData<Installment> loadFromDb() {
                return localInstallmentsLab.getItem(installmentNo);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Installment>> createCall() {
                return webService.getInstallment(installmentNo);
            }
        }.asLiveData();
    }

    @Override
    public Completable saveItems(final List<Installment> items) {
        return localInstallmentsLab.addItems(items);
    }

    @Override
    public Single<Installment> saveItem(final Installment installment) {
        return localInstallmentsLab.saveItem(installment);
    }


}