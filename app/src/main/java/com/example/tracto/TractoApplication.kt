package com.example.tracto

import android.app.Application
import tech.okto.oktowallet.OktoWallet

class TractoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        OktoWallet.init(this,"f708d23c-df68-4107-a9bc-843a83b30d85")
    }
}