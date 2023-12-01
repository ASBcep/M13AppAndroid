package com.example.mnactecapp

import java.io.Serializable

class Element (val numInventory: Int,
               val field: Int,
               val nameElement: String,
               val image: String,
               val description: String,
               val autonomy: Int,
               val disposalCapacity: Int,
               val cicle: String,
               val cilindrada: Int,
               val wingspan: Int,
               val energyFont: String,
               val sourceIncome: String,
               val formIncome: String,
               val manufacturingPlace: String,
               val length: Int,
               val weight: Int,
               val potency: Int,
               val kmsDone: Int,
               val sostreMaximDeVol: Int,
               val speed: Int,
               val maxSpeed: Int,
               val inicialElement: Boolean,
               val year: Int) : Serializable

/*codi branch main 30/11/2023
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
    //,val prova: String
)*/