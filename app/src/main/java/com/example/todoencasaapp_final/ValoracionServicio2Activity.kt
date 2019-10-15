package com.example.todoencasaapp_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_valoracion_servicio2.*
import kotlinx.android.synthetic.main.fragment_inicio.*

class ValoracionServicio2Activity : AppCompatActivity() {

    // ---------- Leer Base de Datos ---------------------
    private lateinit var mDatabase: DatabaseReference   // Tecnico
    private lateinit var mDatabase2: DatabaseReference // Identificacion
    private lateinit var mDatabase3: DatabaseReference // Comentarios
    // ---------- Leer Base de Datos ---------------------
    private var identificacion = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_valoracion_servicio2)
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
                Toast.makeText(this@ValoracionServicio2Activity,"Error en la lectura de datos", Toast.LENGTH_SHORT).show()
            }
        }

        mDatabase2.addValueEventListener(datos2)
        // ---------- Leer Base de Datos ---------------------------------------------------------------

        // ---------- Leer Base de Datos ---------------------------------------------------------------
        mDatabase3 = FirebaseDatabase.getInstance().getReference().child("Comentarios")

        val datos3 = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()) {
                    comentarios.text = dataSnapshot.child("comentarios").value.toString()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@ValoracionServicio2Activity,"Error en la lectura de datos", Toast.LENGTH_SHORT).show()
            }
        }

        mDatabase3.addValueEventListener(datos3)
        // ---------- Leer Base de Datos ---------------------------------------------------------------

        // ---------- Leer Base de Datos ---------------------------------------------------------------
        mDatabase = FirebaseDatabase.getInstance().getReference()

        val datos = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.d("Datos: ",dataSnapshot.value.toString())
                if(dataSnapshot.exists()){
                    nombre_tecnico.text = dataSnapshot.child("Tecnico").child(identificacion).child("nombres").value.toString()
                    apellido_tecnico.text = dataSnapshot.child("Tecnico").child(identificacion).child("apellidos").value.toString()
                    nacimiento.text = dataSnapshot.child("Tecnico").child(identificacion).child("fecha_nacimiento").value.toString()
                    identificacion_tecnico.text = dataSnapshot.child("Tecnico").child(identificacion).child("identificacion").value.toString()
                    celular_tecnico.text = dataSnapshot.child("Tecnico").child(identificacion).child("celular").value.toString()
                    correo_tecnico.text = dataSnapshot.child("Tecnico").child(identificacion).child("correo").value.toString()
                    descripcion.text = dataSnapshot.child("Tecnico").child(identificacion).child("descripcion").value.toString()
                    experiencia.text = dataSnapshot.child("Tecnico").child(identificacion).child("experiencia").value.toString()
                    horario.text = dataSnapshot.child("Tecnico").child(identificacion).child("horario").value.toString()
                    direccion_tecnico.text = dataSnapshot.child("Tecnico").child(identificacion).child("direccion").value.toString()
                    categoria.text = dataSnapshot.child("Tecnico").child(identificacion).child("categoria").value.toString()
                    subcategoria.text = dataSnapshot.child("Tecnico").child(identificacion).child("subcatergoria").value.toString()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@ValoracionServicio2Activity,"Error en la lectura de datos", Toast.LENGTH_SHORT).show()
            }
        }

        mDatabase.addValueEventListener(datos)
        // ---------- Leer Base de Datos ---------------------------------------------------------------

        inicio.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
