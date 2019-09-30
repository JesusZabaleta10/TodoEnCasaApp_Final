package com.example.todoencasaapp_final

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_ciudades.*
import kotlinx.android.synthetic.main.fragment_inicio.*
import kotlinx.android.synthetic.main.fragment_inicio.marcador

class CiudadesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciudades)

        //val manager = supportFragmentManager
        //val transaction = manager.beginTransaction()

        marcador.setOnClickListener{
            onBackPressed()
            /*val manager = supportFragmentManager
            val transaction = manager.beginTransaction()

            val InicioFragment = InicioFragment()
            transaction.add(R.id.frameLayout, InicioFragment).commit()*/
        }

        /*barranquilla.setOnClickListener{
            onBackPressed()
            ciudad.text = "Barranquilla"
        }*/
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
