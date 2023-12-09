package com.example.tracto.oktoflow.bottomnavfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tracto.databinding.FragmentMyTokensBinding
import com.example.tracto.databinding.FragmentSendTokensBinding
import com.example.tracto.oktoflow.adapter.MyTokensAdapter
import tech.okto.oktowallet.OktoWallet

class SendTokensFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSendTokensBinding.inflate(layoutInflater)

        binding.progressBar.visibility = View.VISIBLE

        OktoWallet.getPortfolio { result, error ->
            if (result != null) {
                val list : MutableList<MyTokensAdapter.MyTokensData> = mutableListOf()
                for (portfolioToken in result) {
                    list.add(
                        MyTokensAdapter.MyTokensData(
                            portfolioToken.tokenName,
                            portfolioToken.quantity,
                            portfolioToken.amountInInr
                        ))
                }

                binding.recyclerViewMyTokens.layoutManager = LinearLayoutManager(requireContext())
                binding.recyclerViewMyTokens.adapter = MyTokensAdapter(requireActivity(), list, true)
                binding.progressBar.visibility = View.GONE
            }
        }

        return binding.root
    }
}