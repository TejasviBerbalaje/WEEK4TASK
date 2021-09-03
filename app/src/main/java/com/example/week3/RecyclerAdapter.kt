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
//        val tvAge=view.PersonAge
//        val tvTime=view.time
//        val tvGender=view.PersonGender
//        val tvdob=view.PersonDob



        val deleteUserID = view.DeleteButton

        fun bind(data: UserEntity) {
            tvName.text = data.name

            tvEmail.text = data.email

//            tvAge.text = data.age
//
//            tvTime.text=data.time
////             tvGender.text=data.gender
//            tvdob.text=data.dob

            deleteUserID.setOnClickListener {
                    view ->
                val builder1 = android.app.AlertDialog.Builder(view.context)
                builder1.setMessage("Are you sure want to delete?")
                builder1.setCancelable(true)
                builder1.setPositiveButton("Yes"){dialogInterface, which ->
                    listener.onDeleteUserClickListener(data)
                }
                //performing cancel action
                builder1.setNeutralButton("Cancel"){dialogInterface , which ->

                }
                //performing negative action

                val alert11 = builder1.create()
                alert11.show()
                true

            }
        }
    }

    interface RowClickListener {
        fun onDeleteUserClickListener(user: UserEntity)
    }


    }