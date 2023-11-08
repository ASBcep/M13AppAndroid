package CEP.PolisTecnics.m13appandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val botonpasar1a2: TextView = findViewById(R.id.botonpasar1a2)
        botonpasar1a2.setOnClickListener {
            // Crear un Intent para abrir Activity2
            val intent = Intent(this@MainActivity, Activity2::class.java)
            startActivity(intent)
        }
    }
}