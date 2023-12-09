package com.example.tracto.oktoflow.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tracto.databinding.ItemCardMyTokensBinding

class MyTokensAdapter(private val dataList: List<MyTokensData>) : RecyclerView.Adapter<MyTokensAdapter.MyViewHolder>() {

    data class MyTokensData(val tokenName: String, val maticAmount: String, val inrAmount: String)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemCardMyTokensBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]

        holder.binding.textViewTokenName.text = currentItem.tokenName
        holder.binding.textViewTokenAmt.text = currentItem.maticAmount + " " + currentItem.tokenName
        holder.binding.textViewTokenInr.text = currentItem.inrAmount + " INR"
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class MyViewHolder(val binding: ItemCardMyTokensBinding) : RecyclerView.ViewHolder(binding.root)
}