package com.example.mnactecapp

import ElementsList
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.Image
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File

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
        val btnPlay: Button = findViewById(R.id.btnPlay)
        val btnList: Button = findViewById(R.id.btnList)
        val txtVwDescripcio: TextView = findViewById(R.id.TxtVwDescripcio)
        val listView: ListView = findViewById(R.id.lvCaract1)
        val imgVwElement: ImageView = findViewById(R.id.imgShownElement)
        val act3FrameText: TextView = findViewById(R.id.act3FrameText)

        //permetre scroll en el textview
        txtVwDescripcio.movementMethod = android.text.method.ScrollingMovementMethod.getInstance()

        val intent = getIntent()
        val elementShown = intent.getSerializableExtra(elementShownConstant.ELEMENT) as Element
        val field = intent.getIntExtra(elementShownConstant.FIELD,1)
        act3FrameText.text = elementShown.nameElement


        botonpasar.setOnClickListener {
            // Crear un Intent para abrir Activity4
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
        btnList.setOnClickListener {
            // Crear un Intent para abrir Activity4 (llistat d'elements)
            val intent = Intent(this, Activity4::class.java)
            startActivity(intent)
        }
        btnPlay.setOnClickListener {
            // Crear un Intent para abrir Activity6 (dificultat joc)
            val intent = Intent(this, Activity6::class.java)
            startActivity(intent)
        }

        txtVwDescripcio.setText(elementShown.description)
        val imgElementPath = getFilesDir().toString() + "/imgelements/" + elementShown.image
        //val bitmap = BitmapFactory.decodeFile(imgElementPath)
        if (File(imgElementPath).exists()) {
            val bitmap = BitmapFactory.decodeFile(imgElementPath)
            imgVwElement.setImageBitmap(bitmap)
        } else {
            imgVwElement.setImageResource(R.drawable.defaultelement)
        }



        val elementsList = ElementsList(this)
        // Simulación de datos para el adaptador (reemplaza esto con tus propios datos)
        val datos = elementsList.filterAndMapElementV2(elementShown)

        // Configurar el adaptador
        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, datos)

        // Establecer el adaptador en el ListView
        listView.adapter = adaptador
    }

}
/*codi branch main 30/11/2023 (crec que és igual, repassar)

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Activity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity3)

        val botonpasar: TextView = findViewById(R.id.botonpasar)
        val botonatras: TextView = findViewById(R.id.botonatras)
        val botonidiomas1: TextView = findViewById(R.id.botonidiomas1)
        val txtVwDescripcio: TextView = findViewById(R.id.TxtVwDescripcio)

        //permetre scroll en el textview TxtVwDescripcio
        txtVwDescripcio.movementMethod = android.text.method.ScrollingMovementMethod.getInstance()

        botonpasar.setOnClickListener {
            // Crear un Intent para abrir Activity2
            val intent = Intent(this@Activity3, Activity4::class.java)
            startActivity(intent)
        }

        botonatras.setOnClickListener {
            // Crear un Intent para abrir Activity1
            val intent = Intent(this@Activity3, MainActivity::class.java)
            startActivity(intent)
        }
        botonidiomas1.setOnClickListener {
            // Crear un Intent para abrir idiomas
            val intent = Intent(this@Activity3, idiomas::class.java)
            startActivity(intent)
        }
        val listView: ListView = findViewById(R.id.lvCaract1)

// Simulación de datos para el adaptador (reemplaza esto con tus propios datos)
        val datos = arrayOf("Número d'inventari: 12345", "Any d'ingrés: 1989", "Alçada: 2m")

// Configurar el adaptador
        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, datos)

// Establecer el adaptador en el ListView
        listView.adapter = adaptador
    }
}
 */