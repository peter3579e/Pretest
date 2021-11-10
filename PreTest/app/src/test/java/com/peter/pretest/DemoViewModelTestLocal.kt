package com.peter.pretest

import com.google.common.truth.Truth
import com.peter.pretest.data.Source
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DemoViewModelTestLocal {

    private lateinit var viewModel: DemoViewModel

    private val list1: List<Source> = listOf(
        Source("BTC", "Bitcoin", "BTC"), Source("ETH", "Ethereum", "ETH"),
        Source("XRP", "XRP", "XRP")
    )
    private val list2: List<Source> = listOf(
        Source("BTC", "Bitcoin", "BTC"), Source("ETH", "Ethereum", "ETH"),
        Source("XRP", "XRP", "XRP")
    )


    @Before
    fun setup() {
        viewModel = DemoViewModel(null)
    }

    /**
     *
     * Test the input Json if same as the data in the database
     */
    @Test
    fun compareInputJson() {
        val result = viewModel.compareToInputJson(list1, list2)
        Truth.assertThat(result).isTrue()
    }

}