package com.example.week3

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.week3.DataBaseFiles.RoomAppdb
import com.example.week3.DataBaseFiles.UserEntity

class MainactivityViewmodel(app:Application):AndroidViewModel(app) {
 var allUser:MutableLiveData<List<UserEntity>>
    init {
allUser= MutableLiveData()
        this.getAllUsers()
    }

    fun getAllUserObserver():MutableLiveData<List<UserEntity>>{
        return allUser
    }


    fun getAllUsers(){
       val userDao= RoomAppdb.getAppDatabase(getApplication())?.userDao()
        val list=userDao?.getAllUserinfo()
        allUser.postValue(list)

    }

    fun  insertUserInfo(entity: UserEntity){
       val userDao= RoomAppdb.getAppDatabase(getApplication())?.userDao()
        userDao?.insertUser(entity)
        getAllUsers()
    }
    fun deletUserInfo(entity: UserEntity){
        val userDao= RoomAppdb.getAppDatabase(getApplication())?.userDao()
        userDao?.deletUser(entity)
        getAllUsers()
    }

}