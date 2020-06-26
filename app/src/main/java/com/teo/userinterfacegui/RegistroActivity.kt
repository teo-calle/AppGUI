package com.teo.userinterfacegui

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_registro.*
import java.util.*

class RegistroActivity : AppCompatActivity() {

    private lateinit var fecha: String
    private var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

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
            //Validacion campo nombre
            if (nombre.isEmpty())
            {
                editText_Nombre.setError("Este campo no puede estar vacio")
            }
            //
            if(contrasena == rep_contrasena){

            tview_resultado.text = "Nombre : $nombre \nCedula : $cedula \n Correo : $correo \n Genero : $genero \n Pasatiempos : $pasatiempos \n Ciudad de Nacimiento : $ciudadNacimiento \n" +
                    " Fecha de Nacimiento : $fecha"

            }else{tview_resultado.text = getString(R.string.error_contrasenas)}
        }

    }

    //Esta parte será util para crear otras actividades teniendo en cuenta que hay que crear un menu_overflow
    //Se crea para dar funcionalidad a la segunda actividad(menu) opcion #1
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_overflow, menu)
        return true
    }

    //Se crea para dar funcionalidad a la segunda actividad opcion #2
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_actividad2){
            Log.d("Menu", "Presionado el menu 1")
            //Me manda a la actividad login
            val intent = Intent(this, LoginActivity::class.java)
            //Con esta linea se pasa el nombre a la actividad login PDT asi es como se mandan datos de una actividad a otra
            intent.putExtra("nombre", editText_Nombre.text.toString())
            //startActivity(intent)
            setResult(Activity.RESULT_OK, intent)
            finish() //Con esto se mata la actividad asi presione lo que sea, estando en login activity

        }else{

            Log.d("Menu", "Presionado el menu 2")
            //Me manda a la actividad Splash
            val intent = Intent(this, SplashActivity::class.java)
            startActivity(intent)

        }
            return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1234 && resultCode == Activity.RESULT_OK){
            val nombre = data?.extras?.getString("nombre")
            Toast.makeText(this,"Nombre: $nombre", Toast.LENGTH_SHORT).show() // Mostrará una especie de notificación

        }
    }

   override fun onStart() {
        super.onStart()
        Log.d("onStart)", "ok")
    }

    override fun onResume() {
        super.onResume()
        Log.d("onResume)", "ok")
    }

    override fun onPause() {
        super.onPause()
        Log.d("onPause)", "ok")
    }

    override fun onStop() {
        super.onStop()
        Log.d("onStop)", "ok")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("onRestart", "ok")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("onDestroy", "ok")
    }
}