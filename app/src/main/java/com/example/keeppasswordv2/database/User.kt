package com.example.keeppasswordv2.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class User {

    //
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "user_id")
    var id: Int = 0

    @ColumnInfo(name = "userName")
    var userName: String = ""

    @ColumnInfo(name = "userPassword")
    var userPassword: String = ""

    constructor(){}

    constructor(userName: String, userPassword: String){
        this.id = id
        this.userName = userName
        this.userPassword = userPassword

    }

}