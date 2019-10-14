package com.example.todoencasaapp_final

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_perfil.*
import kotlinx.android.synthetic.main.fragment_perfil.view.*
import kotlinx.android.synthetic.main.fragment_perfil.view.b_registrar


class PerfilFragment : Fragment() {

    private lateinit var progressBar: ProgressBar
    private lateinit var auth: FirebaseAuth
    private lateinit var root: View

    // ---------- Leer Base de Datos ---------------------
    private lateinit var mDatabase: DatabaseReference //Usuarios
    private lateinit var mDatabase2: DatabaseReference //Identificacion
    // ---------- Leer Base de Datos ---------------------

    private var identificacion = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_perfil, container, false)

        // ---------- Leer Base de Datos ---------------------------------------------------------------
        mDatabase2 = FirebaseDatabase.getInstance().getReference().child("Identificacion")

        val datos = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()) {
                    identificacion = dataSnapshot.child("identificacion").value.toString()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(root.context,"Error en la lectura de datos",Toast.LENGTH_SHORT).show()
            }
        }

        mDatabase2.addValueEventListener(datos)
        // ---------- Leer Base de Datos ---------------------------------------------------------------

        progressBar = root.findViewById(R.id.progressBar)
        auth = FirebaseAuth.getInstance()

        root.b_registrar.setOnClickListener {
            startActivity(Intent(root.context, RegistrarActivity::class.java))
            Toast.makeText(root.context, "Registro", Toast.LENGTH_SHORT).show()
        }

        root.b_login.setOnClickListener {
            loginUser()
        }

        return root
    }

    //---------------------------------- Registro con correo y contraseña ----------------------------------------//
    private fun loginUser() {

        // -------- Añadir la identificacion del usuario a la base de datos --------------------------
        val ref = FirebaseDatabase.getInstance().getReference("Identificacion")
        val id = et_id.text.toString()
        ref.child("identificacion").setValue(id)
        // -------- Añadir la identificacion del usuario a la base de datos --------------------------

        val correo = et_correo.text.toString()
        val contraseña = et_contraseña.text.toString()

        if ((correo == "") || (contraseña == "")|| (id == "")) {
            Toast.makeText(root.context, "Faltan campos por llenar", Toast.LENGTH_SHORT).show()
        } else {

            progressBar.visibility = View.VISIBLE

            auth.signInWithEmailAndPassword(correo, contraseña)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                       action()
                    } else {
                        progressBar.visibility = View.GONE
                        Toast.makeText(root.context, "Error en la autenticación", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun action() {
        // ---------- Leer Base de Datos ---------------------------------------------------------------
        mDatabase = FirebaseDatabase.getInstance().getReference()

        val datos = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.child("Usuarios").child(identificacion).exists()){

                    startActivity(Intent(root.context,MiCuentaActivity::class.java))
                    getActivity()?.finish()
                    Toast.makeText(root.context, "Sesión iniciada", Toast.LENGTH_SHORT).show()

                }else if(dataSnapshot.child("Tecnico").child(identificacion).exists()){

                    startActivity(Intent(root.context,MiCuentaActivity::class.java))
                    getActivity()?.finish()
                    Toast.makeText(root.context, "Sesión iniciada", Toast.LENGTH_SHORT).show()
                }
                else {
                    progressBar.visibility = View.GONE
                    Toast.makeText(root.context,"El número de identificación no se encuentra en la base de datos",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                progressBar.visibility = View.GONE
                Toast.makeText(root.context,"Error en la lectura de datos",Toast.LENGTH_SHORT).show()
            }
        }

        mDatabase.addValueEventListener(datos)
        // ---------- Leer Base de Datos ---------------------------------------------------------------
    }
    //---------------------------------- Registro con correo y contraseña ----------------------------------------//

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            startActivity(Intent(root.context,MiCuentaActivity::class.java))
            getActivity()?.finish()
        }
    }
}