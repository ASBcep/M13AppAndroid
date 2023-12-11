import android.content.Context
import android.widget.Toast
import com.example.mnactecapp.Element
import com.example.mnactecapp.ElementManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.FileReader

class ElementsList(context: Context) {


    private var routeJsonLang = ""
    //cribo por idiomas para leer un json u otro

    init {
        when (ElementManager.idioma) {
            0 -> routeJsonLang = "/json/elements_cat.json"
            1 -> routeJsonLang = "/json/elements_spa.json"
            2 -> routeJsonLang = "/json/elements_eng.json"
        }
    }
    private val jsonFilePath = context.filesDir.toString() + routeJsonLang
    private val elements: MutableList<Element>

    //inicialitzo la llista LOCAL d'elements
    init {
        elements = try {
            val jsonFile = FileReader(jsonFilePath)
            val listElementsType = object : TypeToken<List<Element>>() {}.type

            // Realizar la deserialización y almacenarla en una lista inmutable
            val elementsList: List<Element> = Gson().fromJson(jsonFile, listElementsType)

            // Convertir la lista inmutable a una lista mutable
            elementsList.toMutableList()
        } catch (e: Exception) {
            // Manejar la excepción aquí
            Toast.makeText(context, "Error en llegir el JSON d'elements: ${e.message}", Toast.LENGTH_LONG).show()

            // Retorna una lista con un elemento de muestra en caso de excepción para evitar valores nulos
            mutableListOf(
                Element(
                    12345,
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
                    2023
                )
            ) // Retorna una llista amb 1 element per defecte en cas d'excepció per evitar valors nuls
        }
        ElementManager.elements = elements
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

    //esta función qué hace??
    fun loadField(field:Int,):List<Element>{

        when(field){
            1 -> return elementsField1
            2 -> return elementsField2
            3 -> return elementsField3
            4 -> return elementsField4
            5 -> return elementsField5
            6 -> return elementsField6
        }
        return elements
    }
    // Función para filtrar y mapear un solo elemento según propiedades específicas
    fun filterAndMapElement(elementShown: Element): Array<String> {
        val resultArray = mutableListOf<String>()

        // Verificar cada propiedad y agregarla al array solo si no es nula y no está vacía y cribada según idioma
/*        if (elementShown.autonomy != null && elementShown.autonomy != 0) {
            resultArray.add("Autonomia: ${elementShown.autonomy} km")

        }*/
        if (elementShown.year != null && elementShown.year != 0) {
            resultArray.add(when (ElementManager.idioma){
                0 -> {"Any: ${elementShown.year}"}
                1 -> {"Año: ${elementShown.year}"}
                2 -> {"Year: ${elementShown.year}"}
                else -> {""}
                }
            )
        }
        if (elementShown.autonomy != null && elementShown.autonomy != 0) {
            resultArray.add(when (ElementManager.idioma){
                0 -> {"Autonomia: ${elementShown.autonomy} km"}
                1 -> {"Autonomía: ${elementShown.autonomy} km"}
                2 -> {"Autonomy: ${elementShown.autonomy} km"}
                else -> {""}
                }
            )
        }
        if (elementShown.disposalCapacity != null && elementShown.disposalCapacity != 0) {
            resultArray.add(when (ElementManager.idioma){
                0 -> {"Capacitat del dipòsit: ${elementShown.disposalCapacity} litres"}
                1 -> {"Capacidad del depósito: ${elementShown.disposalCapacity} litros"}
                2 -> {"Tank capacity: ${elementShown.disposalCapacity} liter"}
                else -> {""}
                }
            )
        }
        if (!elementShown.cicle.isNullOrBlank()) {
            resultArray.add(when (ElementManager.idioma){
                0, 2 -> {"Cicle: ${elementShown.cicle}"}
                1 -> {"Ciclo: ${elementShown.cicle}"}
                //2 -> {"Cicle: ${elementShown.cicle}"}
                else -> {""}
                }
            )
        }
        if (elementShown.cilindrada != null && elementShown.cilindrada != 0) {
            resultArray.add(when (ElementManager.idioma){
                0, 1 -> {"Cilindrada: ${elementShown.cilindrada} cc"}
                //1 -> {"Cilindrada: ${elementShown.cilindrada} cc"}
                2 -> {"Engine capacity: ${elementShown.cilindrada} cc"}
                else -> {""}
                }
            )
        }
        if (elementShown.wingspan != null && elementShown.wingspan != 0) {
            resultArray.add(when (ElementManager.idioma){
                0, 1 -> {"Envergadura: ${elementShown.wingspan} m"}
                //1 -> {"Envergadura: ${elementShown.wingspan} m"}
                2 -> {"Wingspan: ${elementShown.wingspan} m"}
                else -> {""}
                }
            )
        }
        if (!elementShown.energyFont.isNullOrBlank()) {
            resultArray.add(when (ElementManager.idioma){
                0 -> {"Font d'energia: ${elementShown.energyFont}"}
                1 -> {"Fuente de energía: ${elementShown.energyFont}"}
                2 -> {"Energy source: ${elementShown.energyFont}"}
                else -> {""}
                }
            )
        }
        if (!elementShown.sourceIncome.isNullOrBlank()) {
            resultArray.add(when (ElementManager.idioma){
                0 -> {"Font d'ingrés: ${elementShown.sourceIncome}"}
                1 -> {"Fuente de ingreso: ${elementShown.sourceIncome}"}
                2 -> {"Admission source: ${elementShown.sourceIncome}"}
                else -> {""}
                }
            )
        }
        if (!elementShown.formIncome.isNullOrBlank()) {
            resultArray.add(when (ElementManager.idioma){
                0 -> {"Forma d'ingrés: ${elementShown.formIncome}"}
                1 -> {"Forma de ingreso: ${elementShown.formIncome}"}
                2 -> {"Admission way: ${elementShown.formIncome}"}
                else -> {""}
                }
            )
        }
        if (!elementShown.manufacturingPlace.isNullOrBlank()) {
            resultArray.add(when (ElementManager.idioma){
                0 -> {"Lloc de fabricació: ${elementShown.manufacturingPlace}"}
                1 -> {"Lugar de fabricación: ${elementShown.manufacturingPlace}"}
                2 -> {"Manufacturing place: ${elementShown.manufacturingPlace}"}
                else -> {""}
                }
            )
        }
        if (elementShown.length != null && elementShown.length != 0) {
            resultArray.add(when (ElementManager.idioma){
                0, 1 -> {"Longitud: ${elementShown.length} m"}
                //1 -> {"Longitud: ${elementShown.length} m"}
                2 -> {"Length: ${elementShown.length} m"}
                else -> {""}
                }
            )
        }
        if (elementShown.weight != null && elementShown.weight != 0) {
            resultArray.add(when (ElementManager.idioma){
                0 -> {"Pes: ${elementShown.weight} kg"}
                1 -> {"Peso: ${elementShown.weight} kg"}
                2 -> {"Weight: ${elementShown.weight} kg"}
                else -> {""}
                }
            )
        }
        if (elementShown.potency != null && elementShown.potency != 0) {
            resultArray.add(when (ElementManager.idioma){
                0 -> {"Potència: ${elementShown.potency} kW"}
                1 -> {"Potencia: ${elementShown.potency} kW"}
                2 -> {"Power output: ${elementShown.potency} kW"}
                else -> {""}
                }
            )
        }
        if (elementShown.kmsDone != null && elementShown.kmsDone != 0) {
            resultArray.add(when (ElementManager.idioma){
                0 -> {"Quilòmetres recorreguts: ${elementShown.kmsDone} km"}
                1 -> {"Quilómetros recorridos: ${elementShown.kmsDone} km"}
                2 -> {"Kilometers made: ${elementShown.kmsDone} km"}
                else -> {""}
                }
            )
        }
        if (elementShown.sostreMaximDeVol != null && elementShown.sostreMaximDeVol != 0) {
            resultArray.add(when (ElementManager.idioma){
                0 -> {"Sostre màxim de vol: ${elementShown.sostreMaximDeVol} km"}
                1 -> {"Techo máximo de vuelo: ${elementShown.sostreMaximDeVol} km"}
                2 -> {"Highest height of flight: ${elementShown.sostreMaximDeVol} km"}
                else -> {""}
                }
            )
        }
        if (elementShown.speed != null && elementShown.speed != 0) {
            resultArray.add(when (ElementManager.idioma){
                0 -> {"Velocitat: ${elementShown.speed} km/h"}
                1 -> {"Velocidad: ${elementShown.speed} km/h"}
                2 -> {"Speed: ${elementShown.speed} km/h"}
                else -> {""}
                }
            )
        }
        if (elementShown.maxSpeed != null && elementShown.maxSpeed != 0) {
            resultArray.add(when (ElementManager.idioma){
                0 -> {"Velocitat màxima: ${elementShown.maxSpeed} km/h"}
                1 -> {"Velocidad máxima: ${elementShown.maxSpeed} km/h"}
                2 -> {"Maximum speed: ${elementShown.maxSpeed} km/h"}
                else -> {""}
                }
            )
        }
        if (elementShown.field != null && elementShown.field != 0) {
            resultArray.add(when (ElementManager.idioma){
                0 -> {"Àmbit: ${elementShown.field}"}
                1 -> {"Ámbito: ${elementShown.field}"}
                2 -> {"Field: ${elementShown.field}"}
                else -> {""}
            }
            )
        }

        // Verificar si se agregó al menos una propiedad al array
        if (resultArray.isNotEmpty()) {
            return resultArray.toTypedArray()
        } else {
            return arrayOf("Datos incompletos")
        }
    }




}
/* codi original 4/12/2023
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
    fun filterAndMapElementV2(elementShown: Element): Array<String> {
        val resultArray = mutableListOf<String>()

        // Verificar cada propiedad y agregarla al array solo si no es nula y no está vacía
        if (elementShown.autonomy != null && elementShown.autonomy != 0) {
            resultArray.add("Autonomia: ${elementShown.autonomy} km")
        }
        if (elementShown.disposalCapacity != null && elementShown.disposalCapacity != 0) {
            resultArray.add("Capacitat de disposició: ${elementShown.disposalCapacity} kg")
        }
        if (!elementShown.cicle.isNullOrBlank()) {
            resultArray.add("Cicle: ${elementShown.cicle}")
        }
        if (elementShown.cilindrada != null && elementShown.cilindrada != 0) {
            resultArray.add("Cilindrada: ${elementShown.cilindrada} cc")
        }
        if (elementShown.wingspan != null && elementShown.wingspan != 0) {
            resultArray.add("Envergadura: ${elementShown.wingspan} m")
        }
        if (!elementShown.energyFont.isNullOrBlank()) {
            resultArray.add("Font d'energia: ${elementShown.energyFont}")
        }
        if (!elementShown.sourceIncome.isNullOrBlank()) {
            resultArray.add("Font d'ingressos: ${elementShown.sourceIncome}")
        }
        if (!elementShown.formIncome.isNullOrBlank()) {
            resultArray.add("Forma d'ingressos: ${elementShown.formIncome}")
        }
        if (!elementShown.manufacturingPlace.isNullOrBlank()) {
            resultArray.add("Lloc de fabricació: ${elementShown.manufacturingPlace}")
        }
        if (elementShown.length != null && elementShown.length != 0) {
            resultArray.add("Longitud: ${elementShown.length} m")
        }
        if (elementShown.weight != null && elementShown.weight != 0) {
            resultArray.add("Pes: ${elementShown.weight} kg")
        }
        if (elementShown.potency != null && elementShown.potency != 0) {
            resultArray.add("Potència: ${elementShown.potency}")
        }
        if (elementShown.kmsDone != null && elementShown.kmsDone != 0) {
            resultArray.add("Kilòmetres recorreguts: ${elementShown.kmsDone} km")
        }
        if (elementShown.sostreMaximDeVol != null && elementShown.sostreMaximDeVol != 0) {
            resultArray.add("Sostre màxim de vol: ${elementShown.sostreMaximDeVol} km")
        }
        if (elementShown.speed != null && elementShown.speed != 0) {
            resultArray.add("Velocitat: ${elementShown.speed} km/h")
        }
        if (elementShown.maxSpeed != null && elementShown.maxSpeed != 0) {
            resultArray.add("Velocitat màxima: ${elementShown.maxSpeed} km/h")
        }

        // Verificar si se agregó al menos una propiedad al array
        if (resultArray.isNotEmpty()) {
            return resultArray.toTypedArray()
        } else {
            return arrayOf("Datos incompletos")
        }
    }




}
 */