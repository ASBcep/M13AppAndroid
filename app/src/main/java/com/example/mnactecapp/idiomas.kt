package com.example.mnactecapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class idiomas : AppCompatActivity() {
    //var idioma = "0"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_idiomas)
        val btnCat = findViewById<Button>(R.id.btnCat)
        val btnSpa = findViewById<Button>(R.id.btnSpa)
        val btnEng = findViewById<Button>(R.id.btnEng)

        btnCat.setOnClickListener {
            ElementManager.idioma = 0
            Toast.makeText(this,"Idioma canviat a català", Toast.LENGTH_LONG).show()
            //val intent = Intent(this,MainActivity::class.java)
            setResult(RESULT_OK,intent)
            finish()
        }
        btnSpa.setOnClickListener {
            ElementManager.idioma = 1
            Toast.makeText(this,"Idioma cambiado a castellano", Toast.LENGTH_LONG).show()
            //val intent = Intent(this,MainActivity::class.java)
            setResult(RESULT_OK,intent)
            finish()
        }
        btnEng.setOnClickListener {
            ElementManager.idioma = 2
            Toast.makeText(this,"Language changed to English", Toast.LENGTH_LONG).show()
            //val intent = Intent(this,MainActivity::class.java)
            setResult(RESULT_OK,intent)
            finish()
        }
    }
}