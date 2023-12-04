import android.content.Context
import android.widget.Toast
import com.example.mnactecapp.Element
import com.example.mnactecapp.ElementManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.FileReader

class ElementsList(context: Context) {
    private val jsonFilePath = context.filesDir.toString() + "/json/elements.json"
    /*prova try/catch
    // Lista completa de elementos
    private val elements: List<Element> by lazy {
        val jsonFile = FileReader(jsonFilePath)
        val listElementsType = object : TypeToken<List<Element>>() {}.type
        Gson().fromJson(jsonFile, listElementsType)*/
    //
    private val elements: List<Element> by lazy {
        try {
            val jsonFile = FileReader(jsonFilePath)
            val listElementsType = object : TypeToken<List<Element>>() {}.type
            Gson().fromJson(jsonFile, listElementsType)
            //ElementManager.elements = listElementsType
        } catch (e: Exception) {
            // Mostra un missatge d'error en cas que es produeixi una excepció
            Toast.makeText(context, "Error en llegir el JSON: ${e.message}", Toast.LENGTH_LONG).show()
            //carrego un element de mostra a la llista
            listOf(Element(12345,
            1,
            "Cotxe d'Exemple",
            "12345",
            "El cotxe, una innovació que ha transformant la societat i la manera en què ens desplacem, té els seus orígens en la tardor del segle XIX. Karl Benz és àmpliament reconegut com l'inventor del primer cotxe pràctic propulsat per un motor de gasolina el 1885. Aquesta innovació va marcar el naixement de la indústria automotriu moderna. Amb el temps, els cotxes han evolucionat des de vehicles rudimentaris fins a sofisticades màquines de transport amb una gamma diversa de funcionalitats i tecnologies integrades. La producció massiva, iniciada per Henry Ford amb la línia de muntatge, va democratitzar l'accés als vehicles, canviant la manera en què les persones viuen i treballen. La cultura del cotxe va créixer amb el pas del temps, convertint-se en un símbol de llibertat, aventura i autonomia. Ha influït profundament en el desenvolupament urbà, la planificació del transport i la mobilitat personal. Tanmateix, el seu impacte ambiental i els reptes relacionats amb la congestió del trànsit i la seguretat vial han estat temes clau. A mesura que la tecnologia avança, els cotxes elèctrics, autònoms i altres innovacions estan liderant el camí cap a una nova era de la mobilitat sostenible i connectada.",
            111,
            222,
            "Cicle de la vida",
            333,
            444,
            "Gasofa",
            "Donatiu",
            "Herència",
            "Barcelona, Catalunya",
            555,
            666,
            777,
            888,
            999,
            1000,
            2000,
            true,
            2023)) // Retorna una llista amb 1 element per defecte en cas d'excepció per evitar valors nuls
        }
        /*
        var eleMut = MutableList<Element>
        eleMut = elements.getValue.toMutableList()
        ElementManager.elements = eleMut
        */
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

    fun loadField(field:Int,):List<Element>{

        when(field){
            1 -> return elementsField1
            2 -> return elementsField2
            3 -> return elementsField3
            4 -> return elementsField4
            5 -> return elementsField5
            6 -> return elementsField6
        }
        return elementsField1
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
                "Autonomia: ${elementShown.autonomy} km",
                "Capacitat de disposició: ${elementShown.disposalCapacity} kg",
                "Cicle: ${elementShown.cicle}",
                "Cilindrada: ${elementShown.cilindrada} cc",
                "Envergadura: ${elementShown.wingspan} m",
                "Font d'energia: ${elementShown.energyFont}",
                "Font d'ingressos: ${elementShown.sourceIncome}",
                "Forma d'ingressos: ${elementShown.formIncome}",
                "Lloc de fabricació: ${elementShown.manufacturingPlace}",
                "Longitud: ${elementShown.length} m",
                "Pes: ${elementShown.weight} kg",
                "Potència: ${elementShown.potency}",
                "Kilòmetres recorreguts: ${elementShown.kmsDone} km",
                "Sostre màxim de vol: ${elementShown.sostreMaximDeVol} km",
                "Velocitat: ${elementShown.speed} km/h",
                "Velocitat màxima: ${elementShown.maxSpeed} km/h"
            )
        } else {
            // Manejar el caso en el que el elemento no tenga todas las propiedades no nulas
            return arrayOf("Datos incompletos")
        }
    }
}