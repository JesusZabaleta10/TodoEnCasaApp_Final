package com.example.todoencasaapp_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_detalles_servicio2.*
import kotlinx.android.synthetic.main.fragment_inicio.*

class DetallesServicio2Activity : AppCompatActivity() {

    // ---------- Leer Base de Datos ---------------------
    private lateinit var mDatabase: DatabaseReference // Usuario
    private lateinit var mDatabase2: DatabaseReference // Identificacion
    // ---------- Leer Base de Datos ---------------------
    private var identificacion = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_servicio2)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // ---------- Leer Base de Datos ---------------------------------------------------------------
        mDatabase2 = FirebaseDatabase.getInstance().getReference().child("Identificacion")

        val datos2 = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()) {
                    identificacion = dataSnapshot.child("identificacion").value.toString()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@DetallesServicio2Activity,"Error en la lectura de datos", Toast.LENGTH_SHORT).show()
            }
        }

        mDatabase2.addValueEventListener(datos2)
        // ---------- Leer Base de Datos ---------------------------------------------------------------

        // ---------- Leer Base de Datos ---------------------------------------------------------------
        mDatabase = FirebaseDatabase.getInstance().getReference()

        val datos = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.d("Datos: ",dataSnapshot.value.toString())
                if(dataSnapshot.exists()){
                    servicio_solicitud.text = "Servicio: " + dataSnapshot.child("DatosServicio").child(identificacion).child("servicio").value.toString()
                    dia_solicitud.text = "Dia: " + dataSnapshot.child("DatosServicio").child(identificacion).child("dia").value.toString()
                    hora_solicitud.text = "Hora: " + dataSnapshot.child("DatosServicio").child(identificacion).child("hora").value.toString()
                    direccion_solicitud.text = "Dirección: " + dataSnapshot.child("DatosServicio").child(identificacion).child("direccion").value.toString()
                    celular_solicitud.text = "Celular: " + dataSnapshot.child("DatosServicio").child(identificacion).child("celular").value.toString()
                    descripcion_solicitud.text = dataSnapshot.child("DatosServicio").child(identificacion).child("descripcion").value.toString()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@DetallesServicio2Activity,"Error en la lectura de datos", Toast.LENGTH_SHORT).show()
            }
        }

        mDatabase.addValueEventListener(datos)
        // ---------- Leer Base de Datos ---------------------------------------------------------------

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
