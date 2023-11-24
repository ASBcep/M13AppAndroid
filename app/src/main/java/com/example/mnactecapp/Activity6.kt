package com.example.mnactecapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Activity6 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity6)
        val botonatras: TextView = findViewById(R.id.botonatras)
        val botonidiomas1: TextView = findViewById(R.id.botonidiomas1)
        val btnEasy: Button = findViewById(R.id.BtnFacil)
        val btnNormal: Button = findViewById(R.id.BtnNormal)
        val btnHard: Button = findViewById(R.id.BtnDificil)


        botonatras.setOnClickListener {
            // Crear un Intent para abrir Activity2
            val intent = Intent(this@Activity6, Activity5::class.java)
            startActivity(intent)
        }
        botonidiomas1.setOnClickListener {
            // Crear un Intent para abrir idiomas
            val intent = Intent(this@Activity6, idiomas::class.java)
            startActivity(intent)
        }
        btnEasy.setOnClickListener{
            val dificulty = 1;
            val intent = Intent(this, Launcher::class.java)
            intent.putExtra(Launcher.launcher.DIFICULTY, dificulty)
            startActivity(intent)
        }
        btnNormal.setOnClickListener{
            val dificulty = 2;
            val intent = Intent(this, Launcher::class.java)
            intent.putExtra(Launcher.launcher.DIFICULTY, dificulty)
            startActivity(intent)
        }
        btnHard.setOnClickListener{
            val dificulty = 3;
            val intent = Intent(this, Launcher::class.java)
            intent.putExtra(Launcher.launcher.DIFICULTY, dificulty)
            startActivity(intent)
        }
    }
}