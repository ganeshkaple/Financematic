/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.scleroid.financematic.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.scleroid.financematic.MainActivity;
import com.scleroid.financematic.R;

public class EmailPasswordActivity extends BaseActivity implements
		View.OnClickListener {

	private static final String TAG = "EmailPassword";

	/*	private TextView mStatusTextView;
		private TextView mDetailTextView;*/
	private EditText mEmailField;
	private EditText mPasswordField;
	/*private Button memail_sign;*/
	private TextView mforgot;

	// [START declare_auth]
	private FirebaseAuth mAuth;
	// [END declare_auth]

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Views
		/*mStatusTextView = findViewById(R.id.status);
		mDetailTextView = findViewById(R.id.detail);*/
		mEmailField = findViewById(R.id.field_email);
		mPasswordField = findViewById(R.id.field_password);
		mforgot = findViewById(R.id.email_create_account_button);
		mforgot.setOnClickListener(v -> {
			Intent downloadIntent1 = new Intent(EmailPasswordActivity.this, ForgotActivity.class);
			startActivity(downloadIntent1);
		});
		/*memail_sign = findViewById(R.id.email_sign_in_button);
        memail_sign.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{

			}
		});*/

		// Buttons
		findViewById(R.id.email_sign_in_button).setOnClickListener(this);
		/*findViewById(R.id.email_create_account_button).setOnClickListener(this);*/


		/*findViewById(R.id.sign_out_button).setOnClickListener(this);*/
		/*findViewById(R.id.verify_email_button).setOnClickListener(this);*/

		// [START initialize_auth]
		mAuth = FirebaseAuth.getInstance();
		// [END initialize_auth]
	}

	/**
	 * @return layout resource id
	 */
	@Override
	public int getLayoutId() {
		return R.layout.activity_emailpassword;
	}

	// [START on_start_check_user]
	@Override
	public void onStart() {
		super.onStart();
		// Check if user is signed in (non-null) and update UI accordingly.
		FirebaseUser currentUser = mAuth.getCurrentUser();
		updateUI(currentUser);
	}
	// [END on_start_check_user]

	@Override
	public void onClick(View v) {
		int i = v.getId();
		if (i == R.id.email_sign_in_button) {
			signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());
		} /*else if (i == R.id.sign_out_button) {
			signOut();
		} else if (i == R.id.verify_email_button) {
			sendEmailVerification();
		}*/
	}

	private void signIn(String email, String password) {
		Log.d(TAG, "signIn:" + email);
		if (!validateForm()) {
			return;
		}

		showProgressDialog();

		// [START sign_in_with_email]
		mAuth.signInWithEmailAndPassword(email, password)
				.addOnCompleteListener(this, task -> {
					if (task.isSuccessful()) {
						// Sign in success, update UI with the signed-in user's information
						Log.d(TAG, "signInWithEmail:success");
						FirebaseUser user = mAuth.getCurrentUser();
						updateUI(user);
					} else {
						// If sign in fails, display a message to the user.
						Log.w(TAG, "signInWithEmail:failure", task.getException());
						Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
								Toast.LENGTH_SHORT).show();
						updateUI(null);
					}

					// [START_EXCLUDE]
				/*	if (!task.isSuccessful()) {
						mStatusTextView.setText(R.string.auth_failed);
					}*/
					hideProgressDialog();
					// [END_EXCLUDE]
				});
		// [END sign_in_with_email]
	}

	private void updateUI(FirebaseUser user) {
		hideProgressDialog();
		if (user != null) {
		/*	mStatusTextView.setText(getString(R.string.emailpassword_status_fmt,
					user.getEmail(), user.isEmailVerified()));
			mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));*/

			findViewById(R.id.email_password_buttons).setVisibility(View.GONE);
			findViewById(R.id.email_password_fields).setVisibility(View.GONE);
			Intent downloadIntent = new Intent(this, MainActivity.class);
			startActivity(downloadIntent);
			/*findViewById(R.id.signed_in_buttons).setVisibility(View.VISIBLE);*/

			/*	findViewById(R.id.verify_email_button).setEnabled(!user.isEmailVerified());*/
		} else {
			/*mStatusTextView.setText(R.string.signed_out);
			mDetailTextView.setText(null);*/

			findViewById(R.id.email_password_buttons).setVisibility(View.VISIBLE);
			findViewById(R.id.email_password_fields).setVisibility(View.VISIBLE);
			/*findViewById(R.id.signed_in_buttons).setVisibility(View.GONE);*/
		}
	}

	private boolean validateForm() {
		boolean valid = true;

		String email = mEmailField.getText().toString();
		if (TextUtils.isEmpty(email)) {
			mEmailField.setError("Required.");
			valid = false;
		} else {
			mEmailField.setError(null);
		}

		String password = mPasswordField.getText().toString();
		if (TextUtils.isEmpty(password)) {
			mPasswordField.setError("Required.");
			valid = false;
		} else {
			mPasswordField.setError(null);
		}

		return valid;
	}

	private void createAccount(String email, String password) {
		Log.d(TAG, "createAccount:" + email);
		if (!validateForm()) {
			return;
		}

		showProgressDialog();

		// [START create_user_with_email]
		mAuth.createUserWithEmailAndPassword(email, password)
				.addOnCompleteListener(this, task -> {
					if (task.isSuccessful()) {
						// Sign in success, update UI with the signed-in user's information
						Log.d(TAG, "createUserWithEmail:success");
						FirebaseUser user = mAuth.getCurrentUser();
						updateUI(user);
					} else {
						// If sign in fails, display a message to the user.
						Log.w(TAG, "createUserWithEmail:failure", task.getException());
						Toast.makeText(EmailPasswordActivity.this,
								"Authentication Fail or wrong id",
								Toast.LENGTH_SHORT).show();
						updateUI(null);
					}

					// [START_EXCLUDE]
					hideProgressDialog();
					// [END_EXCLUDE].class
				});
		// [END create_user_with_email]
	}

	/*private void signOut() {
		mAuth.signOut();
		updateUI(null);
	}*/

	private void sendEmailVerification() {
		// Disable button
		/*findViewById(R.id.verify_email_button).setEnabled(false);*/

		// Send verification email
		// [START send_email_verification]
		final FirebaseUser user = mAuth.getCurrentUser();
		user.sendEmailVerification()
				.addOnCompleteListener(this, new OnCompleteListener<Void>() {
					@Override
					public void onComplete(@NonNull Task<Void> task) {
						// [START_EXCLUDE]
						// Re-enable button
						/*findViewById(R.id.verify_email_button).setEnabled(true);*/

						if (task.isSuccessful()) {
							Toast.makeText(EmailPasswordActivity.this,
									"Verification email sent to " + user.getEmail(),
									Toast.LENGTH_SHORT).show();
						} else {
							Log.e(TAG, "sendEmailVerification", task.getException());
							Toast.makeText(EmailPasswordActivity.this,
									"Failed to send verification email.",
									Toast.LENGTH_SHORT).show();
						}
						// [END_EXCLUDE]
					}
				});
		// [END send_email_verification]
	}
}
