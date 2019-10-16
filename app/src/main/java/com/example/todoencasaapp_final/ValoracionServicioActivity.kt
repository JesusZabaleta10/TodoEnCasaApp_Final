package com.example.todoencasaapp_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_valoracion_servicio.*
import kotlinx.android.synthetic.main.fragment_inicio.*
import kotlinx.android.synthetic.main.fragment_inicio.marcador

class ValoracionServicioActivity : AppCompatActivity() {

    // ---------- Leer Base de Datos ---------------------
    private lateinit var mDatabase: DatabaseReference // Usuario
    private lateinit var mDatabase2: DatabaseReference // Identificacion
    // ---------- Leer Base de Datos ---------------------
    private var identificacion = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_valoracion_servicio)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // ---------- Leer Base de Datos ---------------------------------------------------------------
        mDatabase2 = FirebaseDatabase.getInstance().getReference().child("Identificacion_Tecnico")

        val datos2 = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()) {
                    identificacion = dataSnapshot.child("identificacion_tecnico").value.toString()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@ValoracionServicioActivity,"Error en la lectura de datos", Toast.LENGTH_SHORT).show()
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
                    val nombres = dataSnapshot.child("Tecnico").child(identificacion).child("nombres").value.toString()
                    val apellidos = dataSnapshot.child("Tecnico").child(identificacion).child("apellidos").value.toString()
                    nombre_tecnico.text = nombres + " " + apellidos
                    servicio_solicitud.text = "Servicio: " + dataSnapshot.child("DatosServicio").child(identificacion).child("servicio").value.toString()
                    dia_solicitud.text = "Dia: " + dataSnapshot.child("DatosServicio").child(identificacion).child("dia").value.toString()
                    hora_solicitud.text = "Hora: " + dataSnapshot.child("DatosServicio").child(identificacion).child("hora").value.toString()
                    direccion_solicitud.text = "Dirección: " + dataSnapshot.child("DatosServicio").child(identificacion).child("direccion").value.toString()
                    celular_solicitud.text = "Celular: " + dataSnapshot.child("DatosServicio").child(identificacion).child("celular").value.toString()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@ValoracionServicioActivity,"Error en la lectura de datos", Toast.LENGTH_SHORT).show()
            }
        }

        mDatabase.addValueEventListener(datos)
        // ---------- Leer Base de Datos ---------------------------------------------------------------

        // ----------------- Añado un comentario a la base de datos ---------------------------------------
        valorar_servicio.setOnClickListener {
            // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            val ref = FirebaseDatabase.getInstance().getReference("Comentarios")
            val comentarios_tecnico = comentarios.text.toString()
            ref.child("comentarios").setValue(comentarios_tecnico)
            // -------- Añadir la ciudad del usuario a la base de datos --------------------------

            startActivity(Intent(this,ValoracionServicio2Activity::class.java))
            finish()
        }
        // ----------------- Añado un comentario a la base de datos --------------------------------------
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
