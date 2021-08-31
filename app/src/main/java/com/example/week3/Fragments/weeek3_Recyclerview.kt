package com.example.week3.Fragments

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.week3.Listdatacard
import com.example.week3.MainActivity
import com.example.week3.R
import com.example.week3.RecyclerAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [weeek3_Recyclerview.newInstance] factory method to
 * create an instance of this fragment.
 */
class weeek3_Recyclerview : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var list: List<Listdatacard>? = null
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_weeek3__recyclerview, container, false)
        list = ArrayList<Listdatacard>()


        val fab: View = view.findViewById(R.id.fab)

        var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->


            if (result.resultCode == RESULT_OK) {

                var data = result.data
                // There are no request codes
                val name = data?.getStringExtra("Name").toString()
                val email = data?.getStringExtra("Email").toString()
                val gender = data?.getStringExtra("Gender").toString()
                val age = data?.getStringExtra("Age").toString()
                val dob = data?.getStringExtra("Dob").toString()
                val time = data?.getStringExtra("Time").toString()


                (list as ArrayList<Listdatacard>).add(Listdatacard(R.drawable.person_icon_blue,email,name,gender,time,age,dob))


                val recyclerView = view.findViewById<View>(R.id.recyclerView) as RecyclerView
                val adapter = RecyclerAdapter(list as ArrayList<Listdatacard>)
                recyclerView.setHasFixedSize(true)
                recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
                recyclerView.adapter = adapter

                val insertIndex = 2
                adapter.notifyItemInserted(insertIndex);
            }
        }
        fab.setOnClickListener { view ->
//            val intent =Intent()
        resultLauncher.run { fragmentManager?.beginTransaction()?.replace(R.id.fragment_layout,Register_form())?.commit() }



        }
        return view
    }

}


