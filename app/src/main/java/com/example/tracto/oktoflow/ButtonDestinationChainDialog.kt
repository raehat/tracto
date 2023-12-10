package com.example.tracto.oktoflow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.tracto.R
import com.example.tracto.databinding.DialogDestinationChangeBinding

object SelectedChain {
    var sc : String = "l"
}
class ButtonDestinationChainDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DialogDestinationChangeBinding.inflate(layoutInflater)

        binding.apply {
            textLinea.setOnClickListener {
                Toast.makeText(inflater.context, "Chain switched", Toast.LENGTH_LONG).show()
                SelectedChain.sc = "l"
            }
            textArbitrum.setOnClickListener {
                Toast.makeText(inflater.context, "Chain switched", Toast.LENGTH_LONG).show()
                SelectedChain.sc = "a"
            }
            textMantle.setOnClickListener {
                Toast.makeText(inflater.context, "Chain switched", Toast.LENGTH_LONG).show()
                SelectedChain.sc = "m"
            }
            textScroll.setOnClickListener {
                Toast.makeText(inflater.context, "Chain switched", Toast.LENGTH_LONG).show()
                SelectedChain.sc = "s"
            }
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