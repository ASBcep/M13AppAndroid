package com.example.mnactecapp


import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.FileReader

class Activity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity4)

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


        var elements = getElements()

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

    fun getElements(): MutableList<Element> {

        val jsonFilePath = getFilesDir().toString() + "/json/elements.json"
        val jsonFile = FileReader(jsonFilePath)
        val listElementsType = object : TypeToken<MutableList<Element>>() {}.type

        return Gson().fromJson(jsonFile, listElementsType)
    }
}