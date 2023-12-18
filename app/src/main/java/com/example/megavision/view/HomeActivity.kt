package com.example.megavision.view

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.megavision.adapter.HistoryAdapter
import com.example.megavision.data.model.List
import com.example.megavision.databinding.ActivityHomeBinding
import com.example.megavision.utils.SessionManager
import com.example.megavision.utils.Status
import com.example.megavision.viewmodel.HomeViewModel

class HomeActivity : AppCompatActivity() {

    private var progressDialog: ProgressDialog? = null
    private lateinit var sessionManager: SessionManager
    private lateinit var _binding: ActivityHomeBinding
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapterHistory: HistoryAdapter
    private var historyList: ArrayList<List> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
    }

    private fun initData() = with(binding) {
        sessionManager = SessionManager(this@HomeActivity)
        adapterHistory = HistoryAdapter(historyList, this@HomeActivity)
        rvHistory.adapter = adapterHistory
        rvHistory.layoutManager = LinearLayoutManager(this@HomeActivity)

        viewModel.hitReport()
        observeReportData()
    }

    private fun observeReportData() {
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
            if (data != null) {
                binding.tvIncome.text = String.format("%s %s", "Rp", data.data?.orderAmount)
                binding.tvSales.text =
                    String.format("%s %s", data.data?.orderCount, " Sales Orders")
                historyList.clear()
                historyList.addAll(data.data?.list!!)
                adapterHistory.notifyDataSetChanged()
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
}