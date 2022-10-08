package com.github.travelbankapplication.ui.ExpenseList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.api.models.entities.TravelBankAPIResponseItem
import com.github.travelbankapplication.databinding.ItemExpenseBinding
import com.github.travelbankapplication.helper.loadImage

class ExpenseListAdapter(val onItemClicked: (clickedItem: TravelBankAPIResponseItem) -> Unit) :
    ListAdapter<TravelBankAPIResponseItem, ExpenseListAdapter.ViewHolder>(object :
        DiffUtil.ItemCallback<TravelBankAPIResponseItem>() {

        override fun areItemsTheSame(
            oldItem: TravelBankAPIResponseItem,
            newItem: TravelBankAPIResponseItem
        ): Boolean {
            return "${oldItem.id}" == "${newItem.id}"
        }

        override fun areContentsTheSame(
            oldItem: TravelBankAPIResponseItem,
            newItem: TravelBankAPIResponseItem
        ): Boolean {
            return oldItem == newItem
        }

    }) {

    inner class ViewHolder(val binding: ItemExpenseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemData: TravelBankAPIResponseItem) {
            setItemData(itemData, binding)
        }

    }

    private fun setItemData(expense: TravelBankAPIResponseItem, binding: ItemExpenseBinding) {
        binding.apply {
            merchantTitle.text = expense.expenseVenueTitle ?: "Not Set"
            date.text = setDate(expense.date)
            amount.text = "$${expense.amount}"
            category.text = expense.tripBudgetCategory
            currency.text = expense.currencyCode

            if (!expense.attachments?.get(0)?.thumbnails?.full.isNullOrEmpty()) {
                attachmentPreview.loadImage(expense.attachments?.get(0)?.thumbnails?.full)
                attachmentPreview.visibility = View.VISIBLE
            } else {
                attachmentPreview.visibility = View.INVISIBLE
            }
        }
    }

    private fun setDate(date: String?): String {
        return if(!date.isNullOrEmpty()){
            val delim = "T"
            val list = date.split(delim)
            list[0]
        }else{
            "Not Set"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemExpenseBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemData = getItem(position)
        holder.bind(itemData)
        holder.binding.root.setOnClickListener {
            onItemClicked(itemData)
        }
    }
}