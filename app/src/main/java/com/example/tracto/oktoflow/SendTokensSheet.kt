package com.example.tracto.oktoflow

import android.os.Bundle
import android.util.Log
import com.example.tracto.R

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.example.tracto.databinding.SheetSendTokensBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import org.web3j.abi.FunctionEncoder
import org.web3j.abi.TypeReference
import org.web3j.abi.datatypes.Address
import org.web3j.abi.datatypes.Function
import org.web3j.abi.datatypes.Type
import org.web3j.abi.datatypes.generated.Uint256
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.request.Transaction
import org.web3j.protocol.core.methods.response.EthCall
import org.web3j.protocol.http.HttpService
import org.web3j.utils.Numeric
import tech.okto.oktowallet.OktoWallet
import java.io.IOException
import java.math.BigInteger
import java.util.Collections
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class SendTokensSheet(private val context : FragmentActivity) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = SheetSendTokensBinding.inflate(layoutInflater)

//        return inflater.inflate(R.layout.sheet_send_tokens, container, false)

//        binding.buttonTokenChange.setOnClickListener {
//            val bottomSheetFragment = TokenChangeDialog()
//            bottomSheetFragment.show(context.supportFragmentManager, bottomSheetFragment.tag)
//        }
//
//        binding.buttonSend.setOnClickListener {
//            val receivingAddress = binding.editTextReceivingAddress.text.toString()
//            val amount = binding.editTextAmount.text.toString().toDouble() * 1000000000000000000
//            val erc20Token = "0x9c3C9283D3e44854697Cd22D3Faa240Cfb032889"
//
//            val contractAddress = "0x2c80688bd7D367A269a3C7fA05597188264D0614"
//            val functionName = "sendPayment"
//
//            OktoWallet.getPortfolio {result, error ->
//                val amount : Double = result!![0].quantity.toDouble() * 1000000000000000000
//
//                val function = Function(
//                    "approve",
//                    listOf(Address(contractAddress), Uint256(amount.toLong())),
//                    emptyList()
//                )
//
//                val encodedFunction = FunctionEncoder.encode(function)
//                println("Encoded function data: $encodedFunction")
//
//                OktoWallet.getWallets {result, error ->
//                    OktoWallet.executeRawTransaction(
//                        result!![0].networkName,
//                        encodedFunction,
//                        result[0].address,
//                        "0x9c3C9283D3e44854697Cd22D3Faa240Cfb032889",
//                        "0x00"
//                    ) { result, error ->
//                        if (result != null)
//                            println(result)
//                        else
//                            println(error)
//                    }
//                }
//
//            }
//
//            // Create the function
//            val function = Function(
//                functionName,
//                listOf(
//                    Address(receivingAddress),
//                    Address(erc20Token),
//                    Uint256(amount.toLong()),
//                    Uint256(10000L)
//                ),
//                emptyList()
//            )
//
//            // Encode the function call data
////            val encodedFunction = FunctionEncoder.encode(function)
////            println("Encoded function data: $encodedFunction")
////
////            OktoWallet.getWallets {result, error ->
////                val web3 = Web3j.build(HttpService("https://polygon-mumbai.infura.io/v3/39b2abffe10e4659a12074ce9a344bae"))
////
////                val function = Function(
////                    "allowance",
////                    listOf(Address(result!![0].address), Address("0x2c80688bd7D367A269a3C7fA05597188264D0614")),
////                    listOf(object : TypeReference<Uint256?>() {})
////                )
////
////                val encodedFunction = FunctionEncoder.encode(function)
////
////                val ethCall = web3.ethCall(
////                    Transaction.createEthCallTransaction(
////                        result[0].address,
////                        "0x9c3C9283D3e44854697Cd22D3Faa240Cfb032889",
////                        encodedFunction
////                    ),
////                    DefaultBlockParameterName.LATEST
////                ).sendAsync().get()
////
////                println("serror: ${ethCall.error.data}")
////
////            }
//        }

        binding.buttonSend.setOnClickListener {

            val function = Function(
                "sendPayment",
                listOf(
                    Address(binding.editTextReceivingAddress.text.toString()),
                    Address("0x9c3C9283D3e44854697Cd22D3Faa240Cfb032889"),
                    Uint256((binding.editTextAmount.text.toString().toDouble() * 1000000000000000000).toLong()),
                    Uint256(10000L)
                ),
                emptyList()
            )

            val encodedFunction = FunctionEncoder.encode(function)

            OktoWallet.getWallets {result, error ->
                OktoWallet.executeRawTransaction(
                    result!![0].networkName,
                    encodedFunction,
                    result[0].address,
                    "0x9c3C9283D3e44854697Cd22D3Faa240Cfb032889",
                    "0x00"
                ) { result, error ->
                    if (result != null)
                        println(result)
                    else
                        println(error)
                }
            }

        }

        return binding.root
    }
}

