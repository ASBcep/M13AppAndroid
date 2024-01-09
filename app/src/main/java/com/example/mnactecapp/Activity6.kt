package com.example.mnactecapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class Activity6 : AppCompatActivity() {

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            if(it.resultCode == RESULT_OK){
                reiniciarActividad()
            }else if(it.resultCode == RESULT_CANCELED){
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity6)

        //val botonatras: TextView = findViewById(R.id.botonatras)
        val botonidiomas1: Button = findViewById(R.id.botonidiomas1)
        val btnEasy: Button = findViewById(R.id.BtnFacil)
        val btnNormal: Button = findViewById(R.id.BtnNormal)
        val btnHard: Button = findViewById(R.id.BtnDificil)
        val btnConfigAdmin: Button = findViewById(R.id.btnConfigAdmin)
        val btnMainScreen: Button = findViewById(R.id.btnMainScreen)
        val act6FrameText: TextView = findViewById(R.id.act6FrameText)

        //mostrar text segons idioma
        when (ElementManager.idioma){
            0 -> {btnConfigAdmin.text = getString(R.string.btnAdvOptCAT)
                botonidiomas1.text = getString(R.string.btn_idioma)
                btnMainScreen.text = getString(R.string.btnIniciCAT)
                btnEasy.text = getString(R.string.btnEasyCAT)
                btnNormal.text = getString(R.string.btnMediumCAT)
                btnHard.text = getString(R.string.btnDificil)
                act6FrameText.text = getString(R.string.HeadAct6CAT)}
            1 -> {btnConfigAdmin.text = getString(R.string.btnAdvOptSPA)
                botonidiomas1.text = getString(R.string.btn_idioma)
                btnMainScreen.text = getString(R.string.btnIniciSPA)
                btnEasy.text = getString(R.string.btnEasySPA)
                btnNormal.text = getString(R.string.btnMediumSPA)
                btnHard.text = getString(R.string.btnDificil)
                act6FrameText.text = getString(R.string.HeadAct6SPA)}
            2 -> {btnConfigAdmin.text = getString(R.string.btnAdvOptENG)
                botonidiomas1.text = getString(R.string.btn_language)
                btnMainScreen.text = getString(R.string.btnIniciENG)
                btnEasy.text = getString(R.string.btnEasyENG)
                btnNormal.text = getString(R.string.btnMediumENG)
                btnHard.text = getString(R.string.btnHard)
                act6FrameText.text = getString(R.string.HeadAct6ENG)}
        }

        /*botonatras.setOnClickListener {
            // Crear un Intent para abrir Activity5
            val intent = Intent(this, Activity5::class.java)
            startActivity(intent)
        }*/
        botonidiomas1.setOnClickListener {
            // Crear un Intent para abrir idiomas
            val intent = Intent(this, idiomas::class.java)
            getResult.launch(intent)
        }
        btnEasy.setOnClickListener{
            val dificulty = 1;
            /*val intent = Intent(this, Launcher::class.java)
            intent.putExtra(Launcher.launcher.DIFICULTY, dificulty)
            startActivity(intent)*/
            launchGame(dificulty)
        }
        btnNormal.setOnClickListener{
            val dificulty = 2;
            /*val intent = Intent(this, Launcher::class.java)
            intent.putExtra(Launcher.launcher.DIFICULTY, dificulty)
            startActivity(intent)*/
            launchGame(dificulty)
        }
        btnHard.setOnClickListener{
            val dificulty = 3;
            /*val intent = Intent(this, Launcher::class.java)
            intent.putExtra(Launcher.launcher.DIFICULTY, dificulty)
            startActivity(intent)*/
            launchGame(dificulty)
        }
        btnConfigAdmin.setOnClickListener{
            // Crear un Intent para abrir ConfigAdmin
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }
        btnMainScreen.setOnClickListener {
            // Crear un Intent para abrir la pantalla principal
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    //comprovació de si les imatges del joc estan a la carpeta gameimg o si s'han d'importar
    private fun checkGameImages(): Boolean {
        val directoryPath = "${filesDir}/gameimg"

        val directory = File(directoryPath)
        if (directory.exists() && directory.isDirectory) {
            val files = directory.listFiles()
            return files?.size ?:0 > 1
        }
        return false
    }
    private fun launchGame(dificulty: Int){
        if (checkGameImages()) {
            val intent = Intent(this, Launcher::class.java)
            intent.putExtra(Launcher.launcher.DIFICULTY, dificulty)
            startActivity(intent)
        } else {
            var toast = ""
             when (ElementManager.idioma){
            0 -> {toast = getString(R.string.gameNoImagesCAT)}
            1 -> {toast = getString(R.string.gameNoImagesSPA)}
            2 -> {toast = getString(R.string.gameNoImagesENG)}
        }
            //Toast.makeText(this,"No es pot iniciar el joc, si us plau importeu les imatges del joc", Toast.LENGTH_LONG).show()
            Toast.makeText(this,toast, Toast.LENGTH_LONG).show()
        }
    }

    fun reiniciarActividad() {
        // Cerrar la actividad actual
        finish()
        // Crear un nuevo Intent para reiniciar la actividad
        val intent = Intent(this, this::class.java)
        startActivity(intent)
    }
}
/*codi branch main 30/11/2023

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
 */