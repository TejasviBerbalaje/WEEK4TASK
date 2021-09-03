package com.example.week3


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.week3.DataBaseFiles.UserEntity
import com.example.week3.Fragments.Profile_details
import kotlinx.android.synthetic.main.cardview_layout.view.*


class RecyclerAdapter(val listener: RowClickListener) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    lateinit var personname:TextView
    var items = ArrayList<UserEntity>()

    fun setListData(data:ArrayList<UserEntity> ){

        this.items=data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.MyViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.cardview_layout,parent,false)
        return MyViewHolder(v,listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.MyViewHolder, position: Int) {


        holder.bind(items[position])

    }


    class MyViewHolder(view: View, val listener: RowClickListener): RecyclerView.ViewHolder(view) {

        val tvName = view.PersonName
        val tvEmail = view.PersonEmailId

        val deleteUserID = view.DeleteButton

        fun bind(data: UserEntity) {
            tvName.text = data.name

            tvEmail.text = data.email


//            tvPhone.text = data.phone

            deleteUserID.setOnClickListener {
                listener.onDeleteUserClickListener(data)
            }
        }
    }

    interface RowClickListener {
        fun onDeleteUserClickListener(user: UserEntity)
    }


    }