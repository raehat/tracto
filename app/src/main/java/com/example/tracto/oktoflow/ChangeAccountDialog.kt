package com.example.tracto.oktoflow

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tracto.databinding.ChangeAccountDialogBinding
import com.example.tracto.oktoflow.adapter.MyTokensAdapter

class ChangeAccountDialog(private val dataList: List<MyTokensAdapter.MyTokensData>) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = ChangeAccountDialogBinding.inflate(layoutInflater)
        val view = binding.root

        val recyclerView = binding.recyclerViewPortfolio
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter = MyTokensAdapter(dataList)
        recyclerView.adapter = adapter

        return AlertDialog.Builder(requireContext())
            .setView(view)
            .setTitle("Your Dialog Title")
            .setPositiveButton("Close") { _, _ ->
                // Handle positive button click if needed
            }
            .create()
    }
}