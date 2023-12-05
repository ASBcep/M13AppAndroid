package com.example.mnactecapp

import android.content.Intent
import android.os.Bundle
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration

class Launcher : AndroidApplication() {

    object launcher{
        const val DIFICULTY = "DIFICULTY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val config = AndroidApplicationConfiguration()


        val intent = getIntent()
        val dificulty = intent.getIntExtra(launcher.DIFICULTY,1)

        // Crea un Intent y agrega la información como un extra
        val intentGame = Intent(this, Launcher::class.java)
        intentGame.putExtra("dificulty",dificulty )

        initialize(MyGame(intentGame,context), config)
    }
}