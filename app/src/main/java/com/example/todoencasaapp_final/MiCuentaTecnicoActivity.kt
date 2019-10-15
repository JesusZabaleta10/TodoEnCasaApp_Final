package com.example.todoencasaapp_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_mi_cuenta_tecnico.*

class MiCuentaTecnicoActivity : AppCompatActivity() {

    // ---------- Leer Base de Datos ---------------------
    private lateinit var mDatabase: DatabaseReference // Identificacion
    private lateinit var mDatabase2: DatabaseReference // Tecnico
    // ---------- Leer Base de Datos ---------------------
    private var identificacion = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mi_cuenta_tecnico)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // ---------- Leer Base de Datos ---------------------------------------------------------------
        mDatabase2 = FirebaseDatabase.getInstance().getReference().child("Identificacion")

        val datos2 = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()) {
                    identificacion = dataSnapshot.child("identificacion").value.toString()
                    Log.d("Identificacion: ",identificacion)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@MiCuentaTecnicoActivity,"Error en la lectura de datos", Toast.LENGTH_SHORT).show()
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
                Toast.makeText(this@MiCuentaTecnicoActivity,"Error en la lectura de datos", Toast.LENGTH_SHORT).show()
            }
        }

        mDatabase.addValueEventListener(datos)
        // ---------- Leer Base de Datos ---------------------------------------------------------------

        b_cerrar_sesion.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this,MainActivity::class.java))
            Toast.makeText(this,"Sesión cerrada",Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

}
