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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrega_json)


        //String que contindrà el json, sense contingut, per a carregar-lo des de un arxiu
        val jsonString = String
        //val btnJson = findViewById<Button>(R.id.BtnJson)

        //inici codi chatgpt dialeg arxiu json part 2
        openFileButton = findViewById(R.id.openFileButton)

        openFileButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "application/json"
            getContent.launch(intent)
        }

    }
    //inici codi chatgpt dialeg arxiu json part 1
    private lateinit var openFileButton: Button

    //funció getContent que llegirà el JSON i el desarà
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
                    var jaHiHaElementPerDefecte = false
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

                        //do while per verificar que només hi ha 1 elementPerDefecte=true
                        //while{}
                        // Iterar sobre cada objecte dins del JSONArray
                        for (i in 0 until llargadaJson) {
                            val jsonObject = jsonArray.getJSONObject(i)

                            /*// Obtenir els valors de cada propietat
                            val numeroInventari = jsonObject.getInt("NumeroInventari")
                            val ambit  = jsonObject.getString("Ambit")
                            val any = jsonObject.getInt("Any")
                            val autonomia = jsonObject.getInt("Autonomia")
                            val capacitatDiposit = jsonObject.getInt("CapacitatDiposit")
                            val cicle = jsonObject.getString("Cicle")
                            val cilindrada = jsonObject.getInt("Cilindrada")
                            val descipcio =  jsonObject.getString("Descripcio")
                            val elementPerDefecte = jsonObject.getBoolean("ElementPerDefecte")
                            val envergadura = jsonObject.getInt("Envergadura")
                            val fontEnergia = jsonObject.getString("FontEnergia")
                            val fontIngres = jsonObject.getString("FontIngres")
                            val formaIngres = jsonObject.getString("FormaIngres")
                            val imatge = jsonObject.getString("Imatge")//potser no es fa servir
                            val llocFabricacio = jsonObject.getString("LlocFabricacio")
                            val nomElement = jsonObject.getString("NomElement")
                            val longitud  = jsonObject.getInt("Longitud")
                            val pes  = jsonObject.getInt("Pes")
                            val potencia  = jsonObject.getInt("Potencia")
                            val kmsFets  = jsonObject.getInt("KmsFets")
                            val sostreMaximDeVol  = jsonObject.getInt("SostreMaximDeVol")
                            val velocitat  = jsonObject.getInt("Velocitat")
                            val velocitatMax  = jsonObject.getInt("VelocitatMax")*/ //importació sense comprovar
                            //amb try+catch podem donar valor "buit" ("") o (-1) a camps que no estiguin al json
                            /*try{
                                prova = jsonObject.getString("prova")
                            } catch (e: Exception) {
                                e.printStackTrace()
                                prova = "provaException"
                            }*/
                            //llegeixo camps del json en variables amb funcions que fan try+catch per si un camp no existeix al json
                            //en cas de no trobar el camp, el valor serà "" (string en blanc), -1 o false segons el tipus de variable.
                            //val numeroInventari = jsonObject.getInt("NumeroInventari")//ens interessa que falli si la lectura json no té ID
                            val numeroInventari = tryCatchJsonInt(jsonObject, "NumeroInventari")
                            val ambit = tryCatchJsonString(jsonObject, "Ambit")
                            val any = tryCatchJsonInt(jsonObject, "Any")
                            val autonomia = tryCatchJsonInt(jsonObject, "Autonomia")
                            val capacitatDiposit = tryCatchJsonInt(jsonObject, "CapacitatDiposit")
                            val cicle = tryCatchJsonString(jsonObject, "Cicle")
                            val cilindrada = tryCatchJsonInt(jsonObject, "Cilindrada")
                            val descipcio = tryCatchJsonString(jsonObject, "Descripcio")
                            val elementPerDefecte = tryCatchJsonBoolean(jsonObject, "ElementPerDefecte")
                            val envergadura = tryCatchJsonInt(jsonObject, "Envergadura")
                            val fontEnergia = tryCatchJsonString(jsonObject, "FontEnergia")
                            val fontIngres = tryCatchJsonString(jsonObject, "FontIngres")
                            val formaIngres = tryCatchJsonString(jsonObject, "FormaIngres")
                            val imatge = tryCatchJsonString(jsonObject, "Imatge")//potser no es fa servir
                            val llocFabricacio = tryCatchJsonString(jsonObject, "LlocFabricacio")
                            val nomElement = tryCatchJsonString(jsonObject, "NomElement")
                            val longitud = tryCatchJsonInt(jsonObject, "Longitud")
                            val pes = tryCatchJsonInt(jsonObject, "Pes")
                            val potencia = tryCatchJsonInt(jsonObject, "Potencia")
                            val kmsFets = tryCatchJsonInt(jsonObject, "KmsFets")
                            val sostreMaximDeVol = tryCatchJsonInt(jsonObject, "SostreMaximDeVol")
                            val velocitat = tryCatchJsonInt(jsonObject, "Velocitat")
                            val velocitatMax = tryCatchJsonInt(jsonObject, "VelocitatMax")
                            /*
                                                    if (numeroInventari != -1){
                                                        if (elementPerDefecte == true){
                                                            if(jaHiHaElementPerDefecte == false) {
                                                                jaHiHaElementPerDefecte = true
                                                            }else{
                                                                Toast.makeText(this, "break1", Toast.LENGTH_SHORT).show()
                                                                break
                                                            }
                                                        }else{
                                                            Toast.makeText(this, "break2", Toast.LENGTH_SHORT).show()
                                                            break
                                                        }
                                                    }else{
                                                        Toast.makeText(this, "break3", Toast.LENGTH_SHORT).show()
                                                        break
                                                    }
                            */
                            //afegeixo dades llegides al llistat d'elements LOCAL
                            elements.add(Element(
                                numeroInventari,
                                ambit,
                                any,
                                autonomia,
                                capacitatDiposit,
                                cicle,
                                cilindrada,
                                descipcio,
                                elementPerDefecte,
                                envergadura,
                                fontEnergia,
                                fontIngres,
                                formaIngres,
                                R.drawable.moto,//corregir, canviar per imatge definitiva
                                llocFabricacio,
                                nomElement,
                                longitud,
                                pes,
                                potencia,
                                kmsFets,
                                sostreMaximDeVol,
                                velocitat,
                                velocitatMax))
                            //FALTA assignar una imatge per defecte en cas que no en tingui una.

                            //Toast.makeText(this,"Importat element " + (i+1) + ", número d'inventari " + numeroInventari + " amb denominació " + nomElement, Toast.LENGTH_SHORT).show()
                            elementsLlegitsJson++

                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Toast.makeText(this,"Error de lectura del .JSON", Toast.LENGTH_LONG).show()
                        elements.clear()
                    }
                    // Exemple: mostra el contingut llegit en el Logcat
                    //println(jsonString)
                    //Toast.makeText(this, jsonString, Toast.LENGTH_LONG).show()
                    Toast.makeText(this, "Importats " + elementsLlegitsJson + " de " + llargadaJson + " elements que conté el JSON", Toast.LENGTH_LONG).show()

                }
            }
            //buido el llistat d'elements GLOBAL
            ElementManager.elements.clear()
            //afegeixo el llistat d'elements LOCAL al llistat GLOBAL
            ElementManager.elements.addAll(elements)
        }
    //fi codi chatgpt dialeg arxiu json part 1
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
}


