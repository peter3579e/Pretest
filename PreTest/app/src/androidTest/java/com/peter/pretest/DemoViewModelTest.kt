package com.peter.pretest

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.peter.pretest.data.Source
import com.peter.pretest.data.source.remote.FakePretestRepository
import com.peter.pretest.factory.ViewModelFactory
import org.junit.Before
import org.junit.Test

class DemoViewModelTest() {

    private lateinit var viewModel: DemoViewModel

    @Before
    fun setup() {
        viewModel = DemoViewModel(FakePretestRepository())
    }

    /**
     *
     * Test the convert to json function here
     */
    @Test
    fun compareToInputJsonSameAsGivenJson_returnJson() {
        // Context of the app under test.
        val appContext = ApplicationProvider.getApplicationContext<Context>()
        val result = viewModel.getJsonDataFromAsset(appContext, "CurrencyList.json")
        assertThat(result).isEqualTo("[{\"id\": \"BTC\", \"name\": \"Bitcoin\", \"symbol\": \"BTC\"}, {\"id\": \"ETH\", \"name\": \"Ethereum\", \"symbol\": \"ETH\"}, {\"id\": \"XRP\", \"name\": \"XRP\", \"symbol\": \"XRP\"}, {\"id\": \"BCH\", \"name\": \"Bitcoin Cash\", \"symbol\": \"BCH\"}, {\"id\": \"LTC\", \"name\": \"Litecoin\", \"symbol\": \"LTC\"}, {\"id\": \"EOS\", \"name\": \"EOS\", \"symbol\": \"EOS\"}, {\"id\": \"BNB\", \"name\": \"Binance Coin\", \"symbol\": \"BNB\"}, {\"id\": \"LINK\", \"name\": \"Chainlink\", \"symbol\": \"LINK\"}, {\"id\": \"NEO\", \"name\": \"NEO\", \"symbol\": \"NEO\"}, {\"id\": \"ETC\", \"name\": \"Ethereum Classic\", \"symbol\": \"ETC\"}, {\"id\": \"ONT\", \"name\": \"Ontology\", \"symbol\": \"ONT\"}, {\"id\": \"CRO\", \"name\": \"Crypto.com Chain\", \"symbol\": \"CRO\"}, {\"id\": \"CUC\", \"name\": \"Cucumber\", \"symbol\": \"CUC\"}, {\"id\": \"USDC\", \"name\": \"USD Coin\", \"symbol\": \"USDC\"}]")
    }
}