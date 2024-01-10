package com.example.mnactecapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.Toast

class idiomas : AppCompatActivity() {

    // Timer y Runnable para reiniciar actividad después de inactividad
    private val handler = Handler()
    private val inactivityRunnable = Runnable {
        reiniciarActividad()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_idiomas)

        // Inicialización de vistas
        val btnCat = findViewById<Button>(R.id.btnCat)
        val btnSpa = findViewById<Button>(R.id.btnSpa)
        val btnEng = findViewById<Button>(R.id.btnEng)
        val btnMainScreen: Button = findViewById(R.id.btnMainScreen)
        val btnList: Button = findViewById(R.id.btnList)

        // Detectar toques en la vista raíz para reiniciar la actividad después de 30 segundos de inactividad
        val rootView = findViewById<View>(android.R.id.content)
        rootView.setOnTouchListener { _, _ ->
            resetInactivityTimer()
            false
        }

        // Botones para cambiar idioma
        btnCat.setOnClickListener {
            cambiarIdioma(0, "Idioma canviat a català")
        }
        btnSpa.setOnClickListener {
            cambiarIdioma(1, "Idioma cambiado a castellano")
        }
        btnEng.setOnClickListener {
            cambiarIdioma(2, "Language changed to English")
        }

        btnMainScreen.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        btnList.setOnClickListener {
            startActivity(Intent(this, Activity4::class.java))
        }
    }

    private fun cambiarIdioma(idioma: Int, mensaje: String) {
        ElementManager.idioma = idioma
        if (ElementManager.debug) {
            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
        }
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun resetInactivityTimer() {
        handler.removeCallbacks(inactivityRunnable)
        handler.postDelayed(inactivityRunnable, 30000) // 30 segundos
    }

    private fun reiniciarActividad() {
        finish()
        val intent = Intent(this, this::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        resetInactivityTimer()
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(inactivityRunnable)
    }
}
