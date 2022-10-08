package com.github.travelbankapplication.ui.ExpenseList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.api.models.entities.TravelBankAPIResponseItem
import com.github.travelbankapplication.databinding.FragmentExpenseListBinding
import com.github.travelbankapplication.helper.toast
import com.github.travelbankapplication.vm.ExpensesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExpenseListFragment : Fragment() {
    private lateinit var binding: FragmentExpenseListBinding
    private val viewModel: ExpensesViewModel by viewModels()
    private lateinit var expenseListAdapter: ExpenseListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentExpenseListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setAdapterView()
        getExpenseLists()
    }

    private fun getExpenseLists() {
        viewModel.getExpensesList()
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getExpensesList()
        }
    }

    private fun setAdapter() {
        expenseListAdapter = ExpenseListAdapter {
            openExpenseDetails(it)
        }
    }

    private fun openExpenseDetails(expense: TravelBankAPIResponseItem) {
        viewModel.saveExpense(expense)
    val action = ExpenseListFragmentDirections.actionExpenseListFragmentToExpenseDetailsFragment()
        findNavController().navigate(action)
    }

    private fun observeViewModel() {
        viewModel.apply {
            expensesList.observe({ lifecycle }) { expenseListItems ->
                expenseListAdapter.submitList(expenseListItems)
            }

            loading.observe({ lifecycle }) {
                binding.swipeRefreshLayout.isRefreshing = it
            }
            totalAmount.observe({ lifecycle }) { total ->
                binding.apply {
                    txtTotalAmount.text = total
                    txtTotalAmount.visibility = View.VISIBLE
                    txtTotal.visibility = View.VISIBLE
                    dollarsign.visibility = View.VISIBLE
                }
            }
            errorMessage.observe({lifecycle}){
              requireContext().toast(it ?: "Something Went Wrong")
            }
        }
    }

    private fun setAdapterView() {
        binding.expensesRV.apply {
            layoutManager = LinearLayoutManager(context)
            hasFixedSize()
            adapter = expenseListAdapter
        }
    }

}