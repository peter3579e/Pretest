package com.peter.pretest.data.source

import androidx.lifecycle.LiveData
import com.peter.pretest.data.Source

/**
 *
 * Main entry point for accessing Pretest sources.
 */

interface PretestDataSource {

    suspend fun insertData(list: List<Source>)

    suspend fun getAllCurrencies(): List<Source>

    suspend fun clearDatabase()

}