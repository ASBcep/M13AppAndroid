package com.example.mnactecapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val botonpasar: TextView = findViewById(R.id.botonpasar)
        val botonidiomas1: TextView = findViewById(R.id.botonidiomas1)
        botonpasar.setOnClickListener {
            // Crear un Intent para abrir Activity3
            val intent = Intent(this@MainActivity, Activity3::class.java)
            startActivity(intent)
        }

        botonidiomas1.setOnClickListener {
            // Crear un Intent para abrir idiomas
            val intent = Intent(this@MainActivity, idiomas::class.java)
            startActivity(intent)
        }
        // https://www.youtube.com/watch?v=OprxdkVKEcc
    }

}