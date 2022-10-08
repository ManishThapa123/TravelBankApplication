package com.github.travelbankapplication.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.api.models.entities.TravelBankAPIResponseItem
import com.github.travelbankapplication.helper.PreferenceManager
import com.github.travelbankapplication.repository.BaseRepository
import com.squareup.moshi.JsonAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpensesViewModel @Inject constructor(
    private val baseRepository: BaseRepository,
    private val preferenceManager: PreferenceManager,
    private val itemResponseAdapter: JsonAdapter<TravelBankAPIResponseItem>,
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    private val _totalAmount = MutableLiveData<String>()
    val totalAmount: LiveData<String> = _totalAmount

    private val _expensesList = MutableLiveData<List<TravelBankAPIResponseItem>?>()
    val expensesList: LiveData<List<TravelBankAPIResponseItem>?> = _expensesList

    private val _expensesItem = MutableLiveData<TravelBankAPIResponseItem?>()
    val expensesItem: LiveData<TravelBankAPIResponseItem?> = _expensesItem

    fun getExpensesList() = viewModelScope.launch {

        try {
            _loading.value = true
            baseRepository.getExpenseList().let { response ->

                if (response.isSuccessful) {
                    val items = response.body()
                    _expensesList.value = items
                    val totalAmountExpenses = items?.sumOf { it.amount ?: 0.0 }
                    _totalAmount.value = totalAmountExpenses.toString()
                }
            }
        } catch (e: Exception) {
            _errorMessage.value = e.toString()
            Log.d("Exception", e.toString())
        } finally {
            _loading.value = false
        }
    }

    fun saveExpense(expense: TravelBankAPIResponseItem) = viewModelScope.launch {
        val dataInString = itemResponseAdapter.toJson(expense)
        preferenceManager.saveExpenseData(dataInString)
    }

    fun getExpenseDetails() = viewModelScope.launch {
        val data = itemResponseAdapter.fromJson(preferenceManager.getExpenseData()!!)
        _expensesItem.value = data
    }

}