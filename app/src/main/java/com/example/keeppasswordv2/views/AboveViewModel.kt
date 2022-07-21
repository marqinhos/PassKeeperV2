package com.example.keeppasswordv2.views

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.keeppasswordv2.database.SaveData
import com.example.keeppasswordv2.database.SaveDataRoomDatabase
import com.example.keeppasswordv2.repository.SaveDataRepository

class AboveViewModel(application: Application): ViewModel() {

    private val repository: SaveDataRepository

    // val allDataByFK: LiveData<List<SaveData>>
    // val searchResults: MutableLiveData<List<User>>

    init {
        val saveDataDB = SaveDataRoomDatabase.getInstance(application)
        val saveDataDao = saveDataDB.saveDataDao()
        repository = SaveDataRepository(saveDataDao)

    }
    fun insertData(data: SaveData){
        repository.insertData(data)
    }

    fun deleteEmail(email: String){
        repository.deleteEmail(email)
    }

    fun findDataByFK(name: String){
        repository.findDataByLogIn(name)
    }

    fun getAllData(name: String): LiveData<List<SaveData>> = repository.getAllData(name)

}
