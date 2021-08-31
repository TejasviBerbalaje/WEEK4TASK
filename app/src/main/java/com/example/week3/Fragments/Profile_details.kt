package com.example.week3.Fragments

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
import com.example.week3.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Profile_details.newInstance] factory method to
 * create an instance of this fragment.
 */
class Profile_details : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var homebutton:Button
    lateinit var sharebutton: ImageButton
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



        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_profile_details, container, false)
        homebutton=view.findViewById(R.id.HomeButton)
        sharebutton = view.findViewById(R.id.ShareButton)

homebutton.setOnClickListener {


    fragmentManager?.beginTransaction()?.replace(R.id.fragment_layout,weeek3_Recyclerview())?.commit()
}

        val nameResult = view.findViewById<TextView>(R.id.resultName)
        val resultEmail = view.findViewById<TextView>(R.id.resultEmail)
        val resultRadio = view.findViewById<TextView>(R.id.resultRadio)
        val resultDob =view.findViewById<TextView>(R.id.resultDob)
        val resultTime=view.findViewById<TextView>(R.id.resultTime)
        val resultAge=view.findViewById<TextView>(R.id.resultAge)


//for getting dat from main activity
        val imageView: ImageView = view.findViewById(R.id.profileImg)


        val name = this.arguments?.get("Name")
        val email = this.arguments?.get("Email")
        val age = this.arguments?.get("Age")
        val gender = this.arguments?.get("Gender")
        val date = this.arguments?.get("Dob")
        val time=this.arguments?.get("Time")
        val resId: Int = requireArguments().getInt("MyImg")


       nameResult.text = "Name:$name"
        resultEmail.text = "Email:$email"
        resultAge.text = "Age:$age"
        resultRadio.text = "Gender:$gender"
        resultDob.text="Dob:$date"
        resultTime.text="Time:$time"
        imageView.setImageResource(resId)

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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Profile_details.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Profile_details().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}