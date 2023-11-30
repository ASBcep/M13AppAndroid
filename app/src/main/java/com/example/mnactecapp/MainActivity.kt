package com.example.mnactecapp

import ElementsList
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imgElement: ImageView = findViewById(R.id.imgElementMain)
        val txtElement: TextView = findViewById(R.id.act1FrameText)
        val botonpasar: TextView = findViewById(R.id.botonpasar)
        val btnMesInfo: Button = findViewById(R.id.BtnMesInfo)
        val botonidiomas1: TextView = findViewById(R.id.botonidiomas1)

        val elementsList = ElementsList(this)
        val field = 1;
        val elementsField = elementsList.elementsField1

        val elementShown = elementsField.find { it.inicialElement }

        // Verificar si se encontró un elemento con inicialElement=true
        if (elementShown != null) {
            // Utilizar el elemento encontrado
            // Aquí puedes actualizar las vistas (ImageView, TextView, etc.) con la información del elemento
            // Por ejemplo:
            val imgElementPath = getFilesDir().toString() + "/imgElements/" + elementShown.image
            val bitmap = BitmapFactory.decodeFile(imgElementPath)
            imgElement.setImageBitmap(bitmap)
            txtElement.text = elementShown.nameElement
        }

            botonpasar.setOnClickListener {
                // Crear un Intent para abrir Activity3
                val intent = Intent(this, Activity3::class.java)
                intent.putExtra(Activity3.elementShownConstant.ELEMENT, elementShown)
                intent.putExtra(Activity3.elementShownConstant.FIELD, field)
                startActivity(intent)

            }
            // Crear un Intent para abrir Activity3
            btnMesInfo.setOnClickListener {
                val intent = Intent(this, Activity3::class.java)
                intent.putExtra(Activity3.elementShownConstant.ELEMENT, elementShown)
                startActivity(intent)
            }

            botonidiomas1.setOnClickListener {
                // Crear un Intent para abrir idiomas
                val intent = Intent(this, idiomas::class.java)
                startActivity(intent)
            }
        }
    }