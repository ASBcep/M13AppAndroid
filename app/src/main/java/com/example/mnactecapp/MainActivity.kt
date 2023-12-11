package com.example.mnactecapp

import ElementsList
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            if(it.resultCode == RESULT_OK){
                reiniciarActividad()
            }else if(it.resultCode == RESULT_CANCELED){
            }
        }
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imgElement: ImageView = findViewById(R.id.imgElementMain)
        val txtElement: TextView = findViewById(R.id.act1FrameText)
        val botonpasar: TextView = findViewById(R.id.botonpasar)
        val btnMesInfo: Button = findViewById(R.id.BtnMesInfo)
        val botonidiomas1: TextView = findViewById(R.id.botonidiomas1)
        //val btnJsonShortcut = findViewById<Button>(R.id.BtnJsonShortcut)
        val btnField: Button = findViewById(R.id.btnField)
        val TvAct1Field: TextView = findViewById(R.id.TvAct1Field)



        //carrego àmbits
        FieldsList(this)
        /* creo que esto no hace falta
        val field = 1;
        val elementsList = ElementsList(this)
        val elementsField = elementsList.loadField(field)
        */
        val field = ElementManager.indexField
        val elementsList = ElementsList(this)

        val elementsField = elementsList.loadField(field)

        val elementShown = elementsField.find { it.inicialElement }

        //intento llegir l'element de la llista GLOBAL; si no s'aconsegueix el llegirà de la classe ElementList
        try {
            txtElement.text = ElementManager.elements[ElementManager.defaultElement].nameElement
            val imgElementPath = getFilesDir().toString() + "/imgelements/" + ElementManager.elements[ElementManager.defaultElement].image
            val bitmap = BitmapFactory.decodeFile(imgElementPath)
            if(bitmap != null) {
                imgElement.setImageBitmap(bitmap)
            }
            TvAct1Field.text = ElementManager.fields[field].nameField
        } catch (e: Exception) {
            // Verificar si se encontró un elemento con inicialElement=true
            if (elementShown != null) {
                val imgElementPath = getFilesDir().toString() + "/imgelements/" + elementShown.image
                val bitmap = BitmapFactory.decodeFile(imgElementPath)
                imgElement.setImageBitmap(bitmap)
                txtElement.text = elementShown.nameElement
                TvAct1Field.text = ElementManager.fields[field].nameField
            }
        }
        //mostrar text segons idioma
        when (ElementManager.idioma){
            0 -> {btnMesInfo.text = "Veure'n més detalls"}
            1 -> {btnMesInfo.text = "Ver más detalles"}
            2 -> {btnMesInfo.text = "See details"}
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
                getResult.launch(intent)
            }
            /*
            btnJsonShortcut.setOnClickListener{
                val intent = Intent(this@MainActivity, carregaJson::class.java)
                startActivity(intent)
            }
            */
            btnField.setOnClickListener {
                val intent = Intent(this, Activity4::class.java)
                startActivity(intent)
            }
        }
    fun reiniciarActividad() {
        // Cerrar la actividad actual
        finish()
        // Crear un nuevo Intent para reiniciar la actividad
        val intent = Intent(this, this::class.java)
        startActivity(intent)


    }
}

/* backup 11/12/2023 pre canvi "element per defecte" a "element random de l'àmbit"
package com/.example.mnactecapp


import ElementsList
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            if(it.resultCode == RESULT_OK){
                reiniciarActividad()
            }else if(it.resultCode == RESULT_CANCELED){
            }
        }
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imgElement: ImageView = findViewById(R.id.imgElementMain)
        val txtElement: TextView = findViewById(R.id.act1FrameText)
        val botonpasar: TextView = findViewById(R.id.botonpasar)
        val btnMesInfo: Button = findViewById(R.id.BtnMesInfo)
        val botonidiomas1: TextView = findViewById(R.id.botonidiomas1)
        //val btnJsonShortcut = findViewById<Button>(R.id.BtnJsonShortcut)
        val btnField: Button = findViewById(R.id.btnField)
        val TvAct1Field: TextView = findViewById(R.id.TvAct1Field)



        //carrego àmbits
        FieldsList(this)
        /* creo que esto no hace falta
        val field = 1;
        val elementsList = ElementsList(this)
        val elementsField = elementsList.loadField(field)
        */
        val field = ElementManager.indexField
        val elementsList = ElementsList(this)

        val elementsField = elementsList.loadField(field)

        val elementShown = elementsField.find { it.inicialElement }

        //intento llegir l'element de la llista GLOBAL; si no s'aconsegueix el llegirà de la classe ElementList
        try {
            txtElement.text = ElementManager.elements[ElementManager.defaultElement].nameElement
            val imgElementPath = getFilesDir().toString() + "/imgelements/" + ElementManager.elements[ElementManager.defaultElement].image
            val bitmap = BitmapFactory.decodeFile(imgElementPath)
            if(bitmap != null) {
                imgElement.setImageBitmap(bitmap)
            }
            TvAct1Field.text = ElementManager.fields[field].nameField
        } catch (e: Exception) {
            // Verificar si se encontró un elemento con inicialElement=true
            if (elementShown != null) {
                val imgElementPath = getFilesDir().toString() + "/imgelements/" + elementShown.image
                val bitmap = BitmapFactory.decodeFile(imgElementPath)
                imgElement.setImageBitmap(bitmap)
                txtElement.text = elementShown.nameElement
                TvAct1Field.text = ElementManager.fields[field].nameField
            }
        }
        //mostrar text segons idioma
        when (ElementManager.idioma){
            0 -> {btnMesInfo.text = "Veure'n més detalls"}
            1 -> {btnMesInfo.text = "Ver más detalles"}
            2 -> {btnMesInfo.text = "See details"}
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
                getResult.launch(intent)
            }
            /*
            btnJsonShortcut.setOnClickListener{
                val intent = Intent(this@MainActivity, carregaJson::class.java)
                startActivity(intent)
            }
            */
            btnField.setOnClickListener {
                val intent = Intent(this, Activity4::class.java)
                startActivity(intent)
            }
        }
    fun reiniciarActividad() {
        // Cerrar la actividad actual
        finish()
        // Crear un nuevo Intent para reiniciar la actividad
        val intent = Intent(this, this::class.java)
        startActivity(intent)
    }
}*/
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
