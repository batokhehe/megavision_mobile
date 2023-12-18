package com.example.megavision.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.megavision.data.model.List
import com.example.megavision.databinding.ItemHistoryBinding

class HistoryAdapter(var listData: ArrayList<List>, var context: Context) :
    RecyclerView.Adapter<HistoryAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {

        val itemViewBinding = ItemHistoryBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
        return MyViewHolder(itemViewBinding, context)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(myViewHolder: MyViewHolder, i: Int) {
        val data = listData[i]
        myViewHolder.bind(data, i)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class MyViewHolder(
        private val item: ItemHistoryBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(item.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(data: List, position: Int) {
            item.tvName.text = data.clientName
            item.tvAmount.text = data.orderAmount
        }
    }
}