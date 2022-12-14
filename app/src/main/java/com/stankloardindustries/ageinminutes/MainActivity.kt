package com.stankloardindustries.ageinminutes

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import java.util.*
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener{ view ->
            clickDatePicker(view)
            Toast.makeText(this, "Pick a date", Toast.LENGTH_LONG).show()
        }
    }

    fun clickDatePicker(view: View){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                view, SelectedYear, SelectedMonth, SelectedDayOfMonth ->

            val SelectedDate = "$SelectedDayOfMonth/${SelectedMonth+1}/$SelectedYear"

            textView4.setText(SelectedDate)

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(SelectedDate)
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

            val selectedTimeInMin = theDate!!.time
            val currentTimeInMin = currentDate!!.time
            val differenceInMinutes = currentTimeInMin - selectedTimeInMin

            textView6.setText((differenceInMinutes/60000).toString())
            textView8.setText((differenceInMinutes/3600000).toString())
            textView10.setText((differenceInMinutes/86400000).toString())

        }, year, month, day)
        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()

    }
}