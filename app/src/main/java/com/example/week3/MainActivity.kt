package com.example.week3


import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.*
import com.example.week3.DataBaseFiles.UserEntity
import com.example.week3.Fragments.Register_form
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cardview_layout.*
import kotlinx.android.synthetic.main.fragment_profile_details.*
import kotlinx.android.synthetic.main.fragment_register_form.*



class MainActivity : AppCompatActivity(), RecyclerAdapter.RowClickListener {
private lateinit var container: FrameLayout
   private lateinit var recyclerViewAdapter: RecyclerAdapter
   private lateinit var viewModel: MainactivityViewmodel
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fab: View = findViewById(R.id.fab)
        container=findViewById(R.id.container)


//        supportFragmentManager.beginTransaction().replace(R.id.fragment_layout,weeek3_Recyclerview()).commit()
val connectionmanager= this.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activenetwrok:NetworkInfo?=connectionmanager.activeNetworkInfo
        val isconnected:Boolean=activenetwrok?.isConnectedOrConnecting==true
        if(isconnected){
            Toast.makeText(this, "Connected to the internet", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "You are offline!!!!", Toast.LENGTH_SHORT).show()
        }


fab.setOnClickListener {
    val registerform=Register_form()
    supportFragmentManager.beginTransaction().replace(R.id.container,registerform).commit()
    fab.visibility = View.GONE
}


        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerViewAdapter = RecyclerAdapter(this@MainActivity)
            adapter = recyclerViewAdapter
            val divider = DividerItemDecoration(applicationContext, VERTICAL)
            addItemDecoration(divider)
        }

        viewModel = ViewModelProviders.of(this).get(MainactivityViewmodel::class.java)
        viewModel.getAllUserObserver().observe(this,Observer { it ->
            recyclerViewAdapter.setListData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })
val bundle=Bundle()
        val name  = bundle.getString("Name").toString()
        val email  = bundle.getString("Email").toString()
            val user = UserEntity(0, name, email)
            viewModel.insertUserInfo(user)



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

    override fun onDeleteUserClickListener(user: UserEntity) {
        viewModel.deletUserInfo(user)
    }
}




