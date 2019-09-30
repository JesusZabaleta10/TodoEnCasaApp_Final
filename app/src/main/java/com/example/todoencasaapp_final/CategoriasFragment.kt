package com.example.todoencasaapp_final

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_inicio.view.*

class CategoriasFragment : Fragment() {

    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_categorias, container, false)

        root.carpinteria.setOnClickListener{
            startActivity(Intent(root.context, CarpinteriaActivity::class.java))
        }

        root.electricidad.setOnClickListener{
            startActivity(Intent(root.context, ElectricidadActivity::class.java))
        }

        root.refrigeracion.setOnClickListener{
            startActivity(Intent(root.context, RefrigeracionActivity::class.java))
        }

        root.limpieza.setOnClickListener{
            startActivity(Intent(root.context, LimpiezaActivity::class.java))
        }

        root.mecanica.setOnClickListener{
            startActivity(Intent(root.context, MecanicaActivity::class.java))
        }

        root.acarreo.setOnClickListener{
            startActivity(Intent(root.context, AcarreosActivity::class.java))
        }

        return root
    }


}
