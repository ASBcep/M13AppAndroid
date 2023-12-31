package CEP.PolisTecnics.m13appandroid

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Activity6 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity6)
        val botonatras: TextView = findViewById(R.id.botonatras)
        val botonidiomas1: TextView = findViewById(R.id.botonidiomas1)

        botonatras.setOnClickListener {
            // Crear un Intent para abrir Activity2
            val intent = Intent(this@Activity6, Activity5::class.java)
            startActivity(intent)
        }
        botonidiomas1.setOnClickListener {
            // Crear un Intent para abrir idiomas
            val intent = Intent(this@Activity6, idiomas::class.java)
            startActivity(intent)
        }
    }
}