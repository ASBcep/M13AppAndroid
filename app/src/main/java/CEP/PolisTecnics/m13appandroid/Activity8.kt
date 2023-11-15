package CEP.PolisTecnics.m13appandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Activity8 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_8)
        val botonidiomas1: TextView = findViewById(R.id.botonidiomas1)
        val partinfo: TextView = findViewById(R.id.partinfo)

        partinfo.setOnClickListener {
            // Crear un Intent para abrir Activity2
            val intent = Intent(this@Activity8, Activity1::class.java)
            startActivity(intent)
        }
        botonidiomas1.setOnClickListener {
            // Crear un Intent para abrir idiomas
            val intent = Intent(this@Activity8, idiomas::class.java)
            startActivity(intent)
        }
    }
}