package com.example.tracto.oktoflow

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.tracto.R
import com.example.tracto.databinding.ActivityOktoHomePageBinding
import com.example.tracto.oktoflow.adapter.MyTokensAdapter
import com.example.tracto.oktoflow.bottomnavfragments.MyTokensFragment
import com.example.tracto.oktoflow.bottomnavfragments.SendTokensFragment
import com.example.tracto.oktoflow.bottomnavfragments.SentTokensFragment
import com.example.tracto.oktoflow.bottomnavfragments.UnclaimedTokensFragment
import tech.okto.oktowallet.OktoWallet


class OktoHomePage : AppCompatActivity() {

    private lateinit var binding: ActivityOktoHomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOktoHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.getString("idToken")?.let {
            OktoWallet.authenticate(it) { result, error ->
                // wallet address of the user
                binding.textViewAccountAddress.text = result!![0].address
                binding.textViewAccount.text = result[0].networkName
                print("result: $result error: $error")
            }
        }

        binding.apply {

            val iconColorStates = ColorStateList(
                arrayOf(
                    intArrayOf(-android.R.attr.state_checked),
                    intArrayOf(android.R.attr.state_checked)
                ), intArrayOf(
                    Color.parseColor("#494949"),
                    Color.parseColor("#007CB5")
                )
            )

            textViewAccount.setOnClickListener {

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
                        val changeAccountDialog = ChangeAccountDialog(list)
                        changeAccountDialog.dialog?.show()
                    }
                }
            }

            bottomNavigation.itemIconTintList = iconColorStates
            bottomNavigation.itemTextColor = iconColorStates

            bottomNavigation.setOnNavigationItemSelectedListener { item ->
                var selectedFragment: Fragment? = null

                when (item.itemId) {
                    R.id.fragment_send_tokens -> selectedFragment = SendTokensFragment()
                    R.id.fragment_view_sent_tx -> selectedFragment = SentTokensFragment()
                    R.id.fragment_view_unclaimed_tx -> selectedFragment = UnclaimedTokensFragment()
                    R.id.fragment_my_tokens -> selectedFragment = MyTokensFragment()
                }

                selectedFragment?.let {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, it).commit()
                }

                true
            }

            // Set the default fragment
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, MyTokensFragment()).commit()
        }

    }
}