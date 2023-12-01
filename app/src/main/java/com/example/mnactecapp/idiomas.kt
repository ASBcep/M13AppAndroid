package com.example.mnactecapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class idiomas : AppCompatActivity() {
    var idioma = "0"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_idiomas)
        val btnCat = findViewById<Button>(R.id.btnCat)

        btnCat.setOnClickListener {
            //ElementManager.idioma = idioma.toInt()
        }
    }
}