package CEP.PolisTecnics.m13appandroid

//class Element (val nomElement: String, val image: Int)

data class Element(
    val numeroInventari: Int,
    val ambit: String,
    val any: Int,
    val autonomia: Int,
    val capacitatDiposit: Int,
    val cicle: String,
    val cilindrada: Int,
    val descripcio: String,
    val elementPerDefecte: Boolean,
    val envergadura: Int,
    val fontEnergia: String,
    val fontIngres: String,
    val formaIngres: String,
    val image: Int,
    val llocFabricacio: String,
    val nomElement: String,
    val longitud: Int,
    val pes: Int,
    val potencia: Int,
    val kmsFets: Int,
    val sostreMaximDeVol: Int,
    val velocitat: Int,
    val velocitatMax: Int
    //prova
    ,val prova: String
)