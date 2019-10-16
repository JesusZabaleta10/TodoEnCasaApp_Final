package com.example.todoencasaapp_final

import android.annotation.SuppressLint
import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_mi_cuenta.*
import kotlinx.android.synthetic.main.activity_registrar.*
import java.util.jar.Manifest

class MiCuentaActivity : AppCompatActivity() {

    // ---------- Leer Base de Datos ---------------------
    private lateinit var mDatabase: DatabaseReference // Usuario
    private lateinit var mDatabase2: DatabaseReference // Identificacion
    // ---------- Leer Base de Datos ---------------------
    private var identificacion = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mi_cuenta)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // ---------- Leer Base de Datos ---------------------------------------------------------------
        mDatabase2 = FirebaseDatabase.getInstance().getReference().child("Identificacion_Usuario")

        val datos2 = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()) {
                    identificacion = dataSnapshot.child("identificacion_usuario").value.toString()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@MiCuentaActivity,"Error en la lectura de datos",Toast.LENGTH_SHORT).show()
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
                    nombre_usuario.text = dataSnapshot.child("Usuarios").child(identificacion).child("nombres").value.toString()
                    apellido_usuario.text = dataSnapshot.child("Usuarios").child(identificacion).child("apellidos").value.toString()
                    identificacion_user.text = dataSnapshot.child("Usuarios").child(identificacion).child("identificacion").value.toString()
                    correo.text = dataSnapshot.child("Usuarios").child(identificacion).child("correo").value.toString()
                    celular.text = dataSnapshot.child("Usuarios").child(identificacion).child("celular").value.toString()
                    direccion_user.text = dataSnapshot.child("Usuarios").child(identificacion).child("direccion").value.toString()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@MiCuentaActivity,"Error en la lectura de datos",Toast.LENGTH_SHORT).show()
            }
        }

        mDatabase.addValueEventListener(datos)
        // ---------- Leer Base de Datos ---------------------------------------------------------------

        b_cerrar.setOnClickListener{

            // -------- Añadir la identificacion del usuario a la base de datos --------------------------
            val ref = FirebaseDatabase.getInstance().getReference("Identificacion")
            val identificacion = "NULL"
            ref.child("identificacion").setValue(identificacion)
            // -------- Añadir la identificacion del usuario a la base de datos --------------------------

            // -------- Añadir la identificacion del usuario a la base de datos --------------------------
            val ref2= FirebaseDatabase.getInstance().getReference("Bandera")
            val bandera = "NULL"
            ref2.child("bandera").setValue(bandera)
            // -------- Añadir la identificacion del usuario a la base de datos --------------------------

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
