package CEP.PolisTecnics.m13appandroid

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
//import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader


class carregaJson : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrega_json)


/*
// Contingut del json en un string (no carregat des d'arxiu)
        val jsonString = """
[
	{
		"NumeroInventari": 0000000,
		"Any": 2023,
		"Autonomia": 1,
		"CapacitatDiposit": 1,
		"CicleCA": "exemple",
		"CicleES": "ejemplo",
		"CicleEN": "example",
		"Cilindrada": 1,
		"DescripcioCA": "exemple",
		"DescripcioES": "ejemplo",
		"DescripcioEN": "example",
		"Envergadura": 1,
		"FontEnergiaCA": "exemple",
		"FontEnergiaES": "ejemplo",
		"FontEnergiaEN": "example",
		"FontIngresCA": "exemple",
		"FontIngresES": "ejemplo",
		"FontIngresEN": "example",
		"FormaIngresCA": "exemple",
		"FormaIngresES": "ejemplo",
		"FormaIngresEN": "example",
		"LlocFabricacioCA": "exemple",
		"LlocFabricacioES": "ejemplo",
		"LlocFabricacioEN": "example",
		"NomElementCA": "exemple",
		"NomElementES": "ejemplo",
		"NomElementEN": "example",
		"Longitud": 1,
		"Pes": 1,
		"Potencia": 1,
		"KmsFets": 1,
		"SostreMaximDeVol": 1,
		"Velocitat": 1,
		"VelocitatMax": 1
	},
	{
		"NumeroInventari": 0000001,
		"Any": 2023,
		"Autonomia": 1,
		"CapacitatDiposit": 1,
		"CicleCA": "exemple",
		"CicleES": "ejemplo",
		"CicleEN": "example",
		"Cilindrada": 1,
		"DescripcioCA": "exemple",
		"DescripcioES": "ejemplo",
		"DescripcioEN": "example",
		"Envergadura": 1,
		"FontEnergiaCA": "exemple",
		"FontEnergiaES": "ejemplo",
		"FontEnergiaEN": "example",
		"FontIngresCA": "exemple",
		"FontIngresES": "ejemplo",
		"FontIngresEN": "example",
		"FormaIngresCA": "exemple",
		"FormaIngresES": "ejemplo",
		"FormaIngresEN": "example",
		"LlocFabricacioCA": "exemple",
		"LlocFabricacioES": "ejemplo",
		"LlocFabricacioEN": "example",
		"NomElementCA": "exemple",
		"NomElementES": "ejemplo",
		"NomElementEN": "example",
		"Longitud": 1,
		"Pes": 1,
		"Potencia": 1,
		"KmsFets": 1,
		"SostreMaximDeVol": 1,
		"Velocitat": 1,
		"VelocitatMax": 1
	},
	{
		"NumeroInventari": 0000002,
		"Any": 2023,
		"Autonomia": 1,
		"CapacitatDiposit": 1,
		"CicleCA": "exemple",
		"CicleES": "ejemplo",
		"CicleEN": "example",
		"Cilindrada": 1,
		"DescripcioCA": "exemple",
		"DescripcioES": "ejemplo",
		"DescripcioEN": "example",
		"Envergadura": 1,
		"FontEnergiaCA": "exemple",
		"FontEnergiaES": "ejemplo",
		"FontEnergiaEN": "example",
		"FontIngresCA": "exemple",
		"FontIngresES": "ejemplo",
		"FontIngresEN": "example",
		"FormaIngresCA": "exemple",
		"FormaIngresES": "ejemplo",
		"FormaIngresEN": "example",
		"LlocFabricacioCA": "exemple",
		"LlocFabricacioES": "ejemplo",
		"LlocFabricacioEN": "example",
		"NomElementCA": "exemple",
		"NomElementES": "ejemplo",
		"NomElementEN": "example",
		"Longitud": 1,
		"Pes": 1,
		"Potencia": 1,
		"KmsFets": 1,
		"SostreMaximDeVol": 1,
		"Velocitat": 1,
		"VelocitatMax": 1
	}
]
"""
        */
        //contingut del json, sense contingut, per a carregar-se des de un arxiu
        val jsonString = String
        var elements = mutableListOf<Element>()
        //val btnJson = findViewById<Button>(R.id.BtnJson)

            //inici codi chatgpt dialeg arxiu json part 2
            openFileButton = findViewById(R.id.openFileButton)

            openFileButton.setOnClickListener {
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                intent.type = "application/json"
                getContent.launch(intent)
            }
            //final codi chatgpt dialeg arxiu json part 2

