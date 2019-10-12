package com.example.todoencasaapp_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_solicitud.*
import kotlinx.android.synthetic.main.fragment_inicio.*
import kotlinx.android.synthetic.main.fragment_inicio.marcador

class SolicitudActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solicitud)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        cancelar.setOnClickListener {
            startActivity(Intent(this,Solicitud2Activity::class.java))
            Toast.makeText(this,"Solicitud cancelada", Toast.LENGTH_SHORT).show()
            finish()
        }

        aceptar.setOnClickListener {
            startActivity(Intent(this,Solicitud3Activity::class.java))
            Toast.makeText(this,"Solicitud aceptada",Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
