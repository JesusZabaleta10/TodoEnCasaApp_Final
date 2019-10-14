package com.example.todoencasaapp_final

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_departamentos.*

class DepartamentosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_departamentos)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        antioquia.setOnClickListener {
            startActivity(Intent(this,AntioquiaActivity::class.java))
        }

        atlantico.setOnClickListener {
            startActivity(Intent(this,AtlanticoActivity::class.java))
        }

        cordoba.setOnClickListener {
            startActivity(Intent(this,CordobaActivity::class.java))
        }

        cundinamarca.setOnClickListener {
            startActivity(Intent(this,CundinamarcaActivity::class.java))
        }

        nariño.setOnClickListener {
            startActivity(Intent(this,NarinoActivity::class.java))
        }

        valle.setOnClickListener {
            startActivity(Intent(this,ValleActivity::class.java))
        }
    }
}
