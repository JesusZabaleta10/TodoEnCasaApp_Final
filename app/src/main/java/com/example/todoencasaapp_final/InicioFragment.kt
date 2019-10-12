package com.example.todoencasaapp_final


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ViewFlipper
import kotlinx.android.synthetic.main.fragment_inicio.view.*

class InicioFragment : Fragment() {

    lateinit var viewflipper : ViewFlipper
    //val image = intArrayOf(R.drawable.mantenimiento,R.drawable.mantenimiento3,R.drawable.mantenimiento4)
    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_inicio, container, false)

        // ---------------------------------- SPINNER -----------------------------------
        /*val adapter = ArrayAdapter.createFromResource(root.context, R.array.ciudades2, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        root.sp_ciudades.adapter = adapter*/
        // ------------------------------------------------------------------------------

        //------------------ Auto Image Slider -----------------------------
        /*viewflipper = root.findViewById(R.id.v_flipper)

        for(i in 0 until image.size) {
            flip_image(image[i])
        }*/
        // -------------------------------------------------------------------

        root.ciudades.setOnClickListener{
            var intent = Intent(root.context, DepartamentosActivity::class.java)
            startActivityForResult(intent,1)
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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            root.ciudad_usuario.text = data?.extras?.getString("antioquia")
        }
    }

}
