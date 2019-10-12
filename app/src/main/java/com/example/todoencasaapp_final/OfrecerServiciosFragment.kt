package com.example.todoencasaapp_final

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.PermissionChecker.checkSelfPermission
import kotlinx.android.synthetic.main.activity_mi_cuenta.*
import kotlinx.android.synthetic.main.fragment_ofrecer_servicios.*
import kotlinx.android.synthetic.main.fragment_ofrecer_servicios.view.*

class OfrecerServiciosFragment : Fragment() {

    private lateinit var root: View
    private var categoria = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_ofrecer_servicios, container, false)

         //------------ Galeria -------------------------------------------------------
        /*root.certificado2.setOnClickListener {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                // Check runtime permission
                if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
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
        }*/
        //------------ Galeria -------------------------------------------------------


        //------------ Camara -------------------------------------------------------
        root.foto.setOnClickListener{
            var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i,123)
        }
        //------------ Camara -------------------------------------------------------

        root.registrar_tecnico.setOnClickListener{
            startActivity(Intent(root.context,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
            Toast.makeText(root.context,"Técnico registrado",Toast.LENGTH_SHORT).show()
        }

        root.certificado.setOnClickListener{
            var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i,456)
        }

        // ---------------------------------- SPINNER -----------------------------------
        val adapter = ArrayAdapter.createFromResource(root.context, R.array.categorias, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        root.sp_categorias.adapter = adapter

        root.sp_categorias.onItemSelectedListener = object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(context, "Selecciona una opción", Toast.LENGTH_SHORT).show()
            }

            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                categoria = root.sp_categorias.selectedItem.toString()
                //Toast.makeText(context, categoria, Toast.LENGTH_SHORT).show()

                //----------------- Spiner de SubCategorías ----------------------------
                if(categoria == "Carpintería"){
                    val adapter = ArrayAdapter.createFromResource(root.context, R.array.carpinteria, android.R.layout.simple_spinner_item)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    root.sp_subcategorias.adapter = adapter
                }
                if(categoria == "Electricidad"){
                    val adapter = ArrayAdapter.createFromResource(root.context, R.array.electricidad, android.R.layout.simple_spinner_item)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    root.sp_subcategorias.adapter = adapter
                }
                if(categoria == "Refrigeración"){
                    val adapter = ArrayAdapter.createFromResource(root.context, R.array.refrigeracion, android.R.layout.simple_spinner_item)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    root.sp_subcategorias.adapter = adapter
                }
                if(categoria == "Limpieza"){
                    val adapter = ArrayAdapter.createFromResource(root.context, R.array.limpieza, android.R.layout.simple_spinner_item)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    root.sp_subcategorias.adapter = adapter
                }
                if(categoria == "Mecánica"){
                    val adapter = ArrayAdapter.createFromResource(root.context, R.array.mecanica, android.R.layout.simple_spinner_item)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    root.sp_subcategorias.adapter = adapter
                }
                if(categoria == "Acarreos"){
                    val adapter = ArrayAdapter.createFromResource(root.context, R.array.acarreos, android.R.layout.simple_spinner_item)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    root.sp_subcategorias.adapter = adapter
                }
                // -----------------------------------------------------------------------
            }
        }
        // ------------------------------------------------------------------------------
    return root
    }

    //------------ Galeria -------------------------------------------------------
    //private fun pickImageFromGalery() {
        // Intent to pick image
    //    val intent = Intent(Intent.ACTION_PICK)
    //    intent.type = "image/*"
    //    startActivityForResult(intent, IMAGE_PICK_CODE)
    //}

    /*override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE -> {
                if((grantResults.size) > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                    pickImageFromGalery()
                    // Permission from popup granted
                }else{
                    // Permission from popup denied
                    Toast.makeText(root.context,"Permiso denegado", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }*/

    /*companion object{
        // Image pick code
        private val IMAGE_PICK_CODE = 1000
        // Permission code
        private val PERMISSION_CODE = 1001
    }*/
    //------------ Galeria -------------------------------------------------------

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //------------ Camara -------------------------------------------------------
        if(requestCode==123){
            var bmp = data?.extras?.get("data") as Bitmap
            root.foto.setImageBitmap(bmp)
        }

        if(requestCode==456){
            var bmp = data?.extras?.get("data") as Bitmap
            root.certificado2.setImageBitmap(bmp)
        }
        //------------ Camara -------------------------------------------------------

        //------------ Galeria -------------------------------------------------------
        /*if(resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            root.foto.setImageURI(data?.data)
        }*/
        //------------ Galeria -------------------------------------------------------
    }
}