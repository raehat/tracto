package com.example.tracto.oktoflow

import android.os.Bundle
import com.example.tracto.R

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.example.tracto.databinding.SheetSendTokensBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SendTokensSheet(private val context : FragmentActivity) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = SheetSendTokensBinding.inflate(layoutInflater)

//        return inflater.inflate(R.layout.sheet_send_tokens, container, false)

        binding.buttonTokenChange.setOnClickListener {
            val bottomSheetFragment = TokenChangeDialog()
            bottomSheetFragment.show(context.supportFragmentManager, bottomSheetFragment.tag)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Handle button click to close the bottom sheet
//        bottomSheetButton.setOnClickListener {
//            dismiss()
//        }
    }
}
