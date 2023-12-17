package com.example.megavision.view

import android.app.Dialog
import android.app.ProgressDialog
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.megavision.utils.NetworkStateReceiver
import com.example.megavision.utils.SessionManager
import com.example.megavision.utils.ToastType
import com.example.megavision.utils.ext.showToast
import com.example.megavision.viewmodel.BaseViewModel

abstract class BaseActivity : AppCompatActivity(),
    NetworkStateReceiver.NetworkStateReceiverListener {

    var session: SessionManager? = null

    private lateinit var dialog: Dialog
    private lateinit var progressDialog: ProgressDialog

    private lateinit var networkStateReceiver: NetworkStateReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDefaultData()
        setupLayout()
        initAppBar()
        initIntentData()
        initRecyclerView()
        initObserver()
        initEditTextListener()
        initClickListener()
        initView()
        initBaseActivityData()
        initSearchView()
        initSpinner()
        registerNetworkListener()
    }

    private fun initDefaultData() {
        session = SessionManager(this)
    }

    private fun setupLayout() {
        when (val layout = setLayout()) {
            is View -> {
                setContentView(layout)
            }

            is Int -> {
                setContentView(layout)
            }
        }
    }

    open fun initAppBar() {}

    open fun initSearchView() {}

    open fun onDestroyActivity() {}

    open fun initView() {}

    open fun initBaseActivityData() {}

    open fun initRecyclerView() {}

    open fun initIntentData() {}

    open fun initObserver() {}

    open fun initSpinner() {}

    private fun registerNetworkListener() {
        networkStateReceiver = NetworkStateReceiver()
        networkStateReceiver.addListener(this)
        registerReceiver(
            networkStateReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    open fun initBaseObserver(viewModel: BaseViewModel) {
        with(viewModel) {
            showToastLiveData.observe(this@BaseActivity) {
                when (it.status) {
                    ToastType.STRING -> {
                        val stringText = it.item as String
                        showToast(stringText)
                    }

                    ToastType.STRING_ID -> {
                        val stringId = it.item as Int
                        val stringText = getString(stringId)
                        showToast(stringText)
                    }
                }
            }

            showLoadingLiveData.observe(this@BaseActivity) {
                when (it) {
                    true -> {
                        showProgressDialog()
                    }

                    false -> {
                        dismissProgressDialog()
                    }
                }
            }
        }
    }

    open fun initEditTextListener() {}

    open fun initClickListener() {}


    /**
     * This function is called when the activity starts.
     * Checks if the auto logoff time has been exceeded since the last activity in the session.
     * If the time is within the auto logoff threshold, it stops the auto logoff service.
     * If the time exceeds the auto logoff threshold, it starts the auto logoff service.
     */
    override fun onResume() {
        super.onResume()
    }

    /**
     * This function is called when the activity is stopped.
     * It starts the SessionOfflineLoginService.
     * This function is typically called when the app is no longer visible to the user.
     */

    override fun onStop() {
        super.onStop()
    }

    open fun setLayout(): Any? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        removeNetworkStateReceiver()
        onDestroyActivity()
    }

    private fun removeNetworkStateReceiver() {
        networkStateReceiver.removeListener(this)
        unregisterReceiver(networkStateReceiver)
    }

    override fun networkAvailable() {}

    override fun networkUnavailable() {}

    private fun showProgressDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog.setCancelable(false)
        progressDialog.setTitle("Loading")
        progressDialog.show()
    }

    private fun dismissProgressDialog() {
        if (progressDialog.isShowing) progressDialog.dismiss()
    }
}