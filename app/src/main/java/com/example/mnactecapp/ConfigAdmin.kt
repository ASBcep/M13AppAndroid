package com.example.mnactecapp

import ElementsList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class ConfigAdmin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.config_admin)

        val btnCat = findViewById<Button>(R.id.openFileButtonElementsCat)
        val btnSpa = findViewById<Button>(R.id.openFileButtonElementsSpa)
        val btnEng = findViewById<Button>(R.id.openFileButtonElementsEng)
        var originalLang = -1

        btnCat.setOnClickListener {
            btnLoadJson(ElementManager.idioma)
            /*
            originalLang = ElementManager.idioma
            ElementManager.idioma = 0
            Toast.makeText(this,"Idioma canviat a català", Toast.LENGTH_LONG).show()

            //falta copiar el json que triï l'usuari a la carpeta local

            ElementsList(this)
            ElementManager.idioma = originalLang
            */
        }
        btnSpa.setOnClickListener {
            originalLang = ElementManager.idioma
            ElementManager.idioma = 1
            Toast.makeText(this,"Idioma cambiado a castellano", Toast.LENGTH_LONG).show()
            ElementsList(this)
            ElementManager.idioma = originalLang
        }
        btnEng.setOnClickListener {
            originalLang = ElementManager.idioma
            ElementManager.idioma = 2
            Toast.makeText(this,"Language changed to English", Toast.LENGTH_LONG).show()
            ElementsList(this)
            ElementManager.idioma = originalLang
        }
    }
    private fun btnLoadJson(originalLang: Int)
    {
        //falta: l'usuari tria json

        ElementManager.idioma = 0
        Toast.makeText(this,"Idioma canviat a català", Toast.LENGTH_LONG).show()

        //falta copiar el json que triï l'usuari a la carpeta local

        ElementsList(this)
        ElementManager.idioma = originalLang
    }
}