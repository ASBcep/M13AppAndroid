package CEP.PolisTecnics.m13appandroid

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Activity1 : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity1)

        val act1FrameText = findViewById<TextView>(R.id.act1FrameText)
        act1FrameText.setTextAppearance(R.layout.dimensions_frame)
    }
}