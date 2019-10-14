package com.example.todoencasaapp_final

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContentResolverCompat
import kotlinx.android.synthetic.main.fragment_ofrecer_servicios.view.*
import java.io.IOException

class OfrecerServiciosFragment : Fragment() {

    private var bandera = 0
    private val PICK_IMAGE_REQUEST = 1234
    private var filePath: Uri ?= null
    private var cr: ContentResolver ?= null
    private lateinit var root: View
    private var categoria = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_ofrecer_servicios, container, false)

        //------------ Galeria -------------------------------------------------------
        root.galeria.setOnClickListener {
            galeria()
            bandera = 1
        }

        root.certificado.setOnClickListener {
            galeria()
            bandera = 2
        }
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

        //------------ Camara -------------------------------------------------------
        root.certificado.setOnClickListener{
            var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i,456)
        }
        //------------ Camara -------------------------------------------------------

        root.direccion_tecnico.setOnClickListener {
            var intent = Intent(root.context, MapsActivity::class.java)
            startActivityForResult(intent,789)
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
                //----------------- Spiner de SubCategorías ----------------------------
            }
        }
        // ---------------------------------- SPINNER -----------------------------------
    return root
    }

    fun galeria(){
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent,"Seleccionar imagen"), PICK_IMAGE_REQUEST)
    }

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
        if(requestCode == PICK_IMAGE_REQUEST &&
            resultCode == Activity.RESULT_OK &&
            data != null && data.data != null &&
            bandera == 1)
        {
            filePath = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(cr,filePath)
                root.foto!!.setImageBitmap(bitmap)
            }catch (e: IOException)
            {
                e.printStackTrace()
            }
        }

        if(requestCode == PICK_IMAGE_REQUEST &&
            resultCode == Activity.RESULT_OK &&
            data != null && data.data != null &&
            bandera == 2)
        {
            filePath = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(cr,filePath)
                root.certificado2!!.setImageBitmap(bitmap)
            }catch (e: IOException)
            {
                e.printStackTrace()
            }
        }
        //------------ Galeria -------------------------------------------------------

        if(requestCode == 789 && resultCode == Activity.RESULT_OK){
            root.direccion_tecnico.text = data?.extras?.getString("ubicacion")
        }
    }
}