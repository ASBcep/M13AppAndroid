package CEP.PolisTecnics.m13appandroid

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Activity5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity5)


        val botonatras: TextView = findViewById(R.id.botonatras)
        val btnJugar: TextView = findViewById(R.id.btnJugar)
        val botonidiomas1: TextView = findViewById(R.id.botonidiomas1)
        botonatras.setOnClickListener {
            // Crear un Intent para abrir Activity2
            val intent = Intent(this@Activity5, Activity4::class.java)
            startActivity(intent)
        }
        btnJugar.setOnClickListener {
            // Crear un Intent para abrir Activity2
            val intent = Intent(this@Activity5, Activity6::class.java)
            startActivity(intent)
        }
        botonidiomas1.setOnClickListener {
            // Crear un Intent para abrir idiomas
            val intent = Intent(this@Activity5, idiomas::class.java)
            startActivity(intent)
        }
    }
}