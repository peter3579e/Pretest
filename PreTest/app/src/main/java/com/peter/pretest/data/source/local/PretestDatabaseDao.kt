package com.peter.pretest.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.peter.pretest.data.Source

/**
 * Created by Wayne Chen in Jul. 2019.
 *
 * Defines methods for using the [Product] class with Room.
 */
@Dao
interface PretestDatabaseDao {


    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(products : List<Source>)


    /**
     * Deletes the [Product] with given id, colorCode and size
     */
    @Query("DELETE from source_table")
    fun delete()

    /**
     * Deletes all values from the table.
     *
     * This does not delete the table, only its contents.
     */
    @Query("DELETE FROM source_table")
    fun clear()

    /**
     * Selects and returns all rows in the table,
     *
     * sorted by product_id in ascending order.
     */
    @Query("SELECT * FROM source_table ")
    fun getAllSortedCurrencies(): List<Source>

}