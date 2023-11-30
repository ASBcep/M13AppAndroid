import android.content.Context
import com.example.mnactecapp.Element
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.FileReader

class ElementsList(context: Context) {
    private val jsonFilePath = context.filesDir.toString() + "/json/elements.json"

    // Lista completa de elementos
    private val elements: List<Element> by lazy {
        val jsonFile = FileReader(jsonFilePath)
        val listElementsType = object : TypeToken<List<Element>>() {}.type
        Gson().fromJson(jsonFile, listElementsType)
    }

    // Listas filtradas por el campo "field"(ambito)
    val elementsField1: List<Element> by lazy {
        elements.filter { it.field == 1 }
    }
    val elementsField2: List<Element> by lazy {
        elements.filter { it.field == 2 }
    }
    val elementsField3: List<Element> by lazy {
        elements.filter { it.field == 3 }
    }
    val elementsField4: List<Element> by lazy {
        elements.filter { it.field == 4 }
    }
    val elementsField5: List<Element> by lazy {
        elements.filter { it.field == 5 }
    }
    val elementsField6: List<Element> by lazy {
        elements.filter { it.field == 6 }
    }

    // Función para filtrar y mapear un solo elemento según propiedades específicas
    fun filterAndMapElement(elementShown: Element): Array<String> {
        // Verificar si el elemento tiene todas las propiedades no nulas
        if (elementShown.autonomy != null &&
            elementShown.disposalCapacity != null &&
            elementShown.cicle != null &&
            elementShown.cilindrada != null &&
            elementShown.wingspan != null &&
            elementShown.energyFont != null &&
            elementShown.sourceIncome != null &&
            elementShown.formIncome != null &&
            elementShown.manufacturingPlace != null &&
            elementShown.length != null &&
            elementShown.weight != null &&
            elementShown.potency != null &&
            elementShown.kmsDone != null &&
            elementShown.sostreMaximDeVol != null &&
            elementShown.speed != null &&
            elementShown.maxSpeed != null
        ) {
            // Mapear las variables específicas y convertirlas a un array de strings
            return arrayOf(
                "${elementShown.autonomy}",
                "${elementShown.disposalCapacity}",
                "${elementShown.cicle}",
                "${elementShown.cilindrada}",
                "${elementShown.wingspan}",
                "${elementShown.energyFont}",
                "${elementShown.sourceIncome}",
                "${elementShown.formIncome}",
                "${elementShown.manufacturingPlace}",
                "${elementShown.length}",
                "${elementShown.weight}",
                "${elementShown.potency}",
                "${elementShown.kmsDone}",
                "${elementShown.sostreMaximDeVol}",
                "${elementShown.speed}",
                "${elementShown.maxSpeed}"
            )
        } else {
            // Manejar el caso en el que el elemento no tenga todas las propiedades no nulas
            return arrayOf("Datos incompletos")
        }
    }
}