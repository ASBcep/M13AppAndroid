import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.mnactecapp.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            if (it.resultCode == RESULT_OK) {
                reiniciarActividad()
            } else if (it.resultCode == RESULT_CANCELED) {
                // Manejar el resultado cancelado si es necesario
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imgElement: ImageView = findViewById(R.id.imgElementMain)
        val txtElement: TextView = findViewById(R.id.act1FrameText)
        val botonpasar: TextView = findViewById(R.id.botonpasar)
        val botonidiomas1: TextView = findViewById(R.id.botonidiomas1)
        val btnField: Button = findViewById(R.id.btnField)
        val TvAct1Field: TextView = findViewById(R.id.TvAct1Field)

        FieldsList(this)

        val field = ElementManager.indexField
        val elementsList = ElementsList(this)
        val elementsField = elementsList.loadField(field)

        val elementToShow = elementsField.random()

        try {
            txtElement.text = elementToShow.nameElement
            val imgElementPath = filesDir.toString() + "/imgelements/" + elementToShow.image
            val bitmap = BitmapFactory.decodeFile(imgElementPath)
            if (bitmap != null) {
                imgElement.setImageBitmap(bitmap)
            }
            TvAct1Field.text = ElementManager.fields[field].nameField
        } catch (e: Exception) {
            // Manejar la excepción si es necesario
        }

        botonpasar.setOnClickListener {
            val intent = Intent(this, Activity3::class.java)
            intent.putExtra(Activity3.elementShownConstant.ELEMENT, elementToShow)
            intent.putExtra(Activity3.elementShownConstant.FIELD, field)
            startActivity(intent)
        }

        botonidiomas1.setOnClickListener {
            val intent = Intent(this, idiomas::class.java)
            getResult.launch(intent)
        }

        btnField.setOnClickListener {
            val intent = Intent(this, Activity4::class.java)
            startActivity(intent)
        }
    }

    fun reiniciarActividad() {
        finish()
        val intent = Intent(this, this::class.java)
        startActivity(intent)
    }
}
/*codi branch main 30/11/2023

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonpasar: TextView = findViewById(R.id.botonpasar)
        val btnMesInfo: Button = findViewById(R.id.BtnMesInfo)
        val botonidiomas1: TextView = findViewById(R.id.botonidiomas1)
        val btnJsonShortcut = findViewById<Button>(R.id.BtnJsonShortcut)
        botonpasar.setOnClickListener {
            // Crear un Intent para abrir Activity3
            val intent = Intent(this@MainActivity, Activity3::class.java)
            startActivity(intent)

        }
        // Crear un Intent para abrir Activity3
        btnMesInfo.setOnClickListener {
            val intent = Intent(this@MainActivity, Activity3::class.java)
            startActivity(intent)
        }

        botonidiomas1.setOnClickListener {
            // Crear un Intent para abrir idiomas
            val intent = Intent(this@MainActivity, idiomas::class.java)
            startActivity(intent)
        }

        btnJsonShortcut.setOnClickListener{
            val intent = Intent(this@MainActivity, carregaJson::class.java)
            startActivity(intent)
        }
    }
}
 */