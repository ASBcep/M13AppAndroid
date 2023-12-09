package com.example.mnactecapp

import ElementsList
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.InputStreamReader

class ConfigAdmin : AppCompatActivity() {
    //variable per poder gestionar el json manualment quan es tria manualment
    private val FILE_PICK_REQUEST_CODE_jsonElements = 5766//JSON
    private var originalLang = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.config_admin)

        val btnCat = findViewById<Button>(R.id.openFileButtonElementsCat)
        val btnSpa = findViewById<Button>(R.id.openFileButtonElementsSpa)
        val btnEng = findViewById<Button>(R.id.openFileButtonElementsEng)

        //segons idioma canvio el valor global d'idioma per poder executar la lectura del json; després es restaura
        btnCat.setOnClickListener {
            originalLang = ElementManager.idioma
            ElementManager.idioma = 0
            btnLoadJson()
            ElementManager.idioma = originalLang
        }
        btnSpa.setOnClickListener {
            originalLang = ElementManager.idioma
            ElementManager.idioma = 1
            btnLoadJson()
            ElementManager.idioma = originalLang
        }
        btnEng.setOnClickListener {
            originalLang = ElementManager.idioma
            ElementManager.idioma = 2
            btnLoadJson()
            ElementManager.idioma = originalLang
        }
    }
    //private fun btnLoadJson(originalLang: Int)
    private fun btnLoadJson()

    {
        //ElementManager.idioma = 0
        //l'usuari tria json
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "application/json"
        startActivityForResult(intent, FILE_PICK_REQUEST_CODE_jsonElements)


        //Toast.makeText(this,"Idioma canviat a català", Toast.LENGTH_LONG).show()

        //falta copiar el json que triï l'usuari a la carpeta local

        //ElementsList(this)
        //ElementManager.idioma = originalLang
    }
    //Obrir arxiu json manualment en androids antics part 1
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?, ) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == FILE_PICK_REQUEST_CODE_jsonElements && resultCode == Activity.RESULT_OK) {
            val uri = data?.data
            // Aquí pots gestionar la URI de l'arxiu seleccionat
            // Per exemple, pots llegir l'arxiu, processar-lo, etc.
            gestionarArxiuSeleccionat(uri)
        }
    }
    //funció per llegir el json
    fun gestionarArxiuSeleccionat(uri: Uri?) {
        uri?.let { selectedFileUri ->
            // Obrir un InputStream per llegir les dades de l'arxiu seleccionat
            val inputStream: InputStream? = contentResolver.openInputStream(selectedFileUri)

            //nom de l'arxiu segons idioma
            var fileName = "delete_me"
            when (ElementManager.idioma){
                0 -> {fileName = "elements_cat.json"}
                1 -> {fileName = "elements_spa.json"}
                2 -> {fileName = "elements_eng.json"}
            }
            // Crear un nou fitxer de destinació on es copiaran les dades
            val destinationFile = File(filesDir, ("/json/" + fileName))

            //comprobar i crear si fa falta la carpeta json
            val FolderJson = File(filesDir, "json")
            if (!FolderJson.exists()) {
                val CreatedFolder = FolderJson.mkdir()
                if (CreatedFolder) {
                    // Carpeta creada amb èxit
                    // Pots afegir aquí qualsevol lògica addicional si cal
                    Toast.makeText(this,"carpeta json creada", Toast.LENGTH_LONG).show()
                } else {
                    // Error en crear la carpeta
                    Toast.makeText(this,"carpeta json no creada i no existent", Toast.LENGTH_LONG).show()
                }
            } else {
                // La carpeta ja existeix
                Toast.makeText(this,"carpeta json ja existia", Toast.LENGTH_LONG).show()
            }

            // Copiar les dades de l'arxiu seleccionat al fitxer de destinació
            try {
                inputStream?.use { input ->
                    FileOutputStream(destinationFile).use { output ->
                        input.copyTo(output)
                    }
                    Toast.makeText(this,"Fitxer copiat: " + destinationFile, Toast.LENGTH_LONG).show()

                    ElementsList(this)
                }
            }catch (e: Exception){
                e.printStackTrace()
                Toast.makeText(this,"Error !! Fitxer no copiat: " + destinationFile, Toast.LENGTH_SHORT).show()

            }
            ElementManager.idioma = originalLang
        }
    }
}