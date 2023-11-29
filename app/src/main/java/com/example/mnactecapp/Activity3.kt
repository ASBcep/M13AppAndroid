package com.example.mnactecapp

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
        val listView: ListView = findViewById(R.id.lvCaract1)

        //permetre scroll en el textview
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

        val shownElement: Element 



// Simulación de datos para el adaptador (reemplaza esto con tus propios datos)
        val datos = arrayOf("Elemento 1", "Elemento 2", "Elemento 3","Elemento 1", "Elemento 2", "Elemento 3"
        ,"Elemento 1", "Elemento 2", "Elemento 3","Elemento 1", "Elemento 2", "Elemento 3","Elemento 1", "Elemento 2", "Elemento 3")

// Configurar el adaptador
        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, datos)

// Establecer el adaptador en el ListView
        listView.adapter = adaptador
    }



}