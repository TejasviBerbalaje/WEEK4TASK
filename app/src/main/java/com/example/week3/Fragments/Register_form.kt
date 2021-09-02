package com.example.week3.Fragments


import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.example.week3.MainActivity
import com.example.week3.R
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class Register_form : Fragment() {
    private var param1: String? = null
    private var param2: String? = null


    private lateinit var textInputFull_Name: TextInputLayout
    private lateinit var textInputReg_Email: TextInputLayout
    private lateinit var textInputReg_age: TextInputLayout
    private lateinit var textInputGender: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioButton: RadioButton
    private lateinit var radioButton2: RadioButton
    private lateinit var checkBox: CheckBox
    private lateinit var regImg: ImageView
    private lateinit var btnValidate: Button
    private lateinit var DateOfBirth: TextView
    private lateinit var btnDateOfBirth: Button
    private lateinit var btntimePicker: Button
    private lateinit var timePicker: TextView
    var formatdate = SimpleDateFormat("dd MMMM yyyy", Locale.US)
    var formattime = SimpleDateFormat("HH:mm aa",Locale.US)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)


        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register_form, container, false)


        textInputFull_Name = view.findViewById(R.id.full_Name)
        textInputReg_Email = view.findViewById(R.id.register_Email)
        textInputReg_age = view.findViewById(R.id.age)
        textInputGender = view.findViewById(R.id.textView3)
        radioGroup = view.findViewById(R.id.radio_Group)
        radioButton = view.findViewById(R.id.radioButton)
        radioButton2 = view.findViewById(R.id.radioButton2)
        checkBox = view.findViewById(R.id.checkBox)
        regImg = view.findViewById(R.id.regImage)
        btnValidate = view.findViewById(R.id.btnValidate)
        timePicker = view.findViewById(R.id.timePicker)
        DateOfBirth = view.findViewById(R.id.DateOfBirth)
        btnDateOfBirth = view.findViewById(R.id.btnDateOfBirth)
        btntimePicker = view.findViewById(R.id.btntimePicker)





        btnValidate.setOnClickListener {


    var name = textInputFull_Name.editText?.text.toString()
    val email = textInputReg_Email.editText?.text.toString()
    val age = textInputReg_age.editText?.text.toString()
    val date = DateOfBirth.text.toString()
    val time = timePicker.text.toString()
    val radioGroup = radioGroup.checkedRadioButtonId
    val male = view.findViewById<RadioButton>(radioGroup)

    if (!validateName() or !validateEmail() or !validateRadiogroup() or !validatecheckbox() or !validateTimpepicker() or !validateage() or !validateDateofBirth()) {

    }
  else {
        Toast.makeText(this.requireContext(), "Registartion successfull", Toast.LENGTH_SHORT).show()
val bundle=Bundle()
        bundle.putString("Name", name)
        bundle.putString("Email", email)
        bundle.putString("Age", age)
        bundle.putString("Gender", male.text.toString())
        bundle.putString("Dob", date)
        bundle.putString("Time", time)
        bundle.putInt("MyImg", R.drawable.person_icon_blue)



        val profiledetais=Profile_details()
        profiledetais.arguments=bundle
//       setFragmentResult(RESULT_OK.toString(),bundle)
         fragmentManager?.beginTransaction()?.replace(R.id.fragment_layout,profiledetais)?.commit()


    }
}



        btnDateOfBirth.setOnClickListener(View.OnClickListener {
            val getdate = Calendar.getInstance()
            val datePicker =
                DatePickerDialog(
                    this.requireContext(),
                     { datePicker, i, i2, i3 ->
                        val selectdate = Calendar.getInstance()
                        selectdate.set(Calendar.YEAR, i)
                        selectdate.set(Calendar.MONTH, i2)
                        selectdate.set(Calendar.DAY_OF_MONTH, i3)
                        DateOfBirth.text = formatdate.format(selectdate.time)
                    },
                    getdate.get(Calendar.YEAR),
                    getdate.get(Calendar.MONTH),
                    getdate.get(Calendar.DAY_OF_MONTH)
                )
            datePicker.show()

        })

        btntimePicker.setOnClickListener(View.OnClickListener {
            val gettime = Calendar.getInstance()
            val timepicker = TimePickerDialog(this.requireContext(),
                 { time, i, i2 ->
                    val selecttime = Calendar.getInstance()
                    selecttime.set(Calendar.HOUR_OF_DAY, i)
                    selecttime.set(Calendar.MINUTE, i2)
                    timePicker.text = formattime.format(selecttime.time)
                }, gettime.get(Calendar.HOUR_OF_DAY), gettime.get(Calendar.MINUTE), false
            )
            timepicker.show()
        })

return view

    }




    fun validateName(): Boolean {
        val name: String = textInputFull_Name.editText!!.text.toString()
        return if (name.isEmpty()) {
            textInputFull_Name.error = "Field can not be empty"
            false
        } else if (name.length <= 5) {
            textInputFull_Name.error = "Username is too Short"
            false
        } else if (name.length >= 30) {
            textInputFull_Name.error = "Username is too long"
            false
        } else {
            textInputFull_Name.error = null
            true
        }
    }

    fun validateEmail(): Boolean {
        val emailInput1 = textInputReg_Email.editText!!.text.toString().trim()
        return if (emailInput1.isEmpty()) {
            textInputReg_Email.error = "Field can not be empty"
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput1).matches()) {
            textInputReg_Email.error = "Please enter valid Email"
            false
        } else {
            textInputReg_Email.error = null
            true
        }
    }

    fun validateage(): Boolean {
        val age = textInputReg_age.editText!!.text.toString().trim()
//        val ageval = "/^[1-9]?[0-9]{1}\$|^100\$/"
        return if (age.isEmpty()) {
            textInputReg_age.error = "Field can not be empty"
            false
        } else {
            textInputReg_age.error = null
            true
        }
    }

    fun validateRadiogroup(): Boolean {
        return if (radioGroup.checkedRadioButtonId == -1) {
            textInputGender.error = "Please select the gender"
            false
        } else {
            textInputGender.error = null
            true
        }
    }

    fun validateDateofBirth(): Boolean {
        val name: String = DateOfBirth.text.toString()
        return if (name.isEmpty()) {
            DateOfBirth.error = "Field can not be empty"
            false
        } else {
            DateOfBirth.error = null
            true
        }
    }

    fun validateTimpepicker(): Boolean {
        val name: String = timePicker.text.toString()
        return if (name.isEmpty()) {
            timePicker.error = "Field can not be empty"
            false
        } else {
            timePicker.error = null
            true
        }
    }

    fun validatecheckbox(): Boolean {
        return if (!checkBox.isChecked) {
            checkBox.error = "Please accept the terms and condition"
            false
        } else {
            checkBox.error = null
            true
        }
    }



    // Inflate the layout for this fragment



//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment Register_form.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            Register_form().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}