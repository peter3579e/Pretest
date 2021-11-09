package com.peter.pretest

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.peter.pretest.databinding.ActivityDemoBinding
import com.peter.pretest.ext.getVmFactory

class DemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDemoBinding

    private val viewModel by viewModels<DemoViewModel> { getVmFactory() }

    private var lastClickTime: Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_demo)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.showButton.setOnClickListener {

            // preventing double, using threshold of 1000 ms
            if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
                Log.d("peter","click to fast")
                return@setOnClickListener;
            }else{
                Log.d("peter", "show is clicked")
                viewModel.getAllCurrencies()
            }

            lastClickTime = SystemClock.elapsedRealtime()

        }

        binding.sortButton.setOnClickListener {
            if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
                Log.d("peter","click to fast")
                return@setOnClickListener;
            }else{
                viewModel.sortCurrencies()
                Log.d("peter", "sort is clicked")
            }

            lastClickTime = SystemClock.elapsedRealtime()
        }




//        viewModel.currencies.observe(this, Observer {
//                viewModel.compareToInputJson(it)
//                if (it.isNotEmpty()){
//                    Log.d("peter","the value in database = $it")
//                    Log.d("peter","run")
//                }
//        })

    }


}