package com.example.todoencasaapp_final

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_registrar.*
import kotlinx.android.synthetic.main.activity_registrar.b_registrar
import kotlinx.android.synthetic.main.activity_registrar.foto_mi_cuenta
import kotlinx.android.synthetic.main.activity_registrar.galeria_mi_cuenta
import kotlinx.android.synthetic.main.fragment_perfil.*
import java.io.ByteArrayOutputStream
import java.io.IOException

class RegistrarActivity : AppCompatActivity() {

    // ----------- Registro con correo y contraseña ------------
    private lateinit var progressBar: ProgressBar
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    // ----------- Registro con correo y contraseña ------------

    private val PICK_IMAGE_REQUEST = 1234
    private var filePath: Uri? = null
    private lateinit var storage: FirebaseStorage

    lateinit var cb_terminos_condiciones: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // ----------- Registro con correo y contraseña ------------
        progressBar = findViewById(R.id.progressBar)
        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()
        // ----------- Registro con correo y contraseña ------------

        cb_terminos_condiciones = findViewById(R.id.cb_terminos) as CheckBox

        b_registrar.setOnClickListener{
            createNewAccount()
            //saveImage()
        }

        condiciones.setOnClickListener{
            startActivity(Intent(this,TerminosCondicionesActivity::class.java))
        }

        direccion.setOnClickListener {
            var intent = Intent(this, MapsActivity::class.java)
            startActivityForResult(intent,1234)
        }

        //------------ Galeria -------------------------------------------------------
        galeria_mi_cuenta.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent,"Seleccionar imagen"), PICK_IMAGE_REQUEST)
        }
        //------------ Galeria -------------------------------------------------------

        //------------ Camara -------------------------------------------------------
        foto_mi_cuenta.setOnClickListener{
            var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i,123)
        }
        //------------ Camara -------------------------------------------------------
    }

    // ----------- Registro con correo y contraseña ------------
    private fun createNewAccount(){
        val nombres = nombres_usuario.text.toString()
        val apellidos = apellidos_usuario.text.toString()
        val num_identificacion = identificacion.text.toString()
        val correo = correo_usuario.text.toString()
        val celular = celular_usuario.text.toString()
        val contraseña = password.text.toString()
        val repetir_contraseña = repeat_password.text.toString()
        val direccion_usuario = direccion.text.toString()
        val foto = ""

        if((nombres == "") || (apellidos == "")|| (num_identificacion == "") || (correo == "")|| (celular == "") || (contraseña == "")|| (repetir_contraseña == "")|| (direccion_usuario == "")){
            Toast.makeText(this, "Hay campos sin llenar", Toast.LENGTH_SHORT).show()
        }else if(!cb_terminos_condiciones.isChecked){
            Toast.makeText(this, "Debes aceptar los términos y condiciones", Toast.LENGTH_SHORT).show()
        }else if(contraseña.length < 6 || repetir_contraseña.length < 6){
            Toast.makeText(this, "La contraseña no tiene los 6 caracteres mínimos", Toast.LENGTH_SHORT).show()
        }
        else if(contraseña != repetir_contraseña){
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show()
        }else{

            progressBar.visibility = View.VISIBLE

            auth.createUserWithEmailAndPassword(correo,contraseña)
                .addOnCompleteListener(this){
                        task ->
                    if(task.isComplete){
                        val user: FirebaseUser? = auth.currentUser
                        verifyEmail(user)

                        val usuario = Usuarios(nombres,apellidos,correo,direccion_usuario,num_identificacion,celular,foto)

                        val database = FirebaseDatabase.getInstance()
                        val myRef = database.getReference("Usuarios")

                        myRef.child(num_identificacion).setValue(usuario)

                        action()
                    }
                }
        }
    }

    private fun action(){
        FirebaseAuth.getInstance().signOut()
        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun verifyEmail(user: FirebaseUser?){
        user?.sendEmailVerification()
            ?.addOnCompleteListener(this){
                    task ->
                if(task.isComplete){
                    Toast.makeText(this, "Correo de verificación enviado", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Error al enviar el correo", Toast.LENGTH_SHORT).show()
                }
            }
    }
    // ----------- Registro con correo y contraseña ------------

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //------------ Galeria -------------------------------------------------------
        if(requestCode == PICK_IMAGE_REQUEST &&
                resultCode == Activity.RESULT_OK &&
                data != null && data.data != null)
        {
            filePath = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,filePath)
                foto_mi_cuenta!!.setImageBitmap(bitmap)
            }catch (e:IOException)
            {
                e.printStackTrace()
            }
        }
        //------------ Galeria -------------------------------------------------------

        //------------ Camara -------------------------------------------------------
        if(requestCode==123){
            var bmp = data?.extras?.get("data") as Bitmap
            foto_mi_cuenta.setImageBitmap(bmp)
        }
        //------------ Camara -------------------------------------------------------

        if(requestCode == 1234 && resultCode == Activity.RESULT_OK){
            direccion.text = data?.extras?.getString("ubicacion")
        }
    }

    // ------------------- Guardar foto en firebase ---------------------------
    private fun saveImage(){

        storage = FirebaseStorage.getInstance()
        val photoRef = storage.reference.child("Usuarios").child(identificacion.text.toString())

        foto_mi_cuenta.isDrawingCacheEnabled = true
        foto_mi_cuenta.buildDrawingCache()
        val bitmap = (foto_mi_cuenta.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = photoRef.putBytes(data)

        val urlTask = uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            return@Continuation photoRef.downloadUrl
        }).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result
                Log.d("URL",downloadUri.toString())
                saveUser(downloadUri.toString())
            } else {
            }
        }
    }
    // ------------------- Guardar foto en firebase ---------------------------

    private fun saveUser(urlFoto: String){
        val nombres = nombres_usuario.text.toString()
        val apellidos = apellidos_usuario.text.toString()
        val num_identificacion = identificacion.text.toString()
        val correo = correo_usuario.text.toString()
        val celular = celular_usuario.text.toString()
        val contraseña = password.text.toString()
        val repetir_contraseña = repeat_password.text.toString()
        val direccion_usuario = direccion.text.toString()
        val foto = urlFoto

        if((nombres == "") || (apellidos == "")|| (num_identificacion == "") || (correo == "")|| (celular == "") || (contraseña == "")|| (repetir_contraseña == "")|| (direccion_usuario == "")){
            Toast.makeText(this, "Hay campos sin llenar", Toast.LENGTH_SHORT).show()
        }else if(!cb_terminos_condiciones.isChecked){
            Toast.makeText(this, "Debes aceptar los términos y condiciones", Toast.LENGTH_SHORT).show()
        }else if(contraseña.length < 6 || repetir_contraseña.length < 6){
            Toast.makeText(this, "La contraseña no tiene los 6 caracteres mínimos", Toast.LENGTH_SHORT).show()
        }
        else if(contraseña != repetir_contraseña){
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show()
        }else{

            progressBar.visibility = View.VISIBLE

            auth.createUserWithEmailAndPassword(correo,contraseña)
                .addOnCompleteListener(this){
                        task ->
                    if(task.isComplete){
                        val user: FirebaseUser? = auth.currentUser
                        verifyEmail(user)

                        val usuario = Usuarios(nombres,apellidos,correo,direccion_usuario,num_identificacion,celular,foto)

                        val database = FirebaseDatabase.getInstance()
                        val myRef = database.getReference("Usuarios")

                        myRef.child(num_identificacion).setValue(usuario)

                        action()
                    }
                }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
