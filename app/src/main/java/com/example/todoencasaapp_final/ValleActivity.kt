package com.example.todoencasaapp_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_valle.*

class ValleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_valle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

            cali.setOnClickListener {
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
                val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
                val ciudad = "Cali"
                ref.child("ciudad").setValue(ciudad)
                startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            }
            cartago.setOnClickListener {
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
                val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
                val ciudad = "Cartago"
                ref.child("ciudad").setValue(ciudad)
                startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            }
            aguila.setOnClickListener {
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
                val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
                val ciudad = "El Águila"
                ref.child("ciudad").setValue(ciudad)
                startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            }
            guacari.setOnClickListener {
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
                val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
                val ciudad = "Guacarí"
                ref.child("ciudad").setValue(ciudad)
                startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            }
            sanpedro.setOnClickListener {
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
                val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
                val ciudad = "San Pedro"
                ref.child("ciudad").setValue(ciudad)
                startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            }
            tulua.setOnClickListener {
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
                val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
                val ciudad = "Tuluá"
                ref.child("ciudad").setValue(ciudad)
                startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            }
    }
}
