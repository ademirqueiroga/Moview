package com.ademir.moview.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.ademir.moview.R
import com.ademir.moview.home.HomeActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class SignInActivity : AppCompatActivity() {

    private val TAG = SignInActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val signInProviders = listOf(
                AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build(),
                AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()
        )

        btn_fb.setOnClickListener {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(signInProviders)
                            .build(),
                    CODE_SIGNIN_FACEBOOK
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CODE_SIGNIN_FACEBOOK && resultCode == Activity.RESULT_OK) {
            data?.let {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }
    }

    companion object {
        const val CODE_SIGNIN_FACEBOOK = 0xA2
    }

}

