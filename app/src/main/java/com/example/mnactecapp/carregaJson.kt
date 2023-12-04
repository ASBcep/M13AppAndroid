package com.example.mnactecapp
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

class carregaJson : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")

    // Declaro llistat d'elements LOCAL
    var elements = mutableListOf<Element>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrega_json)

        // String que contindrà el json, sense contingut, per a carregar-lo des de un arxiu
        val jsonString = String
        // val btnJson = findViewById<Button>(R.id.BtnJson)

        // Inici codi chatgpt dialeg arxiu json part 2
        val openFileButton = findViewById<Button>(R.id.openFileButton)

        openFileButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            // Filtre d'arxius tipus json
            intent.type = "application/json"
            getContent.launch(intent)
        }
    }

    // Inici codi chatgpt dialeg arxiu json part 1
    private val getContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    val inputStream = contentResolver.openInputStream(uri)
                    val reader = BufferedReader(InputStreamReader(inputStream))
                    val stringBuilder = StringBuilder()
                    var elementsLlegitsJson = 0
                    var llargadaJson = 0
                    var line: String?
                    var jaHiHainicialElement = false
                    while (reader.readLine().also { line = it } != null) {
                        stringBuilder.append(line).append("\n")
                    }

                    // L'arxiu json passa a l'string jsonString
                    val jsonString = stringBuilder.toString()

                    // Intentem llegir l'string jsonString
                    try {
                        // Convertir el JSON string a un JSONArray
                        val jsonArray = JSONArray(jsonString)
                        llargadaJson = jsonArray.length()
                        Toast.makeText(this, "El JSON conté $llargadaJson elements", Toast.LENGTH_SHORT).show()

                        // Iterar sobre cada objecte dins del JSONArray
                        for (i in 0 until llargadaJson) {
                            val jsonObject = jsonArray.getJSONObject(i)

                            val numInventory = tryCatchJsonInt(jsonObject, "numInventory")
                            val field = tryCatchJsonInt(jsonObject, "field")
                            val year = tryCatchJsonInt(jsonObject, "year")
                            val autonomy = tryCatchJsonInt(jsonObject, "autonomy")
                            val disposalCapacity = tryCatchJsonInt(jsonObject, "disposalCapacity")
                            val cicle = tryCatchJsonString(jsonObject, "Cicle")
                            val cilindrada = tryCatchJsonInt(jsonObject, "Cilindrada")
                            val description = tryCatchJsonString(jsonObject, "Descripcio")
                            val inicialElement = tryCatchJsonBoolean(jsonObject, "inicialElement")
                            val wingspan = tryCatchJsonInt(jsonObject, "wingspan")
                            val energyFont = tryCatchJsonString(jsonObject, "energyFont")
                            val sourceIncome = tryCatchJsonString(jsonObject, "sourceIncome")
                            val formIncome = tryCatchJsonString(jsonObject, "formIncome")
                            val image = tryCatchJsonString(jsonObject, "image")
                            val manufacturingPlace = tryCatchJsonString(jsonObject, "manufacturingPlace")
                            val nameElement = tryCatchJsonString(jsonObject, "nameElement")
                            val length = tryCatchJsonInt(jsonObject, "length")
                            val weight = tryCatchJsonInt(jsonObject, "weight")
                            val potency = tryCatchJsonInt(jsonObject, "potency")
                            val kmsDone = tryCatchJsonInt(jsonObject, "kmsDone")
                            val sostreMaximDeVol = tryCatchJsonInt(jsonObject, "SostreMaximDeVol")
                            val speed = tryCatchJsonInt(jsonObject, "speed")
                            val maxSpeed = tryCatchJsonInt(jsonObject, "maxSpeed")

                            // Afegeixo dades llegides al llistat d'elements LOCAL
                            if (isValidElement(numInventory, field, year)) {
                                if (inicialElement == true) {
                                    if (!jaHiHainicialElement) {
                                        jaHiHainicialElement = true
                                    } else {
                                        Toast.makeText(this, "break1", Toast.LENGTH_SHORT).show()
                                        break
                                    }
                                } else {
                                    Toast.makeText(this, "break2", Toast.LENGTH_SHORT).show()
                                    break
                                }

                                elements.add(Element(
                                    numInventory,
                                    field,
                                    nameElement,
                                    image,
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
                                    year
                                ))
                                elementsLlegitsJson++
                            } else {
                                Toast.makeText(this, "Element no vàlid: $numInventory", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Toast.makeText(this, "Error de lectura del .JSON", Toast.LENGTH_LONG).show()
                        elements.clear()
                    }

                    // Exemple: mostra el contingut llegit en el Logcat
                    // println(jsonString)
                    // Toast.makeText(this, jsonString, Toast.LENGTH_LONG).show()
                    Toast.makeText(
                        this,
                        "Importats $elementsLlegitsJson de $llargadaJson elements que conté el JSON",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            // Buido el llistat d'elements GLOBAL
            ElementManager.elements.clear()
            // Afegeixo el llistat d'elements LOCAL al llistat GLOBAL
            ElementManager.elements.addAll(elements)
        }

    // Fi codi chatgpt dialeg arxiu json part 1
    private fun tryCatchJsonString(jsonObject: JSONObject, campJson: String): String {
        return try {
            jsonObject.getString(campJson)
        } catch (e: JSONException) {
            e.printStackTrace()
            "" // Retorna el valor per defecte en cas d'error
        }
    }

    private fun tryCatchJsonInt(jsonObject: JSONObject, campJson: String): Int {
        return try {
            jsonObject.getInt(campJson)
        } catch (e: JSONException) {
            e.printStackTrace()
            -1 // Retorna el valor per defecte en cas d'error
        }
    }

    private fun tryCatchJsonBoolean(jsonObject: JSONObject, campJson: String): Boolean {
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
