package com.example.week3

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.NetworkSpecifier
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.week3.Fragments.Recyclerview
import com.example.week3.Fragments.Register_form


class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_layout,Recyclerview()).commit()
val connectionmanager= this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activenetwrok:NetworkInfo?=connectionmanager.activeNetworkInfo
        val isconnected:Boolean=activenetwrok?.isConnectedOrConnecting==true
        if(isconnected){
            Toast.makeText(this, "Connected to the internet", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "You are offline!!!!", Toast.LENGTH_SHORT).show()
        }








    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_Logout -> {
                val builder1 = AlertDialog.Builder(this)
                builder1.setMessage("Are you sure you want to logout of this app?")
                builder1.setCancelable(true)
                builder1.setPositiveButton(
                    "Yes"
                ) { dialog,which ->finishAffinity() }
                builder1.setNegativeButton(
                    "No"
                ) { dialog, id -> dialog.cancel() }
                val alert11 = builder1.create()
                alert11.show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}