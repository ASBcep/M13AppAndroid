package com.example.mnactecapp


import ElementsList
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Activity4 : AppCompatActivity() {

    object fieldConstant{
        const val FIELD = "FIELD"
    }

    //carrego el llistat d'elements LOCAL des de la llista GLOBAL
    val elements = ElementManager.elements

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity4)

        val botonpasar: TextView = findViewById(R.id.botonpasar)
        val botonatras: TextView = findViewById(R.id.botonatras)
        val botonidiomas1: TextView = findViewById(R.id.botonidiomas1)

        botonpasar.setOnClickListener {
            // Crear un Intent para abrir Activity5
            val intent = Intent(this, Activity5::class.java)
            startActivity(intent)
        }

        botonatras.setOnClickListener {
            // Crear un Intent para abrir Activity3
            val intent = Intent(this, Activity3::class.java)

            startActivity(intent)
        }

        val intent = getIntent()
        val field = intent.getIntExtra(fieldConstant.FIELD,1)

        val elementsList = ElementsList(this)
        val elementsField = elementsList.loadField(field)

        val listAltresElements = findViewById<RecyclerView>(R.id.ListAltresElements)
        val layoutManager = GridLayoutManager(this, 3)
        listAltresElements.layoutManager = layoutManager

        val adapter = ElementAdapter(elementsField)
        listAltresElements.adapter = adapter

        botonidiomas1.setOnClickListener {
            // Crear un Intent para abrir idiomas
            val intent = Intent(this@Activity4, idiomas::class.java)
            startActivity(intent)
        }
    }
}
/*codi branch main 30/11/2023

import android.content.Intent
import android.os.Bundle
import android.widget.GridView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Activity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity4)

        //carrego el llistat d'elements LOCAL des de la llista GLOBAL
        val elements = ElementManager.elements

        val botonpasar: TextView = findViewById(R.id.botonpasar)
        val botonatras: TextView = findViewById(R.id.botonatras)
        val botonidiomas1: TextView = findViewById(R.id.botonidiomas1)
        botonpasar.setOnClickListener {
            // Crear un Intent para abrir Activity2
            val intent = Intent(this@Activity4, Activity5::class.java)
            startActivity(intent)
        }

        botonatras.setOnClickListener {
            // Crear un Intent para abrir Activity2
            val intent = Intent(this@Activity4, Activity3::class.java)
            startActivity(intent)
        }


        /*var elements = mutableListOf(
            Element("MOTOCICLETA SANGLAS 400", R.drawable.moto),
            Element("MOTOCICLETA SANGLAS 401", R.drawable.moto),
            Element("MOTOCICLETA SANGLAS 402", R.drawable.moto),
            Element("MOTOCICLETA SANGLAS 403", R.drawable.moto),
            Element("MOTOCICLETA SANGLAS 404", R.drawable.moto),
            Element("MOTOCICLETA SANGLAS 405", R.drawable.moto),
            Element("MOTOCICLETA SANGLAS 406", R.drawable.moto),
            Element("MOTOCICLETA SANGLAS 407", R.drawable.moto),
            Element("MOTOCICLETA SANGLAS 408", R.drawable.moto),
            Element("MOTOCICLETA SANGLAS 409", R.drawable.moto),
            Element("MOTOCICLETA SANGLAS 410", R.drawable.moto),
        )*/

        val listAltresElements = findViewById<RecyclerView>(R.id.ListAltresElements)
        val layoutManager = GridLayoutManager(this, 3)
        listAltresElements.layoutManager = layoutManager

        val adapter = ElementAdapter(elements)
        listAltresElements.adapter = adapter
        botonidiomas1.setOnClickListener {
            // Crear un Intent para abrir idiomas
            val intent = Intent(this@Activity4, idiomas::class.java)
            startActivity(intent)
        }
    }
}
 */