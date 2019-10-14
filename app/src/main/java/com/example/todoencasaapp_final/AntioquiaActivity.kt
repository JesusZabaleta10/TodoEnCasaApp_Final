package com.example.todoencasaapp_final

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_antioquia.*
import kotlinx.android.synthetic.main.fragment_inicio.view.*
import kotlinx.android.synthetic.main.fragment_perfil.*

class AntioquiaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_antioquia)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        bello.setOnClickListener {
            // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
            val ciudad = "Bello"
            ref.child("ciudad").setValue(ciudad)
            startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
            // -------- Añadir la ciudad del usuario a la base de datos --------------------------
        }

        envigado.setOnClickListener {
            // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
            val ciudad = "Envigado"
            ref.child("ciudad").setValue(ciudad)
            startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
            // -------- Añadir la ciudad del usuario a la base de datos --------------------------
        }

        girardota.setOnClickListener {
            // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
            val ciudad = "Girardota"
            ref.child("ciudad").setValue(ciudad)
            startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
            // -------- Añadir la ciudad del usuario a la base de datos --------------------------
        }

        itagui.setOnClickListener {
            // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
            val ciudad = "Itaguí"
            ref.child("ciudad").setValue(ciudad)
            startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
            // -------- Añadir la ciudad del usuario a la base de datos --------------------------
        }

        medellin.setOnClickListener {
            // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
            val ciudad = "Medellín"
            ref.child("ciudad").setValue(ciudad)
            startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
            // -------- Añadir la ciudad del usuario a la base de datos --------------------------
        }

        rionegro.setOnClickListener {
            // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
            val ciudad = "Rionegro"
            ref.child("ciudad").setValue(ciudad)
            startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
            // -------- Añadir la ciudad del usuario a la base de datos --------------------------
        }
    }
}
