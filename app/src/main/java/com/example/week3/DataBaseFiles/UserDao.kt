package com.example.week3.DataBaseFiles

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UserDao {

@Query("SELECT * FROM userinfo ORDER BY id DESC")
fun getAllUserinfo():List<UserEntity>?



@Insert
fun insertUser(user:UserEntity?)


    @Delete
    fun deletUser(user:UserEntity?)


}