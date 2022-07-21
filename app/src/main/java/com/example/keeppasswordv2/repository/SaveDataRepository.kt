package com.example.keeppasswordv2.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.keeppasswordv2.database.SaveData
import com.example.keeppasswordv2.database.SaveDataDao
import kotlinx.coroutines.*


class SaveDataRepository(private val saveDataDao: SaveDataDao) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    // val searchResults = MutableLiveData<List<SaveData>>()

    fun getAllData(name: String): LiveData<List<SaveData>> = saveDataDao.getAllDataByFK(name)


    fun insertData(data: SaveData){
        coroutineScope.launch (Dispatchers.IO){
            saveDataDao.insertData(data)
        }
    }

    fun deleteEmail(name: String){
        coroutineScope.launch(Dispatchers.IO){
            saveDataDao.deleteEmail(name)
        }
    }

    fun deleteById(id: Int){
        coroutineScope.launch(Dispatchers.IO){
            saveDataDao.deleteById(id)
        }
    }

    fun findDataByLogIn(name: String){
        coroutineScope.launch (Dispatchers.IO){
            saveDataDao.getAllDataByFK(name)
        }
    }
    /*
    fun findDataByLogIn(name: String){
        coroutineScope.launch(Dispatchers.IO){
            searchResults.value = asyncFind(name).await()
        }
    }

    private fun asyncFind(name: String): Deferred<LiveData<List<SaveData>>?> =
        coroutineScope.async(Dispatchers.IO){
            return@async saveDataDao.getAllDataByFK(name)
        }
    */
}