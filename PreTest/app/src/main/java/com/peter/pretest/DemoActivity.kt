package com.peter.pretest

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.peter.pretest.databinding.ActivityDemoBinding
import com.peter.pretest.ext.getVmFactory

class DemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDemoBinding

    private val viewModel by viewModels<DemoViewModel> { getVmFactory() }

    private var lastClickTimeForShow: Long = 0

    private var lastClickTimeForSort: Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_demo)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.showButton.setOnClickListener {

            // preventing double, using threshold of 1000 ms
            if (SystemClock.elapsedRealtime() - lastClickTimeForShow < 1000) {
                return@setOnClickListener
            } else {
                viewModel.getAllCurrencies()
            }

            lastClickTimeForShow = SystemClock.elapsedRealtime()

        }

        binding.sortButton.setOnClickListener {
            if (SystemClock.elapsedRealtime() - lastClickTimeForSort < 1000) {
                return@setOnClickListener
            } else {
                viewModel.sortCurrencies()
            }

            lastClickTimeForSort = SystemClock.elapsedRealtime()
        }

    }


}