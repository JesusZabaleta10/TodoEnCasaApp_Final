package com.example.todoencasaapp_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_narino.*

class NarinoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_narino)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

            ipiales.setOnClickListener {
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
                val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
                val ciudad = "Ipiales"
                ref.child("ciudad").setValue(ciudad)
                startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            }
            cruz.setOnClickListener {
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
                val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
                val ciudad = "La cruz"
                ref.child("ciudad").setValue(ciudad)
                startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            }
            union.setOnClickListener {
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
                val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
                val ciudad = "La unión"
                ref.child("ciudad").setValue(ciudad)
                startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            }
            pasto.setOnClickListener {
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
                val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
                val ciudad = "Pasto"
                ref.child("ciudad").setValue(ciudad)
                startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            }
            santacruz.setOnClickListener {
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
                val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
                val ciudad = "Santacruz"
                ref.child("ciudad").setValue(ciudad)
                startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            }
            tumaco.setOnClickListener {
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
                val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
                val ciudad = "Tumaco"
                ref.child("ciudad").setValue(ciudad)
                startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            }
    }
}
