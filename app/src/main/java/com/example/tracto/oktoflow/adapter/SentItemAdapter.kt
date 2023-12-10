package com.example.tracto.oktoflow.adapter

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.tracto.databinding.ItemCardMyTokensBinding
import com.example.tracto.databinding.SentItemCardBinding

class SentItemAdapter(private val context : FragmentActivity, private val dataList: List<SentItemData>, private val isItemClickable: Boolean) : RecyclerView.Adapter<SentItemAdapter.MyViewHolder>() {

    data class SentItemData(val tx_str: String, val claimed: Boolean, val reverted: Boolean)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = SentItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]

        holder.binding.txText.text = currentItem.tx_str
        holder.binding.revertButton.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(context)

            alertDialogBuilder.setTitle("Revert Transaction")
            alertDialogBuilder.setMessage("Do you want to revert this transaction?")

            // Set the positive button
            alertDialogBuilder.setPositiveButton("Yes") { dialogInterface: DialogInterface, _: Int ->
                // Handle the positive button click (revert transaction logic here)
                // For example, you can display a toast message
                Toast.makeText(context, "Transaction reverted", Toast.LENGTH_SHORT).show()
                dialogInterface.dismiss() // dismiss the dialog
            }

            // Set the negative button
            alertDialogBuilder.setNegativeButton("No") { dialogInterface: DialogInterface, _: Int ->
                // Handle the negative button click or do nothing
                // For example, you can dismiss the dialog
                dialogInterface.dismiss()
            }

            // Create and show the dialog
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class MyViewHolder(val binding: SentItemCardBinding) : RecyclerView.ViewHolder(binding.root)
}