package com.example.mnactecapp


object ElementManager {
    //llistat d'objectes Element per a accedir-hi des de totes les classes
    var elements = mutableListOf<Element>()

    //Índex per al llistat elements, per localitzar un Element en particular (p.e. per mostrar a la pantalla de detalls del vehicle)
    var indexElements: Int = -1

    //Índex global per conèixer element per defecte
    var defaultElement = 0

    //llistat d'objectes Field (àmbit) per a accedir-hi des de totes les classes
    var fields = mutableListOf<Field>()

    //Índex global per conèixer Àmbit
    var indexField = 1

    //Índex global per conèixer Àmbit (s'hauria d'esborrar i calcular amb una funció de mutablelist)
    var totalField: Int = 1

    //Definició d'idioma: 0 cat, 1 spa, 2 eng
    var idioma: Int = 0
    //var idioma = String
}