package com.example.todoencasaapp_final

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_departamentos.*

class DepartamentosActivity : AppCompatActivity() {

    var ciudad = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_departamentos)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        antioquia.setOnClickListener {
            var intent = Intent(this, AntioquiaActivity::class.java)
            startActivityForResult(intent,1)
        }

        atlantico.setOnClickListener {
            startActivity(Intent(this,AtlanticoActivity::class.java))
        }

        cordoba.setOnClickListener {
            startActivity(Intent(this,CordobaActivity::class.java))
        }

        cundinamarca.setOnClickListener {
            startActivity(Intent(this,CundinamarcaActivity::class.java))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            ciudad = data?.extras?.getString("antioquia").toString()
            //---------------- Envio de datos ----------------
            var intent = Intent(this, MainActivity::class.java)
            intent.putExtra("antioquia", ciudad)
            setResult(Activity.RESULT_OK,intent)
            finish()
            // ------------------------------------------------
        }
    }
}
