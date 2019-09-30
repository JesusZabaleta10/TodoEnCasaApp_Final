package com.example.todoencasaapp_final

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_inicio.view.*
import kotlinx.android.synthetic.main.fragment_perfil.view.*

class PerfilFragment : Fragment() {

    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_perfil, container, false)

        root.b_registrar.setOnClickListener{
            startActivity(Intent(root.context, RegistrarActivity::class.java))
        }

        root.b_login.setOnClickListener{
            startActivity(Intent(root.context, MiCuentaActivity::class.java))
        }

        return root
    }


}
