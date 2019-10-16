package com.example.todoencasaapp_final

import android.app.PendingIntent.getActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_instalaciones.*
import kotlinx.android.synthetic.main.fragment_inicio.*
import kotlinx.android.synthetic.main.fragment_inicio.marcador

class InstalacionesActivity : AppCompatActivity() {

    // ---------- Leer Base de Datos ---------------------
    private lateinit var mDatabase: DatabaseReference
    private lateinit var mDatabase2: DatabaseReference
    private lateinit var mDatabase3: DatabaseReference
    private lateinit var mDatabase4: DatabaseReference
    // ---------- Leer Base de Datos ---------------------

    private var identificacion = ""
    private var identificacion2 = ""
    private var nombres = ""
    private var apellidos = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instalaciones)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // ---------- Leer Base de Datos ---------------------------------------------------------------
        mDatabase2 = FirebaseDatabase.getInstance().getReference().child("Identificacion_Tecnico")

        val datos = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()) {
                    identificacion2 = dataSnapshot.child("identificacion_tecnico").value.toString()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@InstalacionesActivity,"Error en la lectura de datos", Toast.LENGTH_SHORT).show()
            }
        }

        mDatabase2.addValueEventListener(datos)
        // ---------- Leer Base de Datos ---------------------------------------------------------------

        // ---------- Leer Base de Datos ---------------------------------------------------------------
        mDatabase3 = FirebaseDatabase.getInstance().getReference()

        val datos3 = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.child("Tecnico").child(identificacion2).exists()){

                    nombres = dataSnapshot.child("Tecnico").child(identificacion2).child("nombres").value.toString()
                    apellidos = dataSnapshot.child("Tecnico").child(identificacion2).child("apellidos").value.toString()
                    nombre_tecnico.text = nombres + " " + apellidos

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@InstalacionesActivity,"Error en la lectura de datos",Toast.LENGTH_SHORT).show()
            }
        }

        mDatabase3.addValueEventListener(datos3)
        // ---------- Leer Base de Datos ---------------------------------------------------------------

        card_tecnico.setOnClickListener {
            action()
        }
    }

    private fun action() {

        // ---------- Leer Base de Datos ---------------------------------------------------------------
        mDatabase4 = FirebaseDatabase.getInstance().getReference().child("Identificacion")

        val datos2 = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()) {
                    identificacion = dataSnapshot.child("identificacion").value.toString()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@InstalacionesActivity,"Error en la lectura de datos", Toast.LENGTH_SHORT).show()
            }
        }

        mDatabase4.addValueEventListener(datos2)
        // ---------- Leer Base de Datos ---------------------------------------------------------------

        // ---------- Leer Base de Datos ---------------------------------------------------------------
        mDatabase = FirebaseDatabase.getInstance().getReference()

        val datos = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.child("Usuarios").child(identificacion).exists()){

                    startActivity(Intent(this@InstalacionesActivity,Instalaciones2Activity::class.java))

                }
                else {
                    Toast.makeText(this@InstalacionesActivity,"DEBES INICIAR SESIÓN COMO USUARIO",Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@InstalacionesActivity,"Error en la lectura de datos",Toast.LENGTH_SHORT).show()
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
