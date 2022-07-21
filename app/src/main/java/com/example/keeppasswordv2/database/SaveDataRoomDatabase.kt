package com.example.keeppasswordv2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [(SaveData::class)], version = 3)
abstract class SaveDataRoomDatabase: RoomDatabase() {

    abstract fun saveDataDao(): SaveDataDao

    companion object{
        @Volatile
        private var INSTANCE: SaveDataRoomDatabase? = null

        fun getInstance(context: Context): SaveDataRoomDatabase {
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SaveDataRoomDatabase::class.java,
                        "saveData_database"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }

                return instance
            }

        }

    }

}