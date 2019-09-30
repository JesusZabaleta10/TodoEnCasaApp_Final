package com.example.todoencasaapp_final

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_historial.view.*
import kotlinx.android.synthetic.main.fragment_solicitudes.view.*

class SolicitudesFragment : Fragment() {

    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_solicitudes, container, false)

        root.card_cliente.setOnClickListener {
            startActivity(Intent(root.context,SolicitudActivity::class.java))
        }

        return root
    }


}
