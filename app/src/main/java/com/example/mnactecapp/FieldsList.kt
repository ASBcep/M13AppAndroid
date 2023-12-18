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
            if(ElementManager.debug){
                Toast.makeText(context, "Error en llegir el JSON d'àmbits: ${e.message}", Toast.LENGTH_LONG).show()
            }
            // Retorna una lista con un ámbito de muestra en caso de excepción para evitar valores nulos
            mutableListOf(
                Field(0,"Tots els àmbits"),
                Field(1,"Transport de persones i mercaderies"),
                Field(2,"Motocicletes catalanes"),
                Field(3,"Bombers"),
                Field(4,"Microcotxes, elèctrics i prototips únics"),
                Field(5,"La revolució de l'utilitari"),
                Field(6,"Els inicis de l'aviació"),
                )
        }
        //carrego la llista d'àmbits a l'objecte GLOBAL
        ElementManager.fields = fields
    }
}