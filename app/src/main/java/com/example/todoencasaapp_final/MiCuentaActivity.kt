package com.example.todoencasaapp_final

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_mi_cuenta.*
import kotlinx.android.synthetic.main.fragment_inicio.marcador


class MiCuentaActivity : AppCompatActivity() {

    val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mi_cuenta)

        marcador.setOnClickListener{
            onBackPressed()
        }

        b_cerrar.setOnClickListener{
            onBackPressed()
        }

        foto.setOnClickListener{
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