/*codi original per llegir string de jsons, però no d'obrir jsons. Copiat al mètode getContent()
        btnJson.setOnClickListener()
        {
            try {
                // Convertir el JSON string a un JSONArray
                val jsonArray = JSONArray(jsonString)

                // Iterar sobre cada objecte dins del JSONArray
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)

                    // Obtenir els valors de cada propietat
                    val numeroInventari = jsonObject.getInt("NumeroInventari")

                    val any = jsonObject.getInt("Any")
                    val autonomia = jsonObject.getInt("Autonomia")
                    val nomElementCA = jsonObject.getString("NomElementCA")
                    // Obté la resta de propietats de manera similar...


                    //FALTA afegir dades llegides al llistat d'elements
                    //elements.add(Element(numeroInventari,any,autonomia,0,"","","",0,"","","",0,"","","","","","","","","", R.drawable.moto,"","","",nomElementCA,"","",0,0,0,0,0,0,0))
                    //FALTA assignar una imatge per defecte en cas que no en tingui una.

                    // Aquí pots fer el que vulguis amb les dades llegides
                    // Per exemple, emmagatzemar-les en una llista, mostrar-les en un log, etc.
                    // println("Element $i: NumeroInventari=$numeroInventari, Any=$any, Autonomia=$autonomia")
                    Toast.makeText(this,"Importat número d'inventari " + numeroInventari + " amb denominació " + nomElementCA, Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this,"Error de lectura del .JSON", Toast.LENGTH_LONG).show()
            }
        }*/

    }
    //inici codi chatgpt dialeg arxiu json part 1
    private lateinit var openFileButton: Button

    //se li hauria de passar la variable jsonString per a que tota l'activity treballi amb la mateixa variable
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
                    while (reader.readLine().also { line = it } != null) {
                        stringBuilder.append(line).append("\n")
                    }

                    val jsonString = stringBuilder.toString()//original, abans de declarar la variable a onCreate
                    //jsonString = stringBuilder.toString()

                    // Processa el contingut del fitxer JSON (jsonString) com desitgis
                    // Aquí pots utilitzar libraries com Gson per a processar el JSON

                    try {
                        // Convertir el JSON string a un JSONArray
                        val jsonArray = JSONArray(jsonString)
                        llargadaJson = jsonArray.length()
                        Toast.makeText(this, "el JSON conté" + llargadaJson + " elements", Toast.LENGTH_SHORT).show()

                        // Iterar sobre cada objecte dins del JSONArray
                        for (i in 0 until llargadaJson) {
                            val jsonObject = jsonArray.getJSONObject(i)

                            // Obtenir els valors de cada propietat
                            val numeroInventari = jsonObject.getInt("NumeroInventari")

                            val any = jsonObject.getInt("Any")
                            val autonomia = jsonObject.getInt("Autonomia")
                            val nomElementCA = jsonObject.getString("NomElementCA")
                            // Obté la resta de propietats de manera similar...


                            //FALTA afegir dades llegides al llistat d'elements
                            //elements.add(Element(numeroInventari,any,autonomia,0,"","","",0,"","","",0,"","","","","","","","","", R.drawable.moto,"","","",nomElementCA,"","",0,0,0,0,0,0,0))
                            //FALTA assignar una imatge per defecte en cas que no en tingui una.

                            // Aquí pots fer el que vulguis amb les dades llegides
                            // Per exemple, emmagatzemar-les en una llista, mostrar-les en un log, etc.
                            // println("Element $i: NumeroInventari=$numeroInventari, Any=$any, Autonomia=$autonomia")
                            Toast.makeText(this,"Importat element " + i + ", número d'inventari " + numeroInventari + " amb denominació " + nomElementCA, Toast.LENGTH_SHORT).show()
                            elementsLlegitsJson++
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Toast.makeText(this,"Error de lectura del .JSON", Toast.LENGTH_LONG).show()
                    }
                    // Exemple: mostra el contingut llegit en el Logcat
                    //println(jsonString)
                    //Toast.makeText(this, jsonString, Toast.LENGTH_LONG).show()
                    Toast.makeText(this, "Importats " + elementsLlegitsJson + " de " + llargadaJson + " elements que conté el JSON", Toast.LENGTH_LONG).show()

                }
            }
        }
//fi codi chatgpt dialeg arxiu json part 1
}
