package com.example.keeppasswordv2.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface SaveDataDao {

    @Insert
    fun insertData(data: SaveData)

    @Query("SELECT * FROM saveData_table WHERE saveLoginFK == :name")
    fun getAllDataByFK(name: String): LiveData<List<SaveData>>

    @Query("DELETE FROM saveData_table WHERE saveEmail == :name")
    fun deleteEmail(name: String)

    @Query("DELETE FROM saveData_table WHERE saveData_id == :id")
    fun deleteById(id: Int)

}