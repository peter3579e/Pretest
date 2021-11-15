package com.peter.pretest

import com.peter.pretest.data.Source
import com.peter.pretest.data.source.PretestRepository

class FakePretestRepository : PretestRepository {

    private var currencies = listOf<Source>()

    override suspend fun insertData(list: List<Source>) {
        currencies = list
    }

    override suspend fun getAllCurrencies(): List<Source> {

        return currencies

    }

    override suspend fun clearDatabase() {

        currencies = listOf()

    }
}