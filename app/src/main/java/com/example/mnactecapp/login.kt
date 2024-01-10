package com.example.mnactecapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class login : AppCompatActivity() {

    lateinit var textViewTitulo: TextView
    lateinit var btnAcceder: Button
    lateinit var editTextNombreUsuario: EditText
    lateinit var editTextContraseña: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicializar vistas después de setContentView
        textViewTitulo = findViewById(R.id.textViewTitulo)
        btnAcceder = findViewById(R.id.btnAcceder)
        editTextNombreUsuario = findViewById(R.id.editTextNombreUsuario)
        editTextContraseña = findViewById(R.id.editTextContraseña)

        btnAcceder.setOnClickListener {
            val nom = editTextNombreUsuario.text.toString()
            val contra = editTextContraseña.text.toString()

            if (nom == "admin" && contra == "1234") {
                val intent = Intent(this, ConfigAdmin::class.java)
                startActivity(intent)
            } else {
                mostrarToast("Nom o contrasenya incorrectes")
            }
        }

        actualizarIdioma()
    }

    private fun mostrarToast(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    private fun actualizarIdioma() {
        when (ElementManager.idioma) {
            0 -> setearTextoIdioma(getString(R.string.btnAccederCAT), getString(R.string.editTextNombreUsuarioCAT), getString(R.string.editTextContraseñaCAT), getString(R.string.textViewTituloCAT))
            1 -> setearTextoIdioma(getString(R.string.btnAccederSPA), getString(R.string.editTextNombreUsuarioSPA), getString(R.string.editTextContraseñaSPA), getString(R.string.textViewTituloSPA))
            2 -> setearTextoIdioma(getString(R.string.btnAccederENG), getString(R.string.editTextNombreUsuarioENG), getString(R.string.editTextContraseñaENG), getString(R.string.textViewTituloENG))
        }
    }

    private fun setearTextoIdioma(btnTexto: String, nombreUsuarioTexto: String, contraseñaTexto: String, tituloTexto: String) {
        btnAcceder.text = btnTexto
        editTextNombreUsuario.setText(nombreUsuarioTexto)
        editTextContraseña.setText(contraseñaTexto)
        textViewTitulo.text = tituloTexto
    }
}
