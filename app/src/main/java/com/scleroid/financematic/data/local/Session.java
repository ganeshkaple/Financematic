package com.scleroid.financematic.data.local;

import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Copyright (C) 2018
 *
 * @author Ganesh Kaple
 * @since 6/19/18
 */
public class Session {
	public static final String KEY_USER_TOTAL_AMOUNT = "total_amount";
	private SharedPreferences shref;
	private SharedPreferences.Editor editor;

	@Inject
	public Session(SharedPreferences shpref) {
		this.shref = shpref;
		this.editor = shref.edit();
		editor.apply();
	}

	public void updateAmount(float amount) {
		editor.putFloat(KEY_USER_TOTAL_AMOUNT, getAmount() + amount);
		editor.apply();
	}

	public float getAmount() {
		return shref.getFloat(KEY_USER_TOTAL_AMOUNT, 0);
	}
}