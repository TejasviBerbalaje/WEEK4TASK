package com.example.week3


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.week3.DataBaseFiles.UserEntity
import com.example.week3.Fragments.Profile_details



class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    var items = ArrayList<UserEntity>()

    fun setListData(data:ArrayList<UserEntity> ){

        this.items=data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.MyViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.cardview_layout,parent,false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.MyViewHolder, position: Int) {


        holder.bind(items[position])

    }


    class MyViewHolder(view:View): RecyclerView.ViewHolder(view){

//        val personname=view.PersonName
//        val personemail=view.PersonEmailId
//        val delete=view.DeleteButton

        fun bind(data: UserEntity){

//            personname.text=data.name
//            personemail.text=data.email
//
//            delete.set
        }
    }


   
}