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
import androidx.lifecycle.ViewModelProviders
import com.example.week3.DataBaseFiles.UserEntity
import com.example.week3.MainactivityViewmodel
import com.example.week3.R

import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*




class Register_form : Fragment() {



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
    private lateinit var viewModel: MainactivityViewmodel
    var formatdate = SimpleDateFormat("dd MMMM yyyy", Locale.US)
    var formattime = SimpleDateFormat("HH:mm aa",Locale.US)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register_form, container, false)

        viewModel = ViewModelProviders.of(this).get(MainactivityViewmodel::class.java)
        textInputFull_Name = view.findViewById(R.id.full_Name)
        textInputReg_Email = view.findViewById(R.id.register_Email)
        textInputReg_age = view.findViewById(R.id.age)
        textInputGender=view.findViewById(R.id.textView3)
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


    val name = textInputFull_Name.editText?.text.toString()
    val email = textInputReg_Email.editText?.text.toString()
    val age = textInputReg_age.editText?.text.toString()
    val date= DateOfBirth.text.toString()
    val time = timePicker.text.toString()
//    val radioGroup = radioGroup.checkedRadioButtonId
//    val male = view.findViewById<RadioButton>(radioGroup)


    if (!validateName() or !validateEmail() or !validateRadiogroup() or !validatecheckbox() or !validateTimpepicker() or !validateage() or !validateDateofBirth()) {

    }
  else {
        Toast.makeText(this.requireContext(), "Registartion successfull", Toast.LENGTH_SHORT).show()

        viewModel.insertUserInfo(UserEntity(0,name,email))


        val profiledetais=Profile_details()
         fragmentManager?.beginTransaction()?.replace(R.id.container,profiledetais)?.commit()


    }
}



        btnDateOfBirth.setOnClickListener(View.OnClickListener {
            val getdate = Calendar.getInstance()
            val datePicker =
                DatePickerDialog(
                    this.requireContext(),
                     { _, i, i2, i3 ->
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
            datePicker.datePicker.maxDate = System.currentTimeMillis()
            datePicker.show()

        })

        btntimePicker.setOnClickListener(View.OnClickListener {
            val gettime = Calendar.getInstance()
            val timepicker = TimePickerDialog(this.requireContext(),
                 { _, i, i2 ->
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
        return when {
            name.isEmpty() -> {
                textInputFull_Name.error = "Field can not be empty"
                false
            }
            name.length <= 5 -> {
                textInputFull_Name.error = "Username is too Short"
                false
            }
            name.length >= 30 -> {
                textInputFull_Name.error = "Username is too long"
                false
            }
            else -> {
                textInputFull_Name.error = null
                true
            }
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
        return if (age.isEmpty()) {
            textInputReg_age.error = "Field can not be empty"
            false
        } else {
            textInputReg_age.error = null
            true
        }
    }

    fun validateRadiogroup(): Boolean {
         if (radioGroup.checkedRadioButtonId == -1) {
            textInputGender.error = "Please select the gender"
            return false
        } else {
            textInputGender.error = null
            return true
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




}






