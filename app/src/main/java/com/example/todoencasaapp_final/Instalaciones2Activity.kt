package com.example.todoencasaapp_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_instalaciones2.*
import kotlinx.android.synthetic.main.fragment_inicio.*
import kotlinx.android.synthetic.main.fragment_inicio.marcador

class Instalaciones2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instalaciones2)

        marcador.setOnClickListener{
            onBackPressed()
        }

        certificado2.setOnClickListener {
            startActivity(Intent(this,CertificadoActivity::class.java))
        }

        contratar.setOnClickListener{
            startActivity(Intent(this,ContratarActivity::class.java))
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}