package com.example.mnactecapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnAcceder: Button = findViewById(R.id.btnAcceder)
        val editTextNombreUsuario: EditText = findViewById(R.id.editTextNombreUsuario)
        val editTextContraseña: EditText = findViewById(R.id.editTextContraseña)

        btnAcceder.setOnClickListener{

            val nom = editTextNombreUsuario.text.toString()
            val contra = editTextContraseña.text.toString()

            if (nom == "admin" && contra == "1234") {
                val intent = Intent(this, ConfigAdmin::class.java)
                startActivity(intent)
            } else {
                mostrarToast("Nom o contrasenya incorrectes")
            }
        }
    }

    private fun mostrarToast(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}
