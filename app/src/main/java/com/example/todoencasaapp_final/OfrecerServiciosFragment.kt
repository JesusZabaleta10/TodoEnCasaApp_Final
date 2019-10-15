package com.example.todoencasaapp_final

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_ofrecer_servicios.*
import kotlinx.android.synthetic.main.fragment_ofrecer_servicios.view.*
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class OfrecerServiciosFragment : Fragment() {

    var timeFormat = SimpleDateFormat("hh:mm a", Locale.US)

    // ----------- Registro con correo y contraseña ------------
    private lateinit var progressBar: ProgressBar
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    // ----------- Registro con correo y contraseña ------------

    private var bandera = 0
    private val PICK_IMAGE_REQUEST = 1234
    private var filePath: Uri ?= null
    private var cr: ContentResolver ?= null
    private lateinit var root: View
    private var categoria = ""
    private var subcategoria = ""
    private var dias = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_ofrecer_servicios, container, false)

        // ----------- Registro con correo y contraseña ------------
        progressBar = root.findViewById(R.id.progressBar)
        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()
        // ----------- Registro con correo y contraseña ------------

        // ------------------ RELOJ -----------------------------------
        root.hora_inicial.setOnClickListener {
            val now = Calendar.getInstance()
            val timePicker = TimePickerDialog(root.context, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                val selectedTime = Calendar.getInstance()
                selectedTime.set(Calendar.HOUR_OF_DAY,hourOfDay)
                selectedTime.set(Calendar.MINUTE,minute)
                root.hora_inicial.text = timeFormat.format(selectedTime.time)
            },
                now.get(Calendar.HOUR_OF_DAY),now.get(Calendar.MINUTE),false)
            timePicker.show()
        }

        root.hora_final.setOnClickListener {
            val now = Calendar.getInstance()
            val timePicker = TimePickerDialog(root.context, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                val selectedTime = Calendar.getInstance()
                selectedTime.set(Calendar.HOUR_OF_DAY,hourOfDay)
                selectedTime.set(Calendar.MINUTE,minute)
                root.hora_final.text = timeFormat.format(selectedTime.time)
            },
                now.get(Calendar.HOUR_OF_DAY),now.get(Calendar.MINUTE),false)
            timePicker.show()
        }
        // ------------------ RELOJ -----------------------------------

        //-------------------------------- CALENDARIO ---------------------------------------
        root.nacimiento.setOnClickListener {
            val now = Calendar.getInstance()
            val datePicker = DatePickerDialog(root.context, DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMont ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(Calendar.YEAR,year)
                selectedDate.set(Calendar.MONTH,month)
                selectedDate.set(Calendar.DAY_OF_MONTH,dayOfMont)
                root.nacimiento.text = "" + dayOfMont + "/" + (month+1) + "/" + year
            },
                now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }
        //-----------------------------------------------------------------------------------

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

        //------------ Camara -------------------------------------------------------
        root.certificado2.setOnClickListener{
            var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i,456)
        }
        //------------ Camara -------------------------------------------------------

        root.registrar_tecnico.setOnClickListener{
            createNewAccount()
        }

        root.direccion_tecnico.setOnClickListener {
            var intent = Intent(root.context, MapsActivity::class.java)
            startActivityForResult(intent,789)
        }

        // ---------------------------------- SPINNER -----------------------------------

        // --------------- Spinner de dias -------------------------

        val adapter2 = ArrayAdapter.createFromResource(root.context, R.array.dias, android.R.layout.simple_spinner_item)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        root.sp_dias.adapter = adapter2

        root.sp_dias.onItemSelectedListener = object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(context, "Selecciona una opción", Toast.LENGTH_SHORT).show()
            }

            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                dias = root.sp_dias.selectedItem.toString()
            }
        }
        // --------------- Spinner de dias -------------------------

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

                //----------------- Spiner de SubCategorías ----------------------------
                if(categoria == "Carpintería"){
                    val adapter = ArrayAdapter.createFromResource(root.context, R.array.carpinteria, android.R.layout.simple_spinner_item)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    root.sp_subcategorias.adapter = adapter

                    root.sp_subcategorias.onItemSelectedListener = object : AdapterView.OnItemClickListener,
                        AdapterView.OnItemSelectedListener {
                        override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}

                        override fun onNothingSelected(p0: AdapterView<*>?) {
                            Toast.makeText(context, "Selecciona una opción", Toast.LENGTH_SHORT).show()
                        }

                        override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            subcategoria = root.sp_subcategorias.selectedItem.toString()
                        }
                    }
                }
                if(categoria == "Electricidad"){
                    val adapter = ArrayAdapter.createFromResource(root.context, R.array.electricidad, android.R.layout.simple_spinner_item)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    root.sp_subcategorias.adapter = adapter

                    root.sp_subcategorias.onItemSelectedListener = object : AdapterView.OnItemClickListener,
                        AdapterView.OnItemSelectedListener {
                        override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}

                        override fun onNothingSelected(p0: AdapterView<*>?) {
                            Toast.makeText(context, "Selecciona una opción", Toast.LENGTH_SHORT).show()
                        }

                        override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            subcategoria = root.sp_subcategorias.selectedItem.toString()
                        }
                    }
                }
                if(categoria == "Refrigeración"){
                    val adapter = ArrayAdapter.createFromResource(root.context, R.array.refrigeracion, android.R.layout.simple_spinner_item)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    root.sp_subcategorias.adapter = adapter

                    root.sp_subcategorias.onItemSelectedListener = object : AdapterView.OnItemClickListener,
                        AdapterView.OnItemSelectedListener {
                        override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}

                        override fun onNothingSelected(p0: AdapterView<*>?) {
                            Toast.makeText(context, "Selecciona una opción", Toast.LENGTH_SHORT).show()
                        }

                        override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            subcategoria = root.sp_subcategorias.selectedItem.toString()
                        }
                    }
                }
                if(categoria == "Limpieza"){
                    val adapter = ArrayAdapter.createFromResource(root.context, R.array.limpieza, android.R.layout.simple_spinner_item)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    root.sp_subcategorias.adapter = adapter

                    root.sp_subcategorias.onItemSelectedListener = object : AdapterView.OnItemClickListener,
                        AdapterView.OnItemSelectedListener {
                        override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}

                        override fun onNothingSelected(p0: AdapterView<*>?) {
                            Toast.makeText(context, "Selecciona una opción", Toast.LENGTH_SHORT).show()
                        }

                        override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            subcategoria = root.sp_subcategorias.selectedItem.toString()
                        }
                    }
                }
                if(categoria == "Mecánica"){
                    val adapter = ArrayAdapter.createFromResource(root.context, R.array.mecanica, android.R.layout.simple_spinner_item)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    root.sp_subcategorias.adapter = adapter

                    root.sp_subcategorias.onItemSelectedListener = object : AdapterView.OnItemClickListener,
                        AdapterView.OnItemSelectedListener {
                        override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}

                        override fun onNothingSelected(p0: AdapterView<*>?) {
                            Toast.makeText(context, "Selecciona una opción", Toast.LENGTH_SHORT).show()
                        }

                        override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            subcategoria = root.sp_subcategorias.selectedItem.toString()
                        }
                    }
                }
                if(categoria == "Acarreos"){
                    val adapter = ArrayAdapter.createFromResource(root.context, R.array.acarreos, android.R.layout.simple_spinner_item)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    root.sp_subcategorias.adapter = adapter

                    root.sp_subcategorias.onItemSelectedListener = object : AdapterView.OnItemClickListener,
                        AdapterView.OnItemSelectedListener {
                        override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}

                        override fun onNothingSelected(p0: AdapterView<*>?) {
                            Toast.makeText(context, "Selecciona una opción", Toast.LENGTH_SHORT).show()
                        }

                        override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            subcategoria = root.sp_subcategorias.selectedItem.toString()
                        }
                    }
                }
                //----------------- Spiner de SubCategorías ----------------------------
            }
        }
        // ---------------------------------- SPINNER -----------------------------------
    return root
    }

    // ----------- Registro con correo y contraseña ------------
    private fun createNewAccount(){
        val nombres = nombre_tecnico.text.toString()
        val apellidos = apellido_tecnico.text.toString()
        val fecha = nacimiento.text.toString()
        val num_identificacion = identificacion_tecnico.text.toString()
        val celular = celular_tecnico.text.toString()
        val correo = correo_tecnico.text.toString()
        val contraseña = contraseña.text.toString()
        val horario = dias + " " + root.hora_inicial.text.toString() + " a " + root.hora_final.text.toString()
        val descripcion_tecnico = descripcion.text.toString()
        val experiencia_tecnico = experiencia.text.toString() + " meses"
        val direccion = direccion_tecnico.text.toString()
        val categoria_tecnico = categoria
        val subcategoria_tecnico = subcategoria
        val foto = "https://firebasestorage.googleapis.com/v0/b/fir-application-e8f25.appspot.com/o/usuarios%2F1234?alt=media&token=c1b89095-f28d-489f-8a6c-cf5f9dd71179"
        val certificado = "https://firebasestorage.googleapis.com/v0/b/fir-application-e8f25.appspot.com/o/usuarios%2F12345?alt=media&token=63a90761-5545-41b5-995b-975973bbe510"

        if((nombres == "") || (apellidos == "")|| (fecha == "") || (num_identificacion == "") || (celular == "")
            || (correo == "") || (contraseña == "") || (descripcion_tecnico == "") || (experiencia_tecnico == "")
            || (horario == "") || (direccion == "") || (categoria_tecnico == "") || (subcategoria_tecnico == "")
            || (root.hora_inicial.text.toString() == "") || (root.hora_final.text.toString() == "")){
            Toast.makeText(root.context, "Hay campos sin llenar", Toast.LENGTH_SHORT).show()
        }else if(contraseña.length < 6){
            Toast.makeText(root.context, "La contraseña no tiene los 6 caracteres mínimos", Toast.LENGTH_SHORT).show()
        }else{

            progressBar.visibility = View.VISIBLE

            auth.createUserWithEmailAndPassword(correo,contraseña)
                .addOnCompleteListener{ task ->
                    if(task.isComplete){
                        val user: FirebaseUser? = auth.currentUser
                        verifyEmail(user)

                        val tecnico = Tecnicos(nombres,apellidos,fecha,num_identificacion,celular,correo,descripcion_tecnico,
                            experiencia_tecnico,horario,direccion,categoria_tecnico,subcategoria_tecnico,foto,certificado)

                        val database = FirebaseDatabase.getInstance()
                        val myRef = database.getReference("Tecnico")

                        myRef.child(num_identificacion).setValue(tecnico)

                        action()
                    }
                }
        }
    }

    private fun action(){
        FirebaseAuth.getInstance().signOut()
        Toast.makeText(root.context,"Técnico registrado",Toast.LENGTH_SHORT).show()

        nombre_tecnico.setText("")
        apellido_tecnico.setText("")
        nacimiento.text = ""
        identificacion_tecnico.setText("")
        celular_tecnico.setText("")
        correo_tecnico.setText("")
        contraseña.setText("")
        hora_inicial.text = ""
        hora_final.text = ""
        descripcion.setText("")
        experiencia.setText("")
        direccion_tecnico.text = ""
        //getActivity()?.finish()
    }

    private fun verifyEmail(user: FirebaseUser?){
        user?.sendEmailVerification()
            ?.addOnCompleteListener{ task ->
                if(task.isComplete){
                    Toast.makeText(root.context, "Correo de verificación enviado", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(root.context, "Error al enviar el correo", Toast.LENGTH_SHORT).show()
                }
            }
    }
    // ----------- Registro con correo y contraseña ------------

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
        //------------ Camara -------------------------------------------------------

        //------------ Camara -------------------------------------------------------
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