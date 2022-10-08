package com.github.travelbankapplication.helper

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences("TravelBank", 0)
    private var spEditor: SharedPreferences.Editor = sharedPreferences.edit()

    fun saveExpenseData(data: String) {
        spEditor.putString(AppConstants.EXPENSE_DETAILS, data)
        spEditor.commit()
    }

    fun getExpenseData(): String? {
        return sharedPreferences.getString(AppConstants.EXPENSE_DETAILS, null)
    }
}