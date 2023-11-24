package CEP.PolisTecnics.m13appandroid

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import org.json.JSONArray

class carregaJson : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrega_json)



// El teu JSON
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
	},
]
"""

    val btnJson = findViewById<Button>(R.id.BtnJson)
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

                // Aquí pots fer el que vulguis amb les dades llegides
                // Per exemple, emmagatzemar-les en una llista, mostrar-les en un log, etc.
                println("Element $i: NumeroInventari=$numeroInventari, Any=$any, Autonomia=$autonomia")
                Toast.makeText(this,"el num inventari és: " + numeroInventari, Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this,"Error de lectura del .JSON", Toast.LENGTH_LONG).show()
        }
    }
    }
}
