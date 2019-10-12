package com.example.todoencasaapp_final

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_antioquia.*
import kotlinx.android.synthetic.main.fragment_inicio.view.*

class AntioquiaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_antioquia)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        medellin.setOnClickListener {
            //---------------- Envio de datos ----------------
            var intent = Intent(this, DepartamentosActivity::class.java)
            intent.putExtra("antioquia", "Medellín")
            setResult(Activity.RESULT_OK,intent)
            finish()
            // ------------------------------------------------
        }
    }
}
