package com.example.week3.DataBaseFiles

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "userinfo")

data class UserEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id:Int=0,
    @ColumnInfo(name = "name")val name:String,
    @ColumnInfo(name = "email")val email:String,
//    @ColumnInfo(name = "age")val age:String,
//    @ColumnInfo(name = "gender")val gender:String,
//    @ColumnInfo(name = "dob")val dob:String,
//    @ColumnInfo(name = "time")val time:String,

)