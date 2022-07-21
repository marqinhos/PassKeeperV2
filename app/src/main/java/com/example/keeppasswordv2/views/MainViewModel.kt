package com.example.keeppasswordv2.views

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.keeppasswordv2.database.SaveData
import com.example.keeppasswordv2.database.SaveDataRoomDatabase
import com.example.keeppasswordv2.database.User
import com.example.keeppasswordv2.database.UserRoomDatabase
import com.example.keeppasswordv2.repository.SaveDataRepository
import com.example.keeppasswordv2.repository.UserRepository


class MainViewModel(application: Application): ViewModel() {

    val allUsers: LiveData<List<String>>
    private val repository: UserRepository
    private val repository2: SaveDataRepository
    val searchResults: MutableLiveData<List<User>>

    init {
        val userDB = UserRoomDatabase.getInstance(application)
        val userDao = userDB.userDao()
        repository = UserRepository(userDao)
        allUsers = repository.allUsersName
        searchResults = repository.searchResults

        val saveDataDB = SaveDataRoomDatabase.getInstance(application)
        val saveDataDao = saveDataDB.saveDataDao()
        repository2 = SaveDataRepository(saveDataDao)

    }

    fun insertUser(user: User){
        repository.insertUser(user)
    }

    fun deleteUser(name: String){
        repository.deleteUser(name)
    }

    fun findUser(name: String){
        repository.findUser(name)
    }


    fun insertData(data: SaveData){
        repository2.insertData(data)
    }

    fun deleteEmail(email: String){
        repository2.deleteEmail(email)
    }

    fun deleteById(id: Int){
        repository2.deleteById(id)
    }

    fun findDataByFK(name: String){
        repository2.findDataByLogIn(name)
    }

    fun getAllData(name: String): LiveData<List<SaveData>> =
        repository2.getAllData(name)

    fun getPasswordByName(name: String): LiveData<List<User>> =
        repository.getPasswordByName(name)


}