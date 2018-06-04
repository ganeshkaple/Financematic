package com.scleroid.financematic.data.remote;

import com.scleroid.financematic.data.local.model.Customer;
import com.scleroid.financematic.data.local.model.Expense;
import com.scleroid.financematic.data.local.model.Installment;
import com.scleroid.financematic.data.local.model.Loan;
import com.scleroid.financematic.data.local.model.TransactionModel;

import java.util.List;

import hugo.weaving.DebugLog;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RemotePostEndpoint {

	@DebugLog
	@POST("/newregister")
	Call<Customer> addCustomer(@Body Customer customer);

	@DebugLog
	@POST("/newloanusers")
	Call<Loan> addLoan(@Body Loan loan);

	@DebugLog
	@POST("/posts")
	Call<TransactionModel> addTransaction(@Body TransactionModel transaction);

	@DebugLog
	@POST("/insertmydate")
	Call<Installment> addInstallment(@Body Installment installment);

	@DebugLog
	@POST("/posts")
	Call<Expense> addExpense(@Body Expense expense);

	@DebugLog
	@POST("/posts")
	Call<Customer> addCustomer(@Body List<Customer> customer);

	@DebugLog
	@POST("/posts")
	Call<Loan> addLoan(@Body List<Loan> loan);

	@DebugLog
	@POST("/posts")
	Call<TransactionModel> addTransaction(@Body List<TransactionModel> transaction);

	@DebugLog
	@POST("/posts")
	Call<Installment> addInstallment(@Body List<Installment> installment);

	@DebugLog
	@POST("/posts")
	Call<Expense> addExpense(@Body List<Expense> expense);


	@DebugLog
	//Done
	@POST("delete/{installment_id}")
	void deleteInstallment(@Body int installment);

	@DebugLog
	void deleteCustomer(int customerId);

	@DebugLog
	void deleteExpense(int expenseId);

	@DebugLog
	void deleteLoan(int accountNo);

	@DebugLog
	void deleteTransaction(int transactionId);
}
