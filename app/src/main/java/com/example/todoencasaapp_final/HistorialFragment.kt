package com.example.todoencasaapp_final

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_historial.*
import kotlinx.android.synthetic.main.fragment_historial.view.*

class HistorialFragment : Fragment() {

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

    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_historial, container, false)

        // ---------- Leer Base de Datos ---------------------------------------------------------------
        mDatabase2 = FirebaseDatabase.getInstance().getReference().child("Identificacion_Tecnico")

        val datos = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()) {
                    identificacion = dataSnapshot.child("identificacion_tecnico").value.toString()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(root.context,"Error en la lectura de datos", Toast.LENGTH_SHORT).show()
            }
        }

        mDatabase2.addValueEventListener(datos)
        // ---------- Leer Base de Datos ---------------------------------------------------------------

        // ---------- Leer Base de Datos ---------------------------------------------------------------
        mDatabase3 = FirebaseDatabase.getInstance().getReference()

        val datos3 = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.child("Tecnico").child(identificacion).exists()){

                    nombres = dataSnapshot.child("Tecnico").child(identificacion).child("nombres").value.toString()
                    apellidos = dataSnapshot.child("Tecnico").child(identificacion).child("apellidos").value.toString()
                    nombre_tecnico.text = nombres + " " + apellidos

                }
                else {
                    Toast.makeText(root.context,"DEBES INICIAR SESIÓN COMO USUARIO",Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(root.context,"Error en la lectura de datos",Toast.LENGTH_SHORT).show()
            }
        }

        mDatabase3.addValueEventListener(datos3)
        // ---------- Leer Base de Datos ---------------------------------------------------------------

        root.card_tecnico.setOnClickListener {
            action()
        }
        return root
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
                Toast.makeText(root.context,"Error en la lectura de datos", Toast.LENGTH_SHORT).show()
            }
        }

        mDatabase4.addValueEventListener(datos2)
        // ---------- Leer Base de Datos ---------------------------------------------------------------

        // ---------- Leer Base de Datos ---------------------------------------------------------------
        mDatabase = FirebaseDatabase.getInstance().getReference()

        val datos = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.child("Usuarios").child(identificacion).exists()){

                    startActivity(Intent(root.context,DetallesServicioActivity::class.java))

                }
                else {
                    Toast.makeText(root.context,"DEBES INICIAR SESIÓN COMO USUARIO",Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(root.context,"Error en la lectura de datos",Toast.LENGTH_SHORT).show()
            }
        }

        mDatabase.addValueEventListener(datos)
        // ---------- Leer Base de Datos ---------------------------------------------------------------
    }
}
