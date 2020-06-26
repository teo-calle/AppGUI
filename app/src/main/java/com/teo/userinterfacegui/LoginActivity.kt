package com.teo.userinterfacegui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Para recibir los datos de Registro(El dato que se envía desde allá)
        var datosRecibidos = intent.extras
        var nombre = datosRecibidos?.getString("nombre") //Muestra en pantalla lo que se envía desde registro.
        Toast.makeText(this,"Nombre: $nombre", Toast.LENGTH_SHORT).show() // Mostrará una especie de notificación

        button.setOnClickListener{
            val intent = Intent(this, RegistroActivity::class.java)
            startActivityForResult(intent,1234) //Llamar al registro indicando que falta informacion
           // onBackPressed()

        }

        button3.setOnClickListener{
            val intent = Intent(this, SplashActivity::class.java)
            startActivity(intent)
        }
    }
}