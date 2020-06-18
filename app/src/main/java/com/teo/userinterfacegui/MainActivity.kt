package com.teo.userinterfacegui

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var fecha: String
    private var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dateSetListener = object: DatePickerDialog.OnDateSetListener{
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val format = "MM/dd/yyyy"
                val simpleDateFormat = SimpleDateFormat(format, Locale.US)
                fecha = simpleDateFormat.format(cal.time).toString()
                tView_fecha_nacimiento.text = fecha

            }
        }

        imgBtn_calendario.setOnClickListener{
            DatePickerDialog(this,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }
       // val btn_guardar : Button = findViewById(R.id.btn_guardar)
       // val edt_nombre : EditText = findViewById(R.id.editText_Nombre)

        btn_guardar.setOnClickListener {
            val nombre = editText_Nombre.text.toString()
            val cedula = editTextNumber_cedula.text.toString()
            val correo = editText_Correo.text.toString()
            val contrasena = editTextPass_contrasena.text.toString()
            val rep_contrasena = editTextPass_repcontrsena.text.toString()
            val genero = if (rB_masculino.isChecked) "Masculino" else "Femenino"
            var pasatiempos = ""
            val ciudadNacimiento = spnr_ciudad_nacimiento.selectedItem.toString()

            if(chB_cine.isChecked) pasatiempos = "$pasatiempos cine"
            if(chB_ps4.isChecked) pasatiempos = "$pasatiempos sp4"
            if(chB_series.isChecked) pasatiempos = "$pasatiempos series"

            if(contrasena == rep_contrasena){

            tview_resultado.text = "Nombre : $nombre \nCedula : $cedula \n Correo : $correo \n Genero : $genero \n Pasatiempos : $pasatiempos \n Ciudad de Nacimiento : $ciudadNacimiento \n" +
                    " Fecha de Nacimiento : $fecha"

            }else{tview_resultado.text = getString(R.string.error_contrasenas)}
        }

    }

}