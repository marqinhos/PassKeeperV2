package com.example.keeppasswordv2.database

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import org.jetbrains.annotations.NotNull

@Entity(tableName = "saveData_table")
class SaveData {


    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name = "saveData_id")
    var id: Int = 0

    @ColumnInfo(name = "saveLoginFK")
    var saveLoginFK: String = ""

    @ColumnInfo(name = "saveEmail")
    var saveEmail: String = ""

    @ColumnInfo(name = "saveUsername")
    var saveUsername: String = ""

    @ColumnInfo(name = "savePassword")
    var savePassword: String = ""

    @ColumnInfo(name = "saveDescription")
    var saveDescription: String = ""



    constructor(){}

    constructor(
        saveEmail: String, saveUsername: String, savePassword: String, saveDescription: String,
        saveLoginFK: String
    ){
        this.id = id
        this.saveEmail = saveEmail
        this.savePassword = savePassword
        this.saveUsername = saveUsername
        this.saveDescription = saveDescription
        this.saveLoginFK = saveLoginFK

    }


}

// ,
//    foreignKeys = [
//        ForeignKey(entity = User::class,
//            parentColumns = ["userName"],
//            childColumns = ["saveLoginFK"],
//            onDelete = CASCADE
//        )
//    ],
//    indices = [Index(value = ["saveLoginFK"], unique = true)]