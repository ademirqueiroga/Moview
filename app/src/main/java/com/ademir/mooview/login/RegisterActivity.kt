package com.ademir.mooview.login

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ademir.mooview.R
import com.ademir.mooview.controllers.SessionController
import com.ademir.mooview.core.App
import com.ademir.mooview.home.HomeActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        btn_sign_up.setOnClickListener { validateAndRegister() }
    }

    override fun onStop() {
        super.onStop()
        disposable?.dispose()
    }

    fun validateAndRegister() {
        val username = et_username.text.toString()
        val email = et_email.text.toString()
        val password = et_password.text.toString()
        val confirmPassword = et_confirm_password.text.toString()
        val firstName = et_first_name.text.toString()
        val lastName = et_last_name.text.toString()

        if (password == confirmPassword) {
            val dialog = ProgressDialog(this)
            dialog.setTitle("Register")
            dialog.setMessage("Sending information to database...")
            dialog.show()

            disposable = App.apiService!!.signUp(username, email, password, firstName, lastName)
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .doOnTerminate { dialog.dismiss() }
                    .subscribe(
                            {
                                SessionController.user = it.user
                                SessionController.createSession(this@RegisterActivity, it.user!!)
                                setResult(Activity.RESULT_OK)
                                finish()
                            },
                            { Toast.makeText(this@RegisterActivity, R.string.error_try_again, Toast.LENGTH_SHORT).show() }
                    )
        }
    }

}
