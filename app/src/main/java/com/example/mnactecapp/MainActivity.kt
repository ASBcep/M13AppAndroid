package com.example.mnactecapp

import ElementsList
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imgElement: ImageView = findViewById(R.id.imgElementMain)
        val txtElement: TextView = findViewById(R.id.act1FrameText)
        val botonpasar: TextView = findViewById(R.id.botonpasar)
        val btnMesInfo: Button = findViewById(R.id.BtnMesInfo)
        val botonidiomas1: TextView = findViewById(R.id.botonidiomas1)
        val btnJsonShortcut = findViewById<Button>(R.id.BtnJsonShortcut)

        val field = 1;
        val elementsList = ElementsList(this)
        val elementsField = elementsList.loadField(field)

        val elementShown = elementsField.find { it.inicialElement }

        // Verificar si se encontró un elemento con inicialElement=true
        try {

            txtElement.text = ElementManager.elements[ElementManager.defaultElement].nameElement
            //imgElement = ElementManager.elements[ElementManager.defaultElement].image
        } catch (e: Exception) {
            if (elementShown != null) {
                val imgElementPath = getFilesDir().toString() + "/imgElements/" + elementShown.image
                val bitmap = BitmapFactory.decodeFile(imgElementPath)
                imgElement.setImageBitmap(bitmap)
                txtElement.text = elementShown.nameElement
            }
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
                intent.putExtra(Activity3.elementShownConstant.FIELD, field)
                startActivity(intent)
            }

            botonidiomas1.setOnClickListener {
                // Crear un Intent para abrir idiomas
                val intent = Intent(this, idiomas::class.java)
                startActivity(intent)
            }
            btnJsonShortcut.setOnClickListener{
                val intent = Intent(this@MainActivity, carregaJson::class.java)
                startActivity(intent)
            }
        }
    }
/*codi branch main 30/11/2023

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonpasar: TextView = findViewById(R.id.botonpasar)
        val btnMesInfo: Button = findViewById(R.id.BtnMesInfo)
        val botonidiomas1: TextView = findViewById(R.id.botonidiomas1)
        val btnJsonShortcut = findViewById<Button>(R.id.BtnJsonShortcut)
        botonpasar.setOnClickListener {
            // Crear un Intent para abrir Activity3
            val intent = Intent(this@MainActivity, Activity3::class.java)
            startActivity(intent)

        }
        // Crear un Intent para abrir Activity3
        btnMesInfo.setOnClickListener {
            val intent = Intent(this@MainActivity, Activity3::class.java)
            startActivity(intent)
        }

        botonidiomas1.setOnClickListener {
            // Crear un Intent para abrir idiomas
            val intent = Intent(this@MainActivity, idiomas::class.java)
            startActivity(intent)
        }

        btnJsonShortcut.setOnClickListener{
            val intent = Intent(this@MainActivity, carregaJson::class.java)
            startActivity(intent)
        }
    }
}
 */