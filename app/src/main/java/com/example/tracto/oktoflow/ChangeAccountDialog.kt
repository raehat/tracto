package com.example.tracto.oktoflow

import android.app.Dialog
import android.content.Context
import com.example.tracto.R

class ChangeAccountDialog(private val context: Context) {

    private val dialog: Dialog = Dialog(context)

    init {
        dialog.setContentView(R.layout.change_account_dialog)
    }

    fun show() {
        dialog.show()
    }

}