package com.example.tracto.oktoflow.bottomnavfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tracto.databinding.FragmentUnclaimedTokensBinding

class UnclaimedTokensFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentUnclaimedTokensBinding.inflate(layoutInflater)

        return binding.root
    }
}