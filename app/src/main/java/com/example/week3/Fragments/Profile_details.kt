package com.example.week3.Fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.week3.MainActivity
import com.example.week3.MainactivityViewmodel
import com.example.week3.R



class Profile_details : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var homebutton:Button
    lateinit var sharebutton: ImageButton

    private lateinit var viewModel: MainactivityViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        viewModel = ViewModelProviders.of(this).get(MainactivityViewmodel::class.java)
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_profile_details, container, false)
        homebutton=view.findViewById(R.id.HomeButton)
        sharebutton = view.findViewById(R.id.ShareButton)

homebutton.setOnClickListener {
    val intent=Intent(requireContext(), MainActivity::class.java)
    startActivity(intent)
}

        val nameResult = view.findViewById<TextView>(R.id.resultName)
        val resultEmail = view.findViewById<TextView>(R.id.resultEmail)
        val resultRadio = view.findViewById<TextView>(R.id.resultRadio)
        val resultDob =view.findViewById<TextView>(R.id.resultDob)
        val resultTime=view.findViewById<TextView>(R.id.resultTime)
        val resultAge=view.findViewById<TextView>(R.id.resultAge)


//for getting dat from main activity
        val imageView: ImageView = view.findViewById(R.id.profileImg)
val userDetails=viewModel.getAllUsers()
//
//        val name = this.arguments?.get("Name")
//        val email = this.arguments?.get("Email")
//        val age = this.arguments?.get("Age")
//        val gender = this.arguments?.get("Gender")
//        val date = this.arguments?.get("Dob")
//        val time=this.arguments?.get("Time")
//        val resId: Int = requireArguments().getInt("MyImg")
        viewModel.getAllUserObserver().observe(viewLifecycleOwner, Observer { it ->
            nameResult.text = "Name:"+ it[0].name
                    resultEmail.text = "Email:"+it[0].email
//            resultAge.text = "Age:"+it[0].age
////            resultRadio.text = "Gender:"+it[0].gender
//            resultDob.text="Dob:"+it[0].dob
//            resultTime.text="Time:"+it[0].time
//            imageView.setImageResource(resId)
        })



//share butoon
        sharebutton.setOnClickListener {
            val message = nameResult.text.toString()
            val emailmessage = resultEmail.text.toString()
            val agemessage = resultAge.text.toString()
            val radiomessage = resultRadio.text.toString()
            val dobmessage =resultDob.text.toString()
            val Timemessage =resultTime.text.toString()

            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,message+"\n"+emailmessage+"\n"+radiomessage+"\n"+dobmessage+"\n"+Timemessage +"\n"+agemessage)
            intent.type = "text/plain"

            startActivity(Intent.createChooser(intent, "Share via :"))

        }
        return view
    }


}