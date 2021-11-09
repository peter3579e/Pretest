package com.peter.pretest

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.peter.pretest.data.Source
import com.peter.pretest.data.source.PretestRepository
import com.peter.pretest.data.source.local.PretestConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception

class DemoViewModel (private val pretestRepository: PretestRepository): ViewModel() {

    private val pretestConverter = PretestConverter()

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    private var sourceList = listOf<Source>()

    private var dataBase = listOf<Source>()

    private val _currencies = MutableLiveData<List<Source>>()

    val currencies: LiveData<List<Source>>
        get() = _currencies

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    init {
        convertJsonToList()
    }


    private fun convertJsonToList (){
        try {
            coroutineScope.launch {

                pretestConverter.convertJsonToList(getJsonDataFromAsset(PretestApplication.instance.applicationContext,"CurrencyList.json"))?.let {

                    sourceList = it
                    Log.d("peter","$sourceList")

                    Log.d("peter","${this.coroutineContext}")

                    dataBase = pretestRepository.getAllCurrencies()

                    compareToInputJson()
                }
            }
        }catch (e: Exception){
            throw e
        }
    }

    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun getAllCurrencies (){
        try {
            coroutineScope.launch {
                _currencies.postValue(pretestRepository.getAllCurrencies())
            }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
    }

    fun sortCurrencies(){
        _currencies.value = _currencies.value?.sortedWith(compareBy<Source> {it.name})
    }


    private fun insetToDatabase(list: List<Source>) {
        try {
            coroutineScope.launch {
                pretestRepository.insertData(list)
            }
        }catch (e: Exception){
            throw e
        }
    }

    private fun compareToInputJson(){
        dataBase.let { db ->
            sourceList.let { input ->
                if (db.size != input.size){
                    insetToDatabase(input)
                }else{
                    val tempDb = db.sortedWith(compareBy<Source> {it.id})
                    val tempInput = input.sortedWith(compareBy<Source> {it.id})
                    for (index in db.indices){
                        if (tempDb[index].id != tempInput[index].id){
                            insetToDatabase(input)
                            break
                        }
                    }
                }
            }
        }
    }


}