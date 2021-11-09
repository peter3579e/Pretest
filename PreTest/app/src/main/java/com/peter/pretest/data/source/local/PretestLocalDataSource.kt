package com.peter.pretest.data.source.local

import android.content.Context
import androidx.lifecycle.LiveData
import com.peter.pretest.data.Source
import com.peter.pretest.data.source.PretestDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *
 * Concrete implementation of a Pretest source as a db.
 */

class PretestLocalDataSource(val context: Context) : PretestDataSource {

    override suspend fun insertData(list: List<Source>) {
        withContext(Dispatchers.IO) {
            PretestDatabase.getInstance(context).pretestDatabaseDao.insertAll(list)
        }
    }

    override suspend fun getAllCurrencies() :List<Source>{
           return PretestDatabase.getInstance(context).pretestDatabaseDao.getAllSortedCurrencies()
    }

    override suspend fun clearDatabase() {
        withContext(Dispatchers.IO) {
            PretestDatabase.getInstance(context).pretestDatabaseDao.clear()
        }
    }



}