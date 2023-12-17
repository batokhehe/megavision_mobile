package com.example.megavision.view

import android.view.View
import com.example.megavision.R
import com.example.megavision.databinding.ActivityLoginBinding
import com.example.megavision.utils.NetworkStatus
import com.example.megavision.utils.Result
import com.example.megavision.utils.ext.getSafeText
import com.example.megavision.utils.ext.goToHomeActivity
import com.example.megavision.utils.ext.showToast
import com.example.megavision.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity() {

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModel()

    override fun setLayout(): View {
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initClickListener() {
        with(binding) {
            btnSignIn.setOnClickListener { _ ->
                viewModel.login(
                    email = etEmail.getSafeText().trim(),
                    password = etPassword.getSafeText().trim()
                )
            }
        }
    }

    override fun initObserver() {
        super.initBaseObserver(viewModel)

        viewModel.loginResultLiveData.observe(this) {
            when (it) {
                Result.GO_TO_HOME -> {
                    goToHomeActivity()
                }

                else -> {
                    showToast(messageId = R.string.no_message_available_from_server)
                }
            }
        }
    }

    override fun networkAvailable() {
        viewModel.setNetworkStatus(NetworkStatus.AVAILABLE)
    }

    override fun networkUnavailable() {
        viewModel.setNetworkStatus(NetworkStatus.UNAVAILABLE)
    }
}