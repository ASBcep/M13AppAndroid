package com.example.mnactecapp

import ElementsList
import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.documentfile.provider.DocumentFile
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class ConfigAdmin : AppCompatActivity() {
    //variable per poder gestionar el json quan es tria manualment
    private val FILE_PICK_REQUEST_CODE_jsonElements = 5766//JSON
    private val PICK_DIRECTORY_REQUEST_CODE_imgFolder = 365337//carpeta d'imatges
    private val PICK_DIRECTORY_REQUEST_CODE_jsonFolder = 5766337//carpeta de json


    private var originalLang = -1
    private var jsonLang = -1
    private var destinyFolder = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.config_admin)

        val btnCat = findViewById<Button>(R.id.openFileButtonElementsCat)
        val btnSpa = findViewById<Button>(R.id.openFileButtonElementsSpa)
        val btnEng = findViewById<Button>(R.id.openFileButtonElementsEng)
        val btnImgElements = findViewById<Button>(R.id.openFolderImagesElements)
        val btnJson = findViewById<Button>(R.id.openFolderJson)
        val spinnerFields = findViewById<Spinner>(R.id.spinnerField)
        FieldsList(this)

        //afegeixo àmbits a un array per mostrar-ho a l'spinner d'àmbit
        val arrayFields = ArrayList<String>()
        ElementManager.fields.forEach { field ->
            val nameField = field.nameField
            arrayFields.add(nameField)
        }
        // Crear un adaptador per al Spinner utilitzant l'array d'strings
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayFields)
        // Especificar el disseny de la llista desplegable
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Assignar l'adaptador al Spinner
        spinnerFields.adapter = adapter

        //segons idioma canvio el valor global d'idioma per poder executar la lectura del json; després es restaura
        btnCat.setOnClickListener {
            originalLang = ElementManager.idioma
            jsonLang = 0
            btnLoadJson()
        }
        btnSpa.setOnClickListener {
            originalLang = ElementManager.idioma
            jsonLang = 1
            btnLoadJson()
        }
        btnEng.setOnClickListener {
            originalLang = ElementManager.idioma
            jsonLang = 2
            btnLoadJson()
        }
        btnImgElements.setOnClickListener{
            destinyFolder = "imgelements"
            chooseFolderImg()
        }
        btnJson.setOnClickListener {
            destinyFolder = "json"
            chooseFolderJson()
        }
        spinnerFields.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Accions que es realitzaran quan es seleccioni un element
                val selectedOption = arrayFields[position]
                ElementManager.defaultField = position
                ElementManager.indexField = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    private fun chooseFolderImg() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT_TREE)
        startActivityForResult(intent, PICK_DIRECTORY_REQUEST_CODE_imgFolder)    }
    private fun chooseFolderJson() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT_TREE)
        startActivityForResult(intent, PICK_DIRECTORY_REQUEST_CODE_jsonFolder)    }

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
    //obtindre ruta de l'arxiu json
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?, ) {
        super.onActivityResult(requestCode, resultCode, data)

        //gestiono fitxer json
        if (requestCode == FILE_PICK_REQUEST_CODE_jsonElements && resultCode == Activity.RESULT_OK) {
            val uri = data?.data
            //copiar json
            gestionarArxiuSeleccionat(uri)
        }
        //gestiono carpeta d'imatges
        if (requestCode == PICK_DIRECTORY_REQUEST_CODE_imgFolder && resultCode == Activity.RESULT_OK) {
            val uri: Uri? = data?.data
            // Utilitza la URI seleccionada per accedir a la carpeta
            if (uri != null) {
                //comprovo si existeix o creo la carpeta imgelements, per emmagatzemar les imatges dels elements
                val imgElementsFolder = folderCheck("imgelements")
                if (imgElementsFolder)
                {
                    //copiar imatges d'elements
                    copyImages(uri, destinyFolder)
                }

                // La URI apunta a la carpeta seleccionada
                // Aquí pots utilitzar la URI per accedir als fitxers d'aquesta carpeta
            }
        } else if (requestCode == PICK_DIRECTORY_REQUEST_CODE_jsonFolder && resultCode == Activity.RESULT_OK){
            val uri: Uri? = data?.data
            // Utilitza la URI seleccionada per accedir a la carpeta
            if (uri != null) {
                //comprovo si existeix o creo la carpeta imgelements, per emmagatzemar les imatges dels elements
                val jsonFolder = folderCheck("json")
                if (jsonFolder)
                {
                    //copiar imatges d'elements
                    copyJsons(uri, destinyFolder)
                }

                // La URI apunta a la carpeta seleccionada
                // Aquí pots utilitzar la URI per accedir als fitxers d'aquesta carpeta
            }
        }

    }
    //funció per llegir el json
    fun gestionarArxiuSeleccionat(uri: Uri?) {
        uri?.let { selectedFileUri ->
            // Obrir un InputStream per llegir les dades de l'arxiu seleccionat
            val inputStream: InputStream? = contentResolver.openInputStream(selectedFileUri)

            //nom de l'arxiu segons idioma
            var fileName = "delete_me"
            when (jsonLang){
                0 -> {fileName = "elements_cat.json"}
                1 -> {fileName = "elements_spa.json"}
                2 -> {fileName = "elements_eng.json"}
            }
            // Crear un nou fitxer de destinació on es copiaran les dades
            val destinationFile = File(filesDir, ("/json/" + fileName))

            //comprovar i crear si fa falta la carpeta json
            val jsonFolder = folderCheck("json")

            // Copiar les dades de l'arxiu seleccionat al fitxer de destinació
            if(jsonFolder) {
                try {
                    inputStream?.use { input ->
                        FileOutputStream(destinationFile).use { output ->
                            input.copyTo(output)
                        }
                        Toast.makeText(this, "Fitxer copiat: " + destinationFile, Toast.LENGTH_LONG)
                            .show()
                        ElementManager.idioma = jsonLang
                        ElementsList(this)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(
                        this,
                        "Error !! Fitxer no copiat: " + destinationFile,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            //retorno l'idioma en què es trobava l'app a l'índex
            ElementManager.idioma = originalLang
        }
    }
    private fun folderCheck(folder: String): Boolean {
        val FolderPath = File(filesDir, folder)
        if (!FolderPath.exists()) {
            val CreatedFolder = FolderPath.mkdir()
            if (CreatedFolder) {
                Toast.makeText(this,"Carpeta " + folder + " creada", Toast.LENGTH_LONG).show()
                return true
            } else {
                Toast.makeText(this,"Carpeta " + folder + " no creada i no existent", Toast.LENGTH_LONG).show()
                return false
            }
        } else {
            Toast.makeText(this,"La carpeta " + folder + " ja existia", Toast.LENGTH_LONG).show()
            return true
        }
    }
    private fun copyImages(selectedFolderUri: Uri, folder: String) {
        val documentTree = DocumentFile.fromTreeUri(this, selectedFolderUri)

        val imgelementsFolder = File(filesDir, folder)

        documentTree?.listFiles()?.forEach { file ->
            if (file.isFile && isImageFile(file.name)) {
                val inputStream = contentResolver.openInputStream(file.uri)
                val outputFile = File(imgelementsFolder, file.name ?: "")

                try {
                    inputStream?.use { input ->
                        FileOutputStream(outputFile).use { output ->
                            val buffer = ByteArray(4 * 1024)
                            var read: Int
                            while (input.read(buffer).also { read = it } != -1) {
                                output.write(buffer, 0, read)
                            }
                        }
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }

        Toast.makeText(this, "Images copiades a la carpeta $folder", Toast.LENGTH_LONG).show()
    }
    private fun copyJsons(selectedFolderUri: Uri, folder: String) {
        val documentTree = DocumentFile.fromTreeUri(this, selectedFolderUri)

        val jsonFolder = File(filesDir, folder)

        documentTree?.listFiles()?.forEach { file ->
            if (file.isFile && isJsonFile(file.name)) {
                val inputStream = contentResolver.openInputStream(file.uri)
                val outputFile = File(jsonFolder, file.name ?: "")

                try {
                    inputStream?.use { input ->
                        FileOutputStream(outputFile).use { output ->
                            val buffer = ByteArray(4 * 1024)
                            var read: Int
                            while (input.read(buffer).also { read = it } != -1) {
                                output.write(buffer, 0, read)
                            }
                        }
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }

        Toast.makeText(this, "Jsons copiats a la carpeta $folder", Toast.LENGTH_LONG).show()
    }
    //funció que comprova si un fitxer és una imatge
    private fun isImageFile(fileName: String?): Boolean {
        if (fileName != null) {
            return fileName?.endsWith(".jpg", true) == true ||
                    fileName.endsWith(".jpeg", true) ||
                    fileName.endsWith(".png", true)
        } else {
            return false
        }
    }
    private fun isJsonFile(fileName: String?): Boolean {
        if (fileName != null) {
            return fileName?.endsWith(".json", true) == true
        } else {
            return false
        }
    }
}