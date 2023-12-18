package com.example.megavision.view

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.megavision.R
import com.example.megavision.databinding.ActivityLoginBinding
import com.example.megavision.utils.SessionManager
import com.example.megavision.utils.Status
import com.example.megavision.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private var progressDialog: ProgressDialog? = null
    private lateinit var sessionManager: SessionManager
    private lateinit var _binding: ActivityLoginBinding
    private val binding get() = _binding!!
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        initActionButton()
    }

    private fun initData() {
        sessionManager = SessionManager(this)
    }

    private fun initActionButton() = with(binding) {
        btnSignIn.setOnClickListener {
            if (etEmail.text.isEmpty()) {
                Toast.makeText(
                    this@LoginActivity,
                    resources.getString(R.string.empty_email_field),
                    Toast.LENGTH_SHORT
                ).show()
            } else if (etPassword.text.isEmpty()) {
                Toast.makeText(
                    this@LoginActivity,
                    resources.getString(R.string.empty_password_field),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                viewModel.hitLogin(etEmail.text.toString(), etPassword.text.toString())
                observeLoginData()
            }
        }
    }

    private fun observeLoginData() {
        viewModel.networkState?.observe(this) {
            when (it.status) {
                Status.RUNNING -> {
                    showProgressDialog()
                }

                Status.FAILED, Status.SUCCESS -> {
                    if (progressDialog != null && progressDialog?.isShowing == true) progressDialog?.dismiss()
                    if (it.status == Status.FAILED) Toast.makeText(
                        this,
                        it.msg,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        viewModel.responseData?.observe(this) { data ->
            if (data.accessToken != "") {
                sessionManager.setFromLogin(data)
                gotoHomeActivity()
            } else
                Toast.makeText(this, "Internal Server Error.", Toast.LENGTH_LONG).show()
        }
    }

    private fun showProgressDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog?.setCancelable(false)
        progressDialog?.setTitle("Loading")
        progressDialog?.show()
    }

    private fun gotoHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}