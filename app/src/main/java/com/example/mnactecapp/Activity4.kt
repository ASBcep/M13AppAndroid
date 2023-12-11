package com.example.mnactecapp


import ElementsList
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class Activity4 : AppCompatActivity() {

    // carrego el llistat d'elements LOCAL des de la llista GLOBAL
    val elements = ElementManager.elements
    // carrego les dades d'àmbit a mostrar LOCAL des de la llista GLOBAL
    val fields = ElementManager.fields
    var indexField = ElementManager.indexField
    val totalField = ElementManager.fields.count() - 1//resto 1 ja que començem pel 0

    //val act1FrameText: TextView = findViewById(R.id.act1FrameText)

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            if(it.resultCode == RESULT_OK){
                reiniciarActividad()
            }else if(it.resultCode == RESULT_CANCELED){
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity4)

        //val botonpasar: TextView = findViewById(R.id.botonpasar)
        //val botonatras: TextView = findViewById(R.id.botonatras)

        val act1FrameText: TextView = findViewById(R.id.act1FrameText)

        val botonidiomas1: TextView = findViewById(R.id.botonidiomas1)
        val btnChangeField: Button = findViewById(R.id.btnChangeField)
        val btnMainScreen: Button = findViewById(R.id.btnMainScreen)

        act1FrameText.text = fields[indexField].nameField

        //mostrar text segons idioma
        when (ElementManager.idioma){
            0 -> {btnChangeField.text = getString(R.string.btnChangeFieldCAT)
                botonidiomas1.text = getString(R.string.btn_idioma)
                btnMainScreen.text = getString(R.string.btnIniciCAT)}
            1 -> {btnChangeField.text = getString(R.string.btnChangeFieldSPA)
                botonidiomas1.text = getString(R.string.btn_idioma)
                btnMainScreen.text = getString(R.string.btnIniciSPA)}
            2 -> {btnChangeField.text = getString(R.string.btnChangeFieldENG)
                botonidiomas1.text = getString(R.string.btn_language)
                btnMainScreen.text = getString(R.string.btnIniciENG)}
        }


        /*botonpasar.setOnClickListener {
            // Crear un Intent para abrir Activity6
            val intent = Intent(this, Activity6::class.java)
            startActivity(intent)
        }*/

        /*botonatras.setOnClickListener {
            // Crear un Intent para abrir Activity3
            val intent = Intent(this, Activity3::class.java)
            startActivity(intent)
        }*/
        //inici programació amb llista GLOBAL

        //val elementsField = elements//prova sense cribar per àmbit
        //final programació amb llista GLOBAL
        /*prova sense usar la classe ElementsList
        val intent = getIntent()
        val field = intent.getIntExtra(fieldConstant.FIELD, 1)

        val elementsList = ElementsList(this)
        val elementsField = elementsList.loadField(field)
        */

        val elementsList = ElementsList(this)
        var elementsField = elementsList.loadField(indexField)


        //cribo per àmbit els elements a mostrar a la llista
       /* if (indexField > 0){
            for(i in 0 until elements.count()) {
                if(elements[i].field == indexField){
                    elementsField.add(elements[i])
                }
            }
        } else {
            elementsField = elements
        }
*/

        val listAltresElements = findViewById<RecyclerView>(R.id.ListAltresElements)
        val layoutManager = GridLayoutManager(this, 3)
        listAltresElements.layoutManager = layoutManager

        val adapter = ElementAdapter(elementsField) { selectedElement ->
            // Manejar el clic del elemento aquí
            navigateToActivity3(selectedElement)
        }

        listAltresElements.adapter = adapter

        botonidiomas1.setOnClickListener {
            // Crear un Intent para abrir idiomas
            val intent = Intent(this, idiomas::class.java)
            getResult.launch(intent)
        }
        //botón para cambiar de ámbito
        btnChangeField.setOnClickListener{
            fieldSelector()
            act1FrameText.text = fields[indexField].nameField
            reiniciarActividad()
        }
        btnMainScreen.setOnClickListener {
            // Crear un Intent para abrir la pantalla principal
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun navigateToActivity3(selectedElement: Element) {
        // Crear un Intent para abrir Activity3
        val intent = Intent(this, Activity3::class.java)
        intent.putExtra(Activity3.elementShownConstant.ELEMENT, selectedElement)
        startActivity(intent)
    }
    private fun fieldSelector() {
        if (indexField < totalField){
            indexField++
        } else {
            indexField = 0
        }
        //FALTA canviar que l'àmbit es mostri com a string i no com a enter.
        ElementManager.indexField = indexField
        Toast.makeText(this,"Es canvia d'àmbit a " + indexField + ": " + fields[indexField].nameField, Toast.LENGTH_SHORT).show()
    }
    fun reiniciarActividad() {
        // Cerrar la actividad actual
        finish()
        // Crear un nuevo Intent para reiniciar la actividad
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