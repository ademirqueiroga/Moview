package com.ademir.mooview.login

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ademir.mooview.R
import com.ademir.mooview.home.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginMvp.View {

    private val CODE_REGISTER_ACTIVITY = 13

    private val progressDialog by lazy {
        val dialog = ProgressDialog(this)
        dialog.setTitle("Logging in")
        dialog.setMessage("Fetching data...")
        dialog
    }

    val presenter = LoginPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
            tryLogin()
        }
        btn_register.setOnClickListener { showRegisterUI() }

        et_password.setOnEditorActionListener { _, _, _ ->
            tryLogin()
            true
        }

    }

    override fun onStop() {
        super.onStop()
        presenter.unsubscribe()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CODE_REGISTER_ACTIVITY && resultCode == Activity.RESULT_OK) {
            showHomeUI()
        }
    }

    fun tryLogin() {
        val username = et_username.text.toString()
        val password = et_password.text.toString()
        presenter.login(this@LoginActivity, username, password)
    }

    override fun showProgressDialog(show: Boolean) = with(progressDialog) {
        if (show) show() else dismiss()
    }

    override fun showRegisterUI() {
        startActivityForResult(Intent(this, RegisterActivity::class.java), CODE_REGISTER_ACTIVITY)
    }

    override fun showHomeUI() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

}

