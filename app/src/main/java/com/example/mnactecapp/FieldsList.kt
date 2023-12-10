package com.example.mnactecapp

import android.content.Context
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.FileReader

class FieldsList(context: Context) {

    private val jsonFilePath = context.filesDir.toString() + "/json/fields.json"
    private val fields: MutableList<Field>

    init {
        fields = try {
            val jsonFile = FileReader(jsonFilePath)
            val listElementsType = object : TypeToken<List<Element>>() {}.type

            // Realizar la deserialización y almacenarla en una lista inmutable
            val fieldsList: List<Field> = Gson().fromJson(jsonFile, listElementsType)

            // Convertir la lista inmutable a una lista mutable
            fieldsList.toMutableList()
        } catch (e: Exception) {
            Toast.makeText(context, "Error en llegir el JSON: ${e.message}", Toast.LENGTH_LONG)
                .show()
            // Retorna una lista con un ámbito de muestra en caso de excepción para evitar valores nulos
            mutableListOf(
                Field(0,"Tots els àmbits")
            )
        }
        //carrego la llista d'àmbits a l'objecte GLOBAL
        ElementManager.fields = fields
    }
}