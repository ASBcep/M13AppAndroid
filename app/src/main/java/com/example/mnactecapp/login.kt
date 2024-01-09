package com.example.mnactecapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnAcceder: Button = findViewById(R.id.btnAcceder)

        btnAcceder.setOnClickListener{

            val intent = Intent(this, ConfigAdmin::class.java)
            startActivity(intent)
        }
    }

}