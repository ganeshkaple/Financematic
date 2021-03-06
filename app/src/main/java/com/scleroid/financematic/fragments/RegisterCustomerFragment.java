package com.scleroid.financematic.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.scleroid.financematic.R;
import com.scleroid.financematic.base.BaseFragment;
import com.scleroid.financematic.base.BaseViewModel;
import com.scleroid.financematic.data.local.model.Customer;
import com.scleroid.financematic.data.local.model.IdProofType;
import com.scleroid.financematic.data.repo.CustomerRepo;
import com.scleroid.financematic.utils.CommonUtils;
import com.scleroid.financematic.utils.ui.ActivityUtils;
import com.scleroid.financematic.utils.ui.TextValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by scleroid on 2/3/18.
 * <p>
 * Created by scleroid on 2/3/18.
 */

/**
 * Copyright (C) 2018
 *
 * @author Ganesh Kaple
 * @since 2/3/18
 */

public class RegisterCustomerFragment extends BaseFragment {
	Button submitButton;
	@NonNull
	String[] selectidtype =
			{IdProofType.AADHAR, IdProofType.PAN, IdProofType.RATION_CARD, IdProofType
					.SEVEN_TWELVE_CERTIFICATE, IdProofType.VOTER_ID, IdProofType.PASSPORT,
					IdProofType.OTHER};
	@Inject
	ActivityUtils activityUtils;
	@Inject
	CustomerRepo customerRepo;
	private EditText etname, etmobile, etAddress, etIDproofno;
	private String proofType = IdProofType.AADHAR;

	public RegisterCustomerFragment() {
		// Required empty public constructor
	}

	@NonNull
	public static RegisterCustomerFragment newInstance() {
		RegisterCustomerFragment fragment = new RegisterCustomerFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		super.onCreateView(inflater, container, savedInstanceState);
		final View rootView = getRootView();

		setTitle();
		final Spinner spin = rootView.findViewById(R.id.spinneridtype);

		/*        final String text = spinnerTypeOfInstallment.getSelectedItem().toString();*/
		//Creating the ArrayAdapter instance having the filterSuggestions list
		ArrayAdapter<String> aa = new ArrayAdapter<>(getActivity().getApplicationContext(),
				android.R.layout.simple_spinner_item, selectidtype);
		aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		//Setting the ArrayAdapter data on the Spinner
		spin.setAdapter(aa);

//Intend
		submitButton = rootView.findViewById(R.id.btn_new_customer_Register1);

		etname = rootView.findViewById(R.id.coustomer_Name_EditText);
		etmobile = rootView.findViewById(R.id.Mobile_Number_EditText);
		etAddress = rootView.findViewById(R.id.Address_EditText);

		etIDproofno = rootView.findViewById(R.id.IDproofno);
		final Editable etnameText = etname.getText();
		etname.addTextChangedListener(new TextValidator(etname) {
			@Override
			public void validate(TextView textView, @NonNull String text) {

				if (!isValidEmail(text)) {
					etname.setError("Enter Valid Full name");
				}
			}
		});
		final Editable etmobileText = etmobile.getText();
		etmobile.addTextChangedListener(new TextValidator(etmobile) {
			@Override
			public void validate(TextView textView, @NonNull String text) {

				if (!isValidMobile(text)) {
					etmobile.setError("Enter Valid 10 digit no");
				}
			}
		});
		final Editable etAddressText = etAddress.getText();
		etAddress.addTextChangedListener(new TextValidator(etAddress) {
			@Override
			public void validate(TextView textView, @NonNull String text) {

				if (!isValidAddress(text)) {
					etAddress.setError("Enter Valid address");
				}
			}
		});
		spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(final AdapterView<?> parent, final View view,
			                           final int position, final long id) {
				proofType = selectidtype[position];
			}

			@Override
			public void onNothingSelected(final AdapterView<?> parent) {

			}
		});

		submitButton.setOnClickListener(v -> {
			String stretname = etnameText.toString();
			String stretmobile = etmobileText.toString();
			String stretAddress = etAddressText.toString();
			if (TextUtils.isEmpty(stretname)) {
				etname.setError("Enter Full Name");
				return;
			}
			if (TextUtils.isEmpty(stretmobile)) {
				etmobile.setError("Enter Mobile No");
				return;
			}
			if (TextUtils.isEmpty(stretAddress)) {
				etAddress.setError("Enter Address ");
				return;
			}

			Customer customer =
					new Customer(CommonUtils.getRandomInt(), stretname, stretmobile, stretAddress,
							etIDproofno.getText().toString(), proofType
					);
			saveCustomer(customer);

			Toast.makeText(getActivity().getApplicationContext(),
					"successfully created Customer info", Toast.LENGTH_LONG).show();

		});


		return rootView;
	}

	private void setTitle() {
		activityUtils.setTitle((AppCompatActivity) getActivity(), "Add New Customer");
	}

	/**
	 * @return layout resource id
	 */
	@Override
	public int getLayoutId() {
		return R.layout.fragment_register_new_customer;
	}

	/**
	 * Override so you can observe your viewModel
	 */
	@Override
	@Deprecated
	protected void subscribeToLiveData() {
		//No use here,
		//Stub
	}

	/**
	 * Override for set view model
	 *
	 * @return view model instance
	 */
	@Nullable
	@Override
	public BaseViewModel getViewModel() {
		return null;
	}

	private boolean isValidEmail(@NonNull String nameval) {
		String EMAIL_PATTERN =
				"^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$";//aplha and space ^[a-zA-Z\\s]*$
		//String EMAIL_PATTERN = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$";/
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(nameval);
		return matcher.matches();
	}

	private boolean isValidMobile(@NonNull String mobileval) {
		String EMAIL_PATTERN = "^[0-9]{10}$";//only alpha space

		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(mobileval);
		return matcher.matches();
	}

	private boolean isValidAddress(@NonNull String addressval) {
		String EMAIL_PATTERN = "^[A-Za-z0-9_.,;\\s]{1,}[\\.]{0,1}[A-Za-z0-9_.,;\\s]{0,}$";
		//String EMAIL_PATTERN = "^[a-zA-Z0-9_.-]*$";
		//only alpha space \d+[ ](?:[A-Za-z0-9.-]+[ ]?)+
		// (?:Avenue|Lane|Road|Boulevard|Drive|Street|Ave|Dr|Rd|Blvd|Ln|St)\.?

		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(addressval);
		return matcher.matches();
	}

	private void saveCustomer(@NonNull final Customer customer) {
		customerRepo.saveItem(customer).subscribe(() -> {
			// handle completion
			Timber.d("Item Saved");
			activityUtils.loadFragment(RegisterLoanFragment.newInstance(customer.getCustomerId
							()),
					getFragmentManager());
			//		Toasty.success(context, "Customers Added");
		}, throwable -> {
			// handle error
			Timber.d(throwable,
					"Items not Saved" + customer.toString() + " error is   " + throwable
							.getMessage());
			//		Toasty.error(context, "Customers No
		});
	}


}
