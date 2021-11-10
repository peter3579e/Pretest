package com.peter.pretest.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.peter.pretest.data.Source

/**
 *
 * Defines methods for using the [Source] class with Room.
 */
@Dao
interface PretestDatabaseDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(products: List<Source>)

    /**
     * Deletes all values from the table.
     *
     * This does not delete the table, only its contents.
     */
    @Query("DELETE FROM source_table")
    fun clear()

    /**
     * Selects and returns all rows in the table,
     */
    @Query("SELECT * FROM source_table ")
    fun getAllSortedCurrencies(): List<Source>

}