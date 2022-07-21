package com.example.keeppasswordv2.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.keeppasswordv2.database.SaveData
import com.example.keeppasswordv2.database.User
import com.example.keeppasswordv2.database.UserDao
import kotlinx.coroutines.*


class UserRepository(private val userDao: UserDao) {

    val allUsersName: LiveData<List<String>> = userDao.getAllUsersNames()
    val searchResults = MutableLiveData<List<User>>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun getPasswordByName(name: String): LiveData<List<User>> = userDao.getPasswordByName(name)

    fun insertUser(newUser: User){
        coroutineScope.launch(Dispatchers.IO){
            userDao.insertUser(newUser)
        }
    }

    fun deleteUser(name: String){
        coroutineScope.launch (Dispatchers.IO){
            userDao.deleteUser(name)
        }
    }

    fun findUser(name: String){
        coroutineScope.launch (Dispatchers.IO){
            searchResults.value = asyncFind(name).await()
        }
    }

    private fun asyncFind(name: String): Deferred<List<User>?> =
        coroutineScope.async(Dispatchers.IO){
            return@async userDao.findUser(name)
        }



}


