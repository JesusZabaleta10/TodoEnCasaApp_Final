package com.example.todoencasaapp_final

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_contratar.*
import kotlinx.android.synthetic.main.activity_contratar.hora_final
import kotlinx.android.synthetic.main.activity_contratar.hora_inicial
import java.text.SimpleDateFormat
import java.util.*

class ContratarActivity : AppCompatActivity() {

    // ---------- Leer Base de Datos ---------------------
    private lateinit var mDatabase: DatabaseReference   // Tecnico
    private lateinit var mDatabase2: DatabaseReference // Identificacion
    // ---------- Leer Base de Datos ---------------------
    private var identificacion = ""
    private var categoria = ""
    private var subcategoria = ""
    private var servicios2 = ""

    // ----------- Registro con correo y contraseña ------------
    private lateinit var progressBar: ProgressBar
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    // ----------- Registro con correo y contraseña ------------

    var timeFormat = SimpleDateFormat("hh:mm a", Locale.US)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contratar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // ----------- Registro con correo y contraseña ------------
        progressBar = findViewById(R.id.progressBar)
        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()
        // ----------- Registro con correo y contraseña ------------

        // ---------- Leer Base de Datos ---------------------------------------------------------------
        mDatabase2 = FirebaseDatabase.getInstance().getReference().child("Identificacion")

        val datos2 = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()) {
                    identificacion = dataSnapshot.child("identificacion").value.toString()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@ContratarActivity,"Error en la lectura de datos", Toast.LENGTH_SHORT).show()
            }
        }

        mDatabase2.addValueEventListener(datos2)
        // ---------- Leer Base de Datos ---------------------------------------------------------------

        // ---------- Leer Base de Datos ---------------------------------------------------------------
        mDatabase = FirebaseDatabase.getInstance().getReference()

        val datos = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.d("Datos: ",dataSnapshot.value.toString())
                if(dataSnapshot.exists()){
                    categoria = dataSnapshot.child("Tecnico").child(identificacion).child("categoria").value.toString()
                    subcategoria = dataSnapshot.child("Tecnico").child(identificacion).child("subcatergoria").value.toString()
                    servicios2 = categoria + " - " + subcategoria
                    servicio.text = servicios2
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@ContratarActivity,"Error en la lectura de datos", Toast.LENGTH_SHORT).show()
            }
        }

        mDatabase.addValueEventListener(datos)
        // ---------- Leer Base de Datos ---------------------------------------------------------------

        solicitar.setOnClickListener {
            createNewAccount()
        }

        direccion.setOnClickListener {
            var intent = Intent(this, MapsActivity::class.java)
            startActivityForResult(intent,1234)
        }

        //-------------------------------- CALENDARIO ---------------------------------------
        fecha.setOnClickListener {
            val now = Calendar.getInstance()
            val datePicker = DatePickerDialog(this,DatePickerDialog.OnDateSetListener{view,year,month,dayOfMont ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(Calendar.YEAR,year)
                selectedDate.set(Calendar.MONTH,month)
                selectedDate.set(Calendar.DAY_OF_MONTH,dayOfMont)
                fecha.text = "" + dayOfMont + "/" + (month+1) + "/" + year
            },
                now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }
        //-----------------------------------------------------------------------------------

        //-------------------------------- RELOJ ---------------------------------------
        hora_inicial.setOnClickListener {
            val now = Calendar.getInstance()
            val timePicker = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                val selectedTime = Calendar.getInstance()
                selectedTime.set(Calendar.HOUR_OF_DAY,hourOfDay)
                selectedTime.set(Calendar.MINUTE,minute)
                hora_inicial.text = timeFormat.format(selectedTime.time)
            },
                now.get(Calendar.HOUR_OF_DAY),now.get(Calendar.MINUTE),false)
            timePicker.show()
        }

        hora_final.setOnClickListener {
            val now = Calendar.getInstance()
            val timePicker = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                val selectedTime = Calendar.getInstance()
                selectedTime.set(Calendar.HOUR_OF_DAY,hourOfDay)
                selectedTime.set(Calendar.MINUTE,minute)
                hora_final.text = timeFormat.format(selectedTime.time)
            },
                now.get(Calendar.HOUR_OF_DAY),now.get(Calendar.MINUTE),false)
            timePicker.show()
        }
        //-------------------------------------------------------------------------------
    }

    // ----------- Crear tabla de Datos Servicios --------------------------
    private fun createNewAccount(){
        val dia = fecha.text.toString()
        val hora = hora_inicial.text.toString() + " a " + hora_final.text.toString()
        val direccion_usuario = direccion.text.toString()
        val celular = celular_servicio.text.toString()
        val descripcion = necesidad.text.toString()

        if((servicio.text  == "") || (dia == "") || (hora_inicial.text.toString() == "")
            || (hora_final.text.toString() == "") || (direccion_usuario == "")|| (celular == "")|| (descripcion == "")){
            Toast.makeText(this, "Hay campos sin llenar", Toast.LENGTH_SHORT).show()
        }else{

            progressBar.visibility = View.VISIBLE

            // -------- Añadir la ciudad del usuario a la base de datos --------------------------
            val ref = FirebaseDatabase.getInstance().getReference("DatosServicio")
            val datosServicio = DatosServicio(servicios2,dia,hora,direccion_usuario,celular,descripcion)
            ref.child(identificacion).setValue(datosServicio)

            startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
            Toast.makeText(this,"Técnico contratado",Toast.LENGTH_SHORT).show()
        }
    }
    // ----------- Crear tabla de Datos Servicios --------------------------

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1234 && resultCode == Activity.RESULT_OK){
            direccion.text = data?.extras?.getString("ubicacion")
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
