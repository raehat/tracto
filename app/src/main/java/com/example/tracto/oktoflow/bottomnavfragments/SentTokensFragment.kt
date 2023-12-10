package com.example.tracto.oktoflow.bottomnavfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tracto.databinding.FragmentSentTokensBinding
import com.example.tracto.oktoflow.adapter.SentItemAdapter
import org.web3j.abi.FunctionEncoder
import org.web3j.abi.TypeReference
import org.web3j.abi.datatypes.Address
import org.web3j.abi.datatypes.Bool
import org.web3j.abi.datatypes.DynamicArray
import org.web3j.abi.datatypes.Function
import org.web3j.abi.datatypes.Type
import org.web3j.abi.datatypes.generated.Uint256
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.request.Transaction
import org.web3j.protocol.http.HttpService
import org.web3j.tuples.generated.Tuple8
import tech.okto.oktowallet.OktoWallet
import java.util.List

class SentTokensFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSentTokensBinding.inflate(layoutInflater)

        OktoWallet.getWallets { result, error ->
            val web3 =
                Web3j.build(HttpService("https://polygon-mumbai.infura.io/v3/39b2abffe10e4659a12074ce9a344bae"))

            val function = Function(
                "getReceivedPayments",
                listOf(Address(result!![0].address)),
                listOf(object : TypeReference<DynamicArray<Type<Any>>>() {})
            )


            val encodedFunction = FunctionEncoder.encode(function)

            val ethCall = web3.ethCall(
                Transaction.createEthCallTransaction(
                    result[0].address,
                    "0x9c3C9283D3e44854697Cd22D3Faa240Cfb032889",
                    encodedFunction
                ),
                DefaultBlockParameterName.LATEST
            ).sendAsync().get()

            println("serror: ${ethCall.error.data}")
        }

        val listexdata : MutableList<SentItemAdapter.SentItemData> = mutableListOf()
        listexdata.add(SentItemAdapter.SentItemData("0xC130CD2485b4164d40B14240f24E1B80944d7FAB sent 0.01 USDT to 0xdCb5Ca7561f4571b4A0824E2e0c3ad683023a4cc", false, false))
        listexdata.add(SentItemAdapter.SentItemData("0xC130CD2485b4164d40B14240f24E1B80944d7FAB sent 0.01 USDT to 0xdCb5Ca7561f4571b4A0824E2e0c3ad683023a4cc", false, false))
        listexdata.add(SentItemAdapter.SentItemData("0xC130CD2485b4164d40B14240f24E1B80944d7FAB sent 0.01 USDT to 0xdCb5Ca7561f4571b4A0824E2e0c3ad683023a4cc", false, false))
        listexdata.add(SentItemAdapter.SentItemData("0xC130CD2485b4164d40B14240f24E1B80944d7FAB sent 0.01 USDT to 0xdCb5Ca7561f4571b4A0824E2e0c3ad683023a4cc", false, false))

        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.adapter = SentItemAdapter(requireActivity(), listexdata, false)

        return binding.root
    }
}