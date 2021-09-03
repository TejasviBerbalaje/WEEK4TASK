package com.example.week3.DataBaseFiles

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [UserEntity::class], version = 2)
abstract class RoomAppdb:RoomDatabase() {
 abstract fun userDao():UserDao?


 companion object{
     private var INSTANCE: RoomAppdb?=null
     val migration_1_2: Migration = object: Migration(1, 2) {
         override fun migrate(database: SupportSQLiteDatabase) {
             database.execSQL("ALTER TABLE userinfo ADD COLUMN phone TEXT DEFAULT ''")
         }
     }
     fun getAppDatabase(context: Context):RoomAppdb? {
         if(INSTANCE==null){

             INSTANCE=Room.databaseBuilder<RoomAppdb>(
                 context.applicationContext,
                 RoomAppdb::class.java,
                 "AppDb",
             ).addMigrations(migration_1_2).allowMainThreadQueries().build()
         }
         return INSTANCE
     }


 }


}