package com.example.todoencasaapp_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_registrar.*
import kotlinx.android.synthetic.main.fragment_inicio.*
import kotlinx.android.synthetic.main.fragment_inicio.marcador
import kotlinx.android.synthetic.main.fragment_perfil.*
import kotlinx.android.synthetic.main.fragment_perfil.b_registrar

class RegistrarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)

        marcador.setOnClickListener{
            onBackPressed()
        }

        b_registrar.setOnClickListener{
            onBackPressed()
        }

        condiciones.setOnClickListener{
            startActivity(Intent(this,TerminosCondicionesActivity::class.java))
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}