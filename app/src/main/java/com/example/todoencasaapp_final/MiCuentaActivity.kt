package com.example.todoencasaapp_final

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_mi_cuenta.*
import java.util.jar.Manifest

class MiCuentaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mi_cuenta)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        b_cerrar.setOnClickListener{
            onBackPressed()
            Toast.makeText(this,"Sesión cerrada",Toast.LENGTH_SHORT).show()
        }

        //------------ Galeria -------------------------------------------------------
        galeria_mi_cuenta.setOnClickListener {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                // Check runtime permission
                if(checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                    // Permission denied
                    val permission = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permission, PERMISSION_CODE)
                }else{
                    // Permission already granted
                    pickImageFromGalery()
                }
            }else{
                pickImageFromGalery()
            }
        }
        //------------ Galeria -------------------------------------------------------

        //------------ Camara -------------------------------------------------------
        foto_mi_cuenta.setOnClickListener{
            var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i,123)
        }
        //------------ Camara -------------------------------------------------------
    }

    //------------ Galeria -------------------------------------------------------
    private fun pickImageFromGalery() {
        // Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE -> {
                if((grantResults.size) > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                    pickImageFromGalery()
                    // Permission from popup granted
                }else{
                    // Permission from popup denied
                    Toast.makeText(this,"Permiso denegado", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    companion object{
        // Image pick code
        private val IMAGE_PICK_CODE = 1000
        // Permission code
        private val PERMISSION_CODE = 1001
    }
    //------------ Galeria -------------------------------------------------------

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //------------ Camara -------------------------------------------------------
        if(requestCode==123){
            var bmp = data?.extras?.get("data") as Bitmap
            foto_mi_cuenta.setImageBitmap(bmp)
        }
        //------------ Camara -------------------------------------------------------

        //------------ Galeria -------------------------------------------------------
        if(resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            foto_mi_cuenta.setImageURI(data?.data)
        }
        //------------ Galeria -------------------------------------------------------
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
