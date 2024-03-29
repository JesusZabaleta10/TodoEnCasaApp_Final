package com.example.todoencasaapp_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_atlantico.*

class AtlanticoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atlantico)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

            barranquilla.setOnClickListener {
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
                val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
                val ciudad = "Barranquilla"
                ref.child("ciudad").setValue(ciudad)
                startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            }
            candelaria.setOnClickListener {
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
                val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
                val ciudad = "Candelaria"
                ref.child("ciudad").setValue(ciudad)
                startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            }

            manati.setOnClickListener {
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
                val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
                val ciudad = "Manatí"
                ref.child("ciudad").setValue(ciudad)
                startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            }

            puerto.setOnClickListener {
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
                val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
                val ciudad = "Puerto Colombia"
                ref.child("ciudad").setValue(ciudad)
                startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            }

            santa.setOnClickListener {
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
                val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
                val ciudad = "Santa Lucia"
                ref.child("ciudad").setValue(ciudad)
                startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            }

            soledad.setOnClickListener {
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
                val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
                val ciudad = "Soledad"
                ref.child("ciudad").setValue(ciudad)
                startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            }
    }
}
