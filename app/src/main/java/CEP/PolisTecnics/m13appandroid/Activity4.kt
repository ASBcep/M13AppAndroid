package CEP.PolisTecnics.m13appandroid

import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity

class Activity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity4)

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