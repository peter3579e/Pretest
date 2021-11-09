package com.peter.pretest.currencyList

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.peter.pretest.PretestApplication
import com.peter.pretest.PretestFunctions.selectImage
import com.peter.pretest.R
import com.peter.pretest.data.Source
import com.peter.pretest.databinding.ItemCurrenciesCellBinding

class CurrenciesAdapter(private val fragment: View) :
    ListAdapter<Source, CurrenciesAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(private var binding: ItemCurrenciesCellBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun bind(currencies: Source, fragment: View) {
            binding.name = currencies.name
            binding.symbolName = currencies.symbol
            binding.logo.setImageDrawable(selectImage(currencies.symbol))
            binding.executePendingBindings()
            binding.hook.setOnClickListener {
                Log.d("peter", "the click value = ${currencies.name})")
                val snackbar = Snackbar.make(fragment, currencies.name, Snackbar.LENGTH_LONG)
                snackbar.show()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCurrenciesCellBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), fragment)
    }


    companion object DiffCallback : DiffUtil.ItemCallback<Source>() {
        override fun areItemsTheSame(oldItem: Source, newItem: Source): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Source, newItem: Source): Boolean {
            return oldItem.id == newItem.id
        }
    }


}