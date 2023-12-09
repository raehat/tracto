package com.example.tracto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.tracto.databinding.ActivityLoginScreenBinding
import com.example.tracto.oktoflow.OktoHomePage
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class LoginScreen : AppCompatActivity() {

    private val RC_SIGN_IN = 9001
    private var mGoogleSignInClient: GoogleSignInClient? = null
    private lateinit var binding : ActivityLoginScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken("685503265738-gh3d6lmmn8msb3qt1t2cjlg3ljb5v97m.apps.googleusercontent.com")
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        binding.apply {

            buttonLoginUsingGoogle.setOnClickListener {
                loginUsingGoogle()
            }

        }

    }

    private fun loginUsingGoogle() {
        signIn()
    }

    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient!!.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount = completedTask.getResult(ApiException::class.java)
            account.idToken?.let {
                intent = Intent(this, OktoHomePage::class.java)
                intent.putExtra("idToken", it)
                startActivity(intent)
            }

        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            println(e)
        }
    }
}