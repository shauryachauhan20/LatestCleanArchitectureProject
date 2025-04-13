package com.example.mycleanarchitecture.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mycleanarchitecture.R
import com.example.mycleanarchitecture.data.remote.models.response.QuotesResult
import com.example.mycleanarchitecture.databinding.QuotesLayoutItemBinding

class QuotesAdapter: RecyclerView.Adapter<QuotesAdapter.QuotesViewHolder>() {
    private var itemList = ArrayList<QuotesResult>()

    fun setDataList(list: List<QuotesResult>) {
        this.itemList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: QuotesLayoutItemBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.quotes_layout_item,
            parent,
            false
        )
        return QuotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        val data: QuotesResult = itemList.get(position)
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class QuotesViewHolder(val binding: QuotesLayoutItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(data: QuotesResult) {
            binding.data = data // Bind the data to the layout
            binding.executePendingBindings() // Update the UI immediately
        }
    }
}