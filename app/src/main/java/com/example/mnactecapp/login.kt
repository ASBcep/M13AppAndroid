package com.example.mnactecapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class login : AppCompatActivity() {

    lateinit var textViewTitulo: TextView
    lateinit var btnAcceder: Button
    lateinit var btnVolver: Button
    lateinit var editTextNombreUsuario: EditText
    lateinit var editTextContraseña: EditText

    // Declarar Handler y Runnable para inactividad
    private val handler = Handler()
    private val inactivityRunnable = Runnable {
        mostrarToast("Sin actividad. Redirigiendo a MainActivity.")
        startActivity(Intent(this, MainActivity::class.java))
        finish() // Finalizar esta actividad para evitar que el usuario vuelva aquí al presionar el botón Atrás.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicialización de vistas
        textViewTitulo = findViewById(R.id.textViewTitulo)
        btnAcceder = findViewById(R.id.btnAcceder)
        btnVolver = findViewById(R.id.btnVolver) // Agregado
        editTextNombreUsuario = findViewById(R.id.editTextNombreUsuario)
        editTextContraseña = findViewById(R.id.editTextContraseña)

        // Botón Acceder
        btnAcceder.setOnClickListener {
            val nom = editTextNombreUsuario.text.toString()
            val contra = editTextContraseña.text.toString()

            if (nom == "admin" && contra == "1234") {
                val intent = Intent(this, ConfigAdmin::class.java)
                startActivity(intent)
            } else {
                mostrarToast("Nom o contrasenya incorrectes")
            }

            // Resetear temporizador de inactividad
            resetInactivityTimer()
        }

        // Botón Volver
        btnVolver.setOnClickListener {
            // Iniciar MainActivity
            startActivity(Intent(this, MainActivity::class.java))
            finish() // Finalizar esta actividad para evitar que el usuario vuelva aquí al presionar el botón Atrás.
        }

        // Actualizar idioma
        actualizarIdioma()

        // Iniciar temporizador de inactividad
        resetInactivityTimer()
    }

    private fun mostrarToast(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    private fun actualizarIdioma() {
        // ... (código anterior)
    }

    private fun setearTextoIdioma(btnTexto: String, nombreUsuarioTexto: String, contraseñaTexto: String, tituloTexto: String) {
        // ... (código anterior)
    }

    private fun resetInactivityTimer() {
        // ... (código anterior)
    }

    override fun onResume() {
        super.onResume()
        // Reiniciar temporizador de inactividad
        resetInactivityTimer()
    }

    override fun onPause() {
        super.onPause()
        // Detener temporizador de inactividad
        handler.removeCallbacks(inactivityRunnable)
    }
}
