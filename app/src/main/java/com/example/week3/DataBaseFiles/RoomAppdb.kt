package com.example.week3.DataBaseFiles

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [UserEntity::class], version = 1)
abstract class RoomAppdb:RoomDatabase() {
 abstract fun userDao():UserDao?


 companion object{
     private var INSTANCE: RoomAppdb?=null

     fun getAppDatabase(context: Context):RoomAppdb? {
         if(INSTANCE==null){

             INSTANCE=Room.databaseBuilder<RoomAppdb>(
                 context.applicationContext,RoomAppdb::class.java,"AppDb"
             ).allowMainThreadQueries().build()
         }
         return INSTANCE
     }
 }


}