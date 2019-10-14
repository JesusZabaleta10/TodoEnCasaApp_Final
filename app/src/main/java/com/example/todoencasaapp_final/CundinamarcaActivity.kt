package com.example.todoencasaapp_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_cundinamarca.*

class CundinamarcaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cundinamarca)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

            bogota.setOnClickListener {
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
                val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
                val ciudad = "Bogotá"
                ref.child("ciudad").setValue(ciudad)
                startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            }
            chia.setOnClickListener {
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
                val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
                val ciudad = "Chía"
                ref.child("ciudad").setValue(ciudad)
                startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            }
            facatativa.setOnClickListener {
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
                val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
                val ciudad = "Facatativá"
                ref.child("ciudad").setValue(ciudad)
                startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            }
            girardot.setOnClickListener {
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
                val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
                val ciudad = "Girardot"
                ref.child("ciudad").setValue(ciudad)
                startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            }
            soacha.setOnClickListener {
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
                val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
                val ciudad = "Soacha"
                ref.child("ciudad").setValue(ciudad)
                startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            }
            zipaquira.setOnClickListener {
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
                val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
                val ciudad = "Zipaquirá"
                ref.child("ciudad").setValue(ciudad)
                startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            }
    }
}
