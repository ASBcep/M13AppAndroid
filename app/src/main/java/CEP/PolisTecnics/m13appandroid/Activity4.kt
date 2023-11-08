package CEP.PolisTecnics.m13appandroid

import android.content.Intent
import android.os.Bundle
import android.widget.GridView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Activity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity4)

        val botonpasar: TextView = findViewById(R.id.botonpasar)
        val botonatras: TextView = findViewById(R.id.botonatras)

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


        var elements = mutableListOf(
            Element("MOTOCICLETA SANGLAS 400", R.drawable.moto),
            Element("MOTOCICLETA SANGLAS 400", R.drawable.moto),
            Element("MOTOCICLETA SANGLAS 400", R.drawable.moto),
            Element("MOTOCICLETA SANGLAS 400", R.drawable.moto),
            Element("MOTOCICLETA SANGLAS 400", R.drawable.moto),
            Element("MOTOCICLETA SANGLAS 400", R.drawable.moto),
            Element("MOTOCICLETA SANGLAS 400", R.drawable.moto),
            Element("MOTOCICLETA SANGLAS 400", R.drawable.moto),
            Element("MOTOCICLETA SANGLAS 400", R.drawable.moto),
            Element("MOTOCICLETA SANGLAS 400", R.drawable.moto),
            Element("MOTOCICLETA SANGLAS 400", R.drawable.moto),
        )

        val listAltresElements = findViewById<GridView>(R.id.ListAltresElements)
        val adapter = ElementAdapter(this, R.layout.element_itema4, elements)
        listAltresElements.adapter = adapter
    }

}