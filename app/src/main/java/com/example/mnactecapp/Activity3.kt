package com.example.mnactecapp

import ElementsList
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.Image
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Activity3 : AppCompatActivity()
{

    object elementShownConstant
    {
        const val ELEMENT = "ELEMENT"
        const val FIELD = "FIELD"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity3)

        val botonpasar: TextView = findViewById(R.id.botonpasar)
        val botonatras: TextView = findViewById(R.id.botonatras)
        val botonidiomas1: TextView = findViewById(R.id.botonidiomas1)
        val txtVwDescripcio: TextView = findViewById(R.id.TxtVwDescripcio)
        val listView: ListView = findViewById(R.id.lvCaract1)
        val imgElement: ImageView = findViewById(R.id.imgShownElement)

        //permetre scroll en el textview
        txtVwDescripcio.movementMethod = android.text.method.ScrollingMovementMethod.getInstance()


        val intent = getIntent()
        val elementShown = intent.getSerializableExtra(elementShownConstant.ELEMENT) as Element
        val field = intent.getIntExtra(elementShownConstant.FIELD,1)

        botonpasar.setOnClickListener {
            // Crear un Intent para abrir Activity2
            val intent = Intent(this, Activity4::class.java)
            intent.putExtra(Activity4.fieldConstant.FIELD, field)
            startActivity(intent)
        }

        botonatras.setOnClickListener {
            // Crear un Intent para abrir Activity1
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }
        botonidiomas1.setOnClickListener {
            // Crear un Intent para abrir idiomas
            val intent = Intent(this, idiomas::class.java)
            startActivity(intent)
        }




        txtVwDescripcio.setText(elementShown.description)
        val imgElementPath = getFilesDir().toString() + "/imgElements/" + elementShown.image
        val bitmap = BitmapFactory.decodeFile(imgElementPath)
        imgElement.setImageBitmap(bitmap)



        val elementsList = ElementsList(this)
        // Simulación de datos para el adaptador (reemplaza esto con tus propios datos)
        val datos = elementsList.filterAndMapElement(elementShown)

        // Configurar el adaptador
        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, datos)

        // Establecer el adaptador en el ListView
        listView.adapter = adaptador
    }

}