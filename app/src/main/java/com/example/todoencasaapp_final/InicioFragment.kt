package com.example.todoencasaapp_final


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import android.widget.ViewFlipper
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_inicio.view.*

class InicioFragment : Fragment() {

    lateinit var viewflipper : ViewFlipper
    val image = intArrayOf(R.drawable.mantenimiento,R.drawable.mantenimiento3,R.drawable.mantenimiento4)
    private lateinit var root: View
    // ---------- Leer Base de Datos ---------------------
    private lateinit var mDatabase: DatabaseReference //Ciudad
    // ---------- Leer Base de Datos ---------------------

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_inicio, container, false)

        //------------------ Auto Image Slider -----------------------------
        viewflipper = root.findViewById(R.id.v_flipper)

        for(i in 0 until image.size) {
            flip_image(image[i])
        }
        // -------------------------------------------------------------------

        // ---------- Leer Base de Datos ---------------------------------------------------------------
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Ciudad")

        val datos = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()) {
                    root.ciudad_usuario.text = dataSnapshot.child("ciudad").value.toString()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(root.context,"Error en la lectura de datos", Toast.LENGTH_SHORT).show()
            }
        }

        mDatabase.addValueEventListener(datos)
        // ---------- Leer Base de Datos ---------------------------------------------------------------

        root.ciudades.setOnClickListener{
            startActivity(Intent(root.context, DepartamentosActivity::class.java))
        }

        root.carpinteria.setOnClickListener{
            startActivity(Intent(root.context, CarpinteriaActivity::class.java))
        }

        root.electricidad.setOnClickListener{
            startActivity(Intent(root.context, ElectricidadActivity::class.java))
        }

        root.refrigeracion.setOnClickListener{
            startActivity(Intent(root.context, RefrigeracionActivity::class.java))
        }

        root.limpieza.setOnClickListener{
            startActivity(Intent(root.context, LimpiezaActivity::class.java))
        }

        root.mecanica.setOnClickListener{
            startActivity(Intent(root.context, MecanicaActivity::class.java))
        }

        root.acarreo.setOnClickListener{
            startActivity(Intent(root.context, AcarreosActivity::class.java))
        }

        return root
    }

    //------------------ Auto Image Slider -----------------------------
    fun flip_image(i : Int) {
        val view = ImageView(root.context)
        view.setBackgroundResource(i)
        viewflipper.addView(view)
        viewflipper.setFlipInterval(3000)
        viewflipper.setAutoStart(true)
        viewflipper.setInAnimation(root.context, android.R.anim.slide_in_left)
        viewflipper.setOutAnimation(root.context, android.R.anim.slide_out_right)
    }
}
