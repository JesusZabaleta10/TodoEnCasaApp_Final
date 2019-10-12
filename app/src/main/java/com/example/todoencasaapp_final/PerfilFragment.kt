package com.example.todoencasaapp_final

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registrar.*
import kotlinx.android.synthetic.main.fragment_perfil.view.*
import kotlinx.android.synthetic.main.fragment_perfil.view.b_registrar

class PerfilFragment : Fragment() {

    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_perfil, container, false)

        root.b_registrar.setOnClickListener{
            startActivity(Intent(root.context, RegistrarActivity::class.java))
            Toast.makeText(root.context,"Registro", Toast.LENGTH_SHORT).show()
        }

        root.b_login.setOnClickListener{
            startActivity(Intent(root.context, MiCuentaActivity::class.java))
            Toast.makeText(root.context,"Sesión iniciada", Toast.LENGTH_SHORT).show()
        }

        return root
    }
}
