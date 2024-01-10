package com.example.mnactecapp

import ElementsList
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Activity4 : AppCompatActivity() {

    val elements = ElementManager.elements
    val fields = ElementManager.fields
    var indexField = ElementManager.indexField
    val totalField = ElementManager.fields.count() - 1

    private val handler = Handler()
    private val inactivityRunnable = Runnable {
        reiniciarActividad()
    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                reiniciarActividad()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity4)

        val act1FrameText: TextView = findViewById(R.id.act1FrameText)
        val botonidiomas1: TextView = findViewById(R.id.botonidiomas1)
        val btnChangeField: Button = findViewById(R.id.btnChangeField)
        val btnMainScreen: Button = findViewById(R.id.btnMainScreen)

        act1FrameText.text = fields[indexField].nameField

        when (ElementManager.idioma) {
            0 -> {
                btnChangeField.text = getString(R.string.btnChangeFieldCAT)
                botonidiomas1.text = getString(R.string.btn_idioma)
                btnMainScreen.text = getString(R.string.btnIniciCAT)
            }
            1 -> {
                btnChangeField.text = getString(R.string.btnChangeFieldSPA)
                botonidiomas1.text = getString(R.string.btn_idioma)
                btnMainScreen.text = getString(R.string.btnIniciSPA)
            }
            2 -> {
                btnChangeField.text = getString(R.string.btnChangeFieldENG)
                botonidiomas1.text = getString(R.string.btn_language)
                btnMainScreen.text = getString(R.string.btnIniciENG)
            }
        }

        val elementsList = ElementsList(this)
        var elementsField = elementsList.loadField(indexField)

        val listAltresElements = findViewById<RecyclerView>(R.id.ListAltresElements)
        val layoutManager = GridLayoutManager(this, 3)
        listAltresElements.layoutManager = layoutManager

        val adapter = ElementAdapter(elementsField) { selectedElement ->
            navigateToActivity3(selectedElement)
        }

        listAltresElements.adapter = adapter

        botonidiomas1.setOnClickListener {
            val intent = Intent(this, idiomas::class.java)
            getResult.launch(intent)
        }

        btnChangeField.setOnClickListener {
            var fieldContent = false
            do {
                fieldSelector()
                elementsField = elementsList.loadField(indexField)
                if (elementsField.count() != 0) {
                    fieldContent = true
                } else {
                    if (ElementManager.debug) {
                        Toast.makeText(
                            this,
                            "L'àmbit $indexField: ${fields[indexField].nameField} no té contingut.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } while (!fieldContent)
            act1FrameText.text = fields[indexField].nameField
            reiniciarActividad()
        }

        btnMainScreen.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Detectar toques en la vista raíz
        val rootView = findViewById<View>(android.R.id.content)
        rootView.setOnTouchListener { _, _ ->
            resetInactivityTimer()
            false
        }
    }

    override fun onResume() {
        super.onResume()
        resetInactivityTimer()
    }

    override fun onPause() {
        super.onPause()
        stopInactivityTimer()
    }

    private fun resetInactivityTimer() {
        handler.removeCallbacks(inactivityRunnable)
        handler.postDelayed(inactivityRunnable, 60000) // 30 segundos
    }

    private fun stopInactivityTimer() {
        handler.removeCallbacks(inactivityRunnable)
    }

    private fun navigateToActivity3(selectedElement: Element) {
        val intent = Intent(this, Activity3::class.java)
        intent.putExtra(Activity3.elementShownConstant.ELEMENT, selectedElement)
        startActivity(intent)
    }

    private fun fieldSelector() {
        if (indexField < totalField) {
            indexField++
        } else {
            indexField = 0
        }

        ElementManager.indexField = indexField
        if (ElementManager.debug) {
            Toast.makeText(
                this,
                "Es canvia d'àmbit a $indexField: ${fields[indexField].nameField}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun reiniciarActividad() {
        finish()
        val intent = Intent(this, this::class.java)
        startActivity(intent)
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