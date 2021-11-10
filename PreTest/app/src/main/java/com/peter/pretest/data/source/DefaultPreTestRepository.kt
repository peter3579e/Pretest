package com.peter.pretest.data.source

import androidx.lifecycle.LiveData
import com.peter.pretest.data.Source

/**
 *
 * Concrete implementation to load Pretest sources.
 */

class DefaultPreTestRepository(
    private val pretestRemoteDataSource: PretestDataSource,
    private val pretestLocalDataSource: PretestDataSource
) : PretestRepository {

    override suspend fun insertData(list: List<Source>) {
        return pretestLocalDataSource.insertData(list)
    }

    override suspend fun getAllCurrencies(): List<Source> {
        return pretestLocalDataSource.getAllCurrencies()
    }

    override suspend fun clearDatabase() {
        pretestLocalDataSource.clearDatabase()
    }

}