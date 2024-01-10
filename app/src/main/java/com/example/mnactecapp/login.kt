package com.example.mnactecapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class login : AppCompatActivity() {
    var textViewTitulo : TextView = findViewById(R.id.textViewTitulo)
    var btnAcceder: Button = findViewById(R.id.btnAcceder)
    var editTextNombreUsuario: EditText = findViewById(R.id.editTextNombreUsuario)
    var editTextContraseña: EditText = findViewById(R.id.editTextContraseña)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



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
        when (ElementManager.idioma){
            0 -> {btnAcceder.text = getString(R.string.btnAccederCAT)
                editTextNombreUsuario.setText(getString(R.string.editTextNombreUsuarioCAT))
                editTextContraseña.setText(getString(R.string.editTextContraseñaCAT))
                textViewTitulo.text = getString(R.string.textViewTituloCAT)}

            1 -> {btnAcceder.text = getString(R.string.btnAccederSPA)
                editTextNombreUsuario.setText(getString(R.string.editTextNombreUsuarioSPA))
                editTextContraseña.setText(getString(R.string.editTextContraseñaSPA))
                textViewTitulo.text = getString(R.string.textViewTituloSPA)}

            2 -> {btnAcceder.text = getString(R.string.btnAccederENG)
                editTextNombreUsuario.setText(getString(R.string.editTextNombreUsuarioENG))
                editTextContraseña.setText(getString(R.string.editTextContraseñaENG))
                textViewTitulo.text = getString(R.string.textViewTituloENG) }
        }
    }

    private fun mostrarToast(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }



}
