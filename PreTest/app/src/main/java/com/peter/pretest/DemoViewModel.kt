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

class DemoViewModel(private val pretestRepository: PretestRepository) : ViewModel() {

    private val pretestConverter = PretestConverter()

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    private var sourceList = listOf<Source>()

    private var dataBase = listOf<Source>()

    private val _currencies = MutableLiveData<List<Source>>()

    val currencies: LiveData<List<Source>>
        get() = _currencies

    // the Coroutine runs using the Main (IO) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    init {
        convertJsonToList()
    }


    private fun convertJsonToList() {
        try {
            coroutineScope.launch {

                pretestConverter.convertJsonToList(
                    getJsonDataFromAsset(
                        PretestApplication.instance.applicationContext,
                        PretestApplication.instance.getString(R.string.json_file)
                    )
                )?.let {

                    sourceList = it

                    dataBase = pretestRepository.getAllCurrencies()

                    compareToInputJson(dataBase,sourceList)
                }
            }
        } catch (e: Exception) {
            throw e
        }
    }

    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun getAllCurrencies() {
        try {
            coroutineScope.launch {
                _currencies.postValue(pretestRepository.getAllCurrencies())
            }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
    }

    fun sortCurrencies() {
        _currencies.value = _currencies.value?.sortedWith(compareBy<Source> { it.name })
    }


    private fun insetToDatabase(list: List<Source>) {
        try {
            coroutineScope.launch {
                pretestRepository.insertData(list)
            }
        } catch (e: Exception) {
            throw e
        }
    }

  fun compareToInputJson(dataBase:List<Source>,jsonList:List<Source>):Boolean {
        dataBase.let { db ->
            jsonList.let { input ->
                if (db.size != input.size) {
                    insetToDatabase(input)
                    return false
                } else {
                    val tempDb = db.sortedWith(compareBy<Source> { it.id })
                    val tempInput = input.sortedWith(compareBy<Source> { it.id })
                    for (index in db.indices) {
                        if (tempDb[index].id != tempInput[index].id) {
                            insetToDatabase(input)
                            return false
                        }
                    }
                }
            }
        }
      return true
    }

    /**
     * When the [ViewModel] is finished, we cancel our coroutine [viewModelJob], which tells the
     * Retrofit service to stop.
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}