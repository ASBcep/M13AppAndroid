package com.example.mnactecapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import org.json.JSONArray
//
import android.app.Activity
import android.content.Intent
import android.net.Uri
//import android.os.Bundle
//import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import org.json.JSONException
import org.json.JSONObject
//import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader


class carregaJson : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")

    //declaro llistat d'elements LOCAL
    var elements = mutableListOf<Element>()
    //variable per poder gestionar el json manualment quan es tria manualment
    private val FILE_PICK_REQUEST_CODE_jsonElements = 5766//JSON
    private var defaultElement: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrega_json)


        //String que contindrà el json, sense contingut, per a carregar-lo des de un arxiu
        val jsonString = String
        //val btnJson = findViewById<Button>(R.id.BtnJson)

        openFileButton = findViewById(R.id.openFileButton)
        /*Obrir arxiu json manualment en androids moderns

        openFileButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            //filtre d'arxius tipus json
            intent.type = "application/json"
            getContent.launch(intent)
        }*/

        //openFileButton = findViewById(R.id.openFileButton)

        //Obrir arxiu json manualment en androids antics part 1
        openFileButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "application/json"
            startActivityForResult(intent, FILE_PICK_REQUEST_CODE_jsonElements)
        }
    }
    //Obrir arxiu json manualment en androids antics part 1
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == FILE_PICK_REQUEST_CODE_jsonElements && resultCode == Activity.RESULT_OK) {
            val uri = data?.data
            // Aquí pots gestionar la URI de l'arxiu seleccionat
            // Per exemple, pots llegir l'arxiu, processar-lo, etc.
            gestionarArxiuSeleccionat(uri)
        }
    }
    private fun gestionarArxiuSeleccionat(fileUri: Uri?) {
        // Aquí pots posar la lògica que necessites fer amb la URI de l'arxiu seleccionat
        // Per exemple, pots llegir l'arxiu, processar-lo, etc.
        // fileUri conté la Uri de l'arxiu seleccionat
        // Però és important comprovar si fileUri no és null abans d'utilitzar-lo
        fileUri?.let {
            // Aquí pots continuar amb el teu codi, ja que ara tens la URI de l'arxiu seleccionat
            // ...
            val inputStream = contentResolver.openInputStream(it)
            val reader = BufferedReader(InputStreamReader(inputStream))
            val stringBuilder = StringBuilder()
            var elementsLlegitsJson = 0
            var llargadaJson = 0
            var line: String?
            var jaHiHainicialElement = false
            while (reader.readLine().also { line = it } != null) {
                stringBuilder.append(line).append("\n")
            }

            //l'arxiu json passa a l'string jsonString
            val jsonString = stringBuilder.toString()

            //intentem llegir l'string jsonString
            try {
                // Convertir el JSON string a un JSONArray
                val jsonArray = JSONArray(jsonString)
                llargadaJson = jsonArray.length()
                Toast.makeText(this, "El JSON conté " + llargadaJson + " elements", Toast.LENGTH_SHORT).show()

                //do while per verificar que només hi ha 1 inicialElement=true
                //while{}
                // Iterar sobre cada objecte dins del JSONArray
                for (i in 0 until llargadaJson) {

                    val jsonObject = jsonArray.getJSONObject(i)

                    if (anularLectura == false) {
                        //llegeixo camps del json en variables amb funcions que fan try+catch per si un camp no existeix al json
                        //en cas de no trobar el camp, el valor serà "" (string en blanc), -1 o false segons el tipus de variable.
                        //val numInventory = jsonObject.getInt("numInventory")//ens interessa que falli si la lectura json no té ID
                        val numInventory = tryCatchJsonInt(jsonObject, "numInventory")
                        val field = tryCatchJsonInt(jsonObject, "field")
                        val year = tryCatchJsonInt(jsonObject, "year")
                        val autonomy = tryCatchJsonInt(jsonObject, "autonomy")
                        val disposalCapacity =
                            tryCatchJsonInt(jsonObject, "disposalCapacity")
                        val cicle = tryCatchJsonString(jsonObject, "Cicle")
                        val cilindrada = tryCatchJsonInt(jsonObject, "Cilindrada")
                        val description = tryCatchJsonString(jsonObject, "Descripcio")
                        val inicialElement =
                            tryCatchJsonBoolean(jsonObject, "inicialElement")
                        val wingspan = tryCatchJsonInt(jsonObject, "wingspan")
                        val energyFont = tryCatchJsonString(jsonObject, "energyFont")
                        val sourceIncome = tryCatchJsonString(jsonObject, "sourceIncome")
                        val formIncome = tryCatchJsonString(jsonObject, "formIncome")
                        val image =
                            tryCatchJsonString(jsonObject, "image")//potser no es fa servir
                        val manufacturingPlace =
                            tryCatchJsonString(jsonObject, "manufacturingPlace")
                        val nameElement = tryCatchJsonString(jsonObject, "nameElement")
                        val length = tryCatchJsonInt(jsonObject, "length")
                        val weight = tryCatchJsonInt(jsonObject, "weight")
                        val potency = tryCatchJsonInt(jsonObject, "potency")
                        val kmsDone = tryCatchJsonInt(jsonObject, "kmsDone")
                        val sostreMaximDeVol =
                            tryCatchJsonInt(jsonObject, "SostreMaximDeVol")
                        val speed = tryCatchJsonInt(jsonObject, "speed")
                        val maxSpeed = tryCatchJsonInt(jsonObject, "maxSpeed")

                        if (isValidElement(numInventory, field, year)) {}

                        //EVITAR QUE HI HAGI 2 ELEMENTS PER DEFECTE
                        if (numInventory > -1) {
                            //nou if que busqui el numInventory al llistat

                            //Busco al llistat d'elements si existeix el número d'inventari.
                            val elementTrobat =
                                elements.find { it.numInventory == numInventory }
                            // 'it' fa referència a cada element de la llista mentre el mètode 'find' itera sobre els elements

                            //Encadeno IF per veure si un element està duplicat o si hi ha més d'un element per fefecte
                            if (elementTrobat != null) {
                                // S'ha trobat un element amb l'ID especificat
                                println("Element trobat: $elementTrobat")
                                anularLectura = true
                            } else {
                                // No s'ha trobat cap element amb l'ID especificat
                                println("No s'ha trobat cap element amb l'ID $numInventory")
                                if (inicialElement == true) {
                                    if (jaHiHainicialElement == false) {
                                        jaHiHainicialElement = true
                                        defaultElement = i
                                    } else {
                                        Toast.makeText(this,"ERROR: El Json conté varis elements per defecte",Toast.LENGTH_SHORT).show()
                                        anularLectura = true

                                    }
                                }
                            }
                        } else {
                            Toast.makeText(this,"El número d'inventari no compleix els requisits",Toast.LENGTH_SHORT).show()
                            anularLectura = true
                        }
                        if (anularLectura == false) {
                            //afegeixo dades llegides al llistat d'elements LOCAL
                            elements.add(
                                Element(
                                    numInventory,
                                    field,
                                    nameElement,
                                    image,
                                    //R.drawable.moto,//corregir, canviar per imatge definitiva
                                    description,
                                    autonomy,
                                    disposalCapacity,
                                    cicle,
                                    cilindrada,
                                    wingspan,
                                    energyFont,
                                    sourceIncome,
                                    formIncome,
                                    manufacturingPlace,
                                    length,
                                    weight,
                                    potency,
                                    kmsDone,
                                    sostreMaximDeVol,
                                    speed,
                                    maxSpeed,
                                    inicialElement,
                                    year,
                                )
                            )
                            //FALTA assignar una imatge per defecte en cas que no en tingui una.
                            elementsLlegitsJson++
                        }
                    }
                }
                //EXCEPCIÓ PER SI FALLA LA LECTURA DE JSON
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this,"Error de lectura del .JSON", Toast.LENGTH_LONG).show()
                elements.clear()
            }
            //INFORMO DELS ELEMENTS LLEGITS
            Toast.makeText(this, "Importats " + elementsLlegitsJson + " de " + llargadaJson + " elements que conté el JSON", Toast.LENGTH_LONG).show()

        }
        //verifico si la lectura es vàlida
        if (anularLectura == false) {
            //buido el llistat d'elements GLOBAL
            ElementManager.elements.clear()

            //afegeixo el llistat d'elements LOCAL al llistat GLOBAL
            ElementManager.elements.addAll(elements)

            //Indico element per defecte al llistat GLOBAL
            ElementManager.defaultElement = defaultElement

        } else {
            elements.clear()
            Toast.makeText(this, "Llistat no desat. Si us plau importeu un fitxer JSON sense errors", Toast.LENGTH_LONG).show()
        }
    }
    var anularLectura = false
    //inici codi chatgpt dialeg arxiu json part 1
    private lateinit var openFileButton: Button

    private fun tryCatchJsonString(jsonObject: JSONObject, campJson: String,): String {
        return try {
            jsonObject.getString(campJson)
        } catch (e: JSONException) {
            e.printStackTrace()
            "" // Retorna el valor per defecte en cas d'error
        }
    }
    private fun tryCatchJsonInt(jsonObject: JSONObject, campJson: String,): Int {
        return try {
            jsonObject.getInt(campJson)
        } catch (e: JSONException) {
            e.printStackTrace()
            -1 // Retorna el valor per defecte en cas d'error
        }
    }
    private fun tryCatchJsonBoolean(jsonObject: JSONObject, campJson: String,): Boolean {
        return try {
            jsonObject.getBoolean(campJson)
        } catch (e: JSONException) {
            e.printStackTrace()
            false // Retorna el valor per defecte en cas d'error
        }
    }
    private fun isValidElement(numInventory: Int, field: Int, year: Int): Boolean {
        return numInventory != -1 && field != -1 && year != -1
    }
}
