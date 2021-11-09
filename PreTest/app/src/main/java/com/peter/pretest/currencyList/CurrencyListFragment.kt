package com.peter.pretest.currencyList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.peter.pretest.DemoViewModel
import com.peter.pretest.databinding.FragmentCurrencylistBinding
import com.peter.pretest.ext.getVmFactory


class CurrencyListFragment : Fragment() {

    private lateinit var binding: FragmentCurrencylistBinding
    private lateinit var demoViewModel: DemoViewModel
    private lateinit var adapter: CurrenciesAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCurrencylistBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        demoViewModel = ViewModelProvider(requireActivity()).get(DemoViewModel::class.java)

        adapter = CurrenciesAdapter(this.requireView())
        binding.recyclerView.adapter = adapter


        demoViewModel.currencies.observe(viewLifecycleOwner, Observer {
            Log.d("peter", "the button = $it")
            it?.let {
                if (it.isNotEmpty()) {
                    Log.d("peter", "fragmentValue = $it")
                    adapter.submitList(it)
                    adapter.notifyDataSetChanged()
                }
            }
        })

    }


}