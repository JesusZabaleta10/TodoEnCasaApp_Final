package com.example.todoencasaapp_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_cordoba.*

class CordobaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cordoba)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

            ayapel.setOnClickListener {
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
                val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
                val ciudad = "Ayapel"
                ref.child("ciudad").setValue(ciudad)
                startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            }
            cienaga.setOnClickListener {
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
                val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
                val ciudad = "Ciénaga de Oro"
                ref.child("ciudad").setValue(ciudad)
                startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            }
            montelibano.setOnClickListener {
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
                val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
                val ciudad = "Montelíbano"
                ref.child("ciudad").setValue(ciudad)
                startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            }
            monteria.setOnClickListener {
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
                val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
                val ciudad = "Montería"
                ref.child("ciudad").setValue(ciudad)
                startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            }
            planeta.setOnClickListener {
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
                val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
                val ciudad = "Planeta Rica"
                ref.child("ciudad").setValue(ciudad)
                startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            }
            valencia.setOnClickListener {
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
                val ref = FirebaseDatabase.getInstance().getReference("Ciudad")
                val ciudad = "Valencia"
                ref.child("ciudad").setValue(ciudad)
                startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
                // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            }
    }
}
