package com.peter.pretest.data.source

import androidx.lifecycle.LiveData
import com.peter.pretest.data.Source

/**
 *
 * Interface to the Pretest layers.
 */

interface PretestRepository {


    suspend fun insertData(list: List<Source>)

    suspend fun getAllCurrencies(): List<Source>

    suspend fun clearDatabase()

}