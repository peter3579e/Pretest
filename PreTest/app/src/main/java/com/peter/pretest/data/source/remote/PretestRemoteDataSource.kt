package com.peter.pretest.data.source.remote

import androidx.lifecycle.LiveData
import com.peter.pretest.R
import com.peter.pretest.data.Source
import com.peter.pretest.data.source.PretestDataSource
import com.peter.pretest.util.Logger
import com.peter.pretest.util.Util.getString
import com.peter.pretest.util.Util.isInternetConnected

/**
 *
 * Implementation of the Pretest source that from network.
 */

object PretestRemoteDataSource : PretestDataSource {


    override suspend fun insertData(list:List<Source>) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllCurrencies(): List<Source> {
        TODO("Not yet implemented")
    }

    override suspend fun clearDatabase() {
        TODO("Not yet implemented")
    }


}