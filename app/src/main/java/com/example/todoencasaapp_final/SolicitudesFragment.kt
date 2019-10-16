package com.example.todoencasaapp_final

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_solicitudes.*
import kotlinx.android.synthetic.main.fragment_solicitudes.view.*

class SolicitudesFragment : Fragment() {

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
        root = inflater.inflate(R.layout.fragment_solicitudes, container, false)

        // ---------- Leer Base de Datos ---------------------------------------------------------------
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Identificacion_Usuario")

        val datos = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()) {
                    identificacion = dataSnapshot.child("identificacion_usuario").value.toString()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(root.context,"Error en la lectura de datos", Toast.LENGTH_SHORT).show()
            }
        }

        mDatabase.addValueEventListener(datos)
        // ---------- Leer Base de Datos ---------------------------------------------------------------

        // ---------- Leer Base de Datos ---------------------------------------------------------------
        mDatabase2 = FirebaseDatabase.getInstance().getReference()

        val datos2 = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.child("Usuarios").child(identificacion).exists()){

                    nombres = dataSnapshot.child("Usuarios").child(identificacion).child("nombres").value.toString()
                    apellidos = dataSnapshot.child("Usuarios").child(identificacion).child("apellidos").value.toString()
                    nombre_cliente.text = nombres + " " + apellidos

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(root.context,"Error en la lectura de datos",Toast.LENGTH_SHORT).show()
            }
        }

        mDatabase2.addValueEventListener(datos2)
        // ---------- Leer Base de Datos ---------------------------------------------------------------

        root.card_cliente.setOnClickListener {
            action()
            //startActivity(Intent(root.context,SolicitudActivity::class.java))
        }

        return root
    }

    private fun action() {

        // ---------- Leer Base de Datos ---------------------------------------------------------------
        mDatabase3 = FirebaseDatabase.getInstance().getReference().child("Identificacion")

        val datos3 = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()) {
                    identificacion2 = dataSnapshot.child("identificacion").value.toString()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(root.context,"Error en la lectura de datos", Toast.LENGTH_SHORT).show()
            }
        }

        mDatabase3.addValueEventListener(datos3)
        // ---------- Leer Base de Datos ---------------------------------------------------------------

        // ---------- Leer Base de Datos ---------------------------------------------------------------
        mDatabase4 = FirebaseDatabase.getInstance().getReference()

        val datos4 = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.child("Tecnico").child(identificacion2).exists()){

                    startActivity(Intent(root.context,SolicitudActivity::class.java))

                }
                else {
                    Toast.makeText(root.context,"DEBES INICIAR SESIÓN COMO TÉCNICO",Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(root.context,"Error en la lectura de datos",Toast.LENGTH_SHORT).show()
            }
        }

        mDatabase4.addValueEventListener(datos4)
        // ---------- Leer Base de Datos ---------------------------------------------------------------
    }
}
