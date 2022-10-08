package com.github.travelbankapplication.ui.ExpenseDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.github.api.models.entities.TravelBankAPIResponseItem
import com.github.travelbankapplication.databinding.FragmentExpenseDetailsBinding
import com.github.travelbankapplication.vm.ExpensesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExpenseDetailsFragment : Fragment() {
    private lateinit var binding: FragmentExpenseDetailsBinding
    private val viewModel: ExpensesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPreferenceValues()
    }

    private fun getPreferenceValues() {
        viewModel.getExpenseDetails()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentExpenseDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBindingValues()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.btnCross.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun setBindingValues() {

        viewModel.expensesItem.observe({lifecycle}){ expenseDetail ->
            if (expenseDetail != null){
                binding.apply {
                    merchantTitle.text = expenseDetail.expenseVenueTitle
                    expenseAmount.text = "$${expenseDetail.amount}"
                    currency.text = expenseDetail.currencyCode
                    date.text = expenseDetail.date
                    category.text = expenseDetail.tripBudgetCategory

                }
            }

        }

    }


}