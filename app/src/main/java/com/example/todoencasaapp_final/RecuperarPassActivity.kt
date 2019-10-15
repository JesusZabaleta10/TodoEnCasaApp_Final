package com.example.todoencasaapp_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_recuperar_pass.*

class RecuperarPassActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar_pass)

        auth = FirebaseAuth.getInstance()
        progressBar = findViewById(R.id.progressBar)

        send.setOnClickListener{
            val email = txtEmail.text.toString()

            if (!TextUtils.isEmpty(email)) {
                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(this){
                            task ->
                        if(task.isSuccessful){
                            progressBar.visibility = View.VISIBLE
                            //startActivity(Intent(this, LoginActivity::class.java))
                            Toast.makeText(this, "Se ha enviado un correo de recuperación", Toast.LENGTH_SHORT).show()
                            finish()
                        }else{
                            Toast.makeText(this, "Error al recuperar la contraseña", Toast.LENGTH_SHORT).show()
                            progressBar.visibility = View.GONE
                        }
                    }
            }
        }
    }
}
