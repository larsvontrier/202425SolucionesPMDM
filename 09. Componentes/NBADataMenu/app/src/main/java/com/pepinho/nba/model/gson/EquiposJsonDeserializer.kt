package com.pepinho.nba.model.gson

import android.util.Log
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.pepinho.nba.model.Conferencia
import com.pepinho.nba.model.Division
import com.pepinho.nba.model.Equipo

import java.lang.reflect.Type

class EquiposJsonDeserializer : JsonDeserializer<List<Equipo>> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<Equipo> {
        val equipos = mutableListOf<Equipo>()

        // Verificar si el JSON es nulo o no tiene la estructura esperada
        val listaEquipos = json?.asJsonObject?.get("equipos")?.asJsonArray ?: return equipos

        for (equipoJson in listaEquipos) {
            try {
                val equipoObj = equipoJson.asJsonObject
                val equipo = Equipo(
                    idEquipo = equipoObj.get("idEquipo")?.asInt ?: 0,
                    abreviatura = equipoObj.get("abreviatura")?.asString ?: "",
                    nombre = equipoObj.get("nombre")?.asString ?: "",
                    nombreCompleto = equipoObj.get("nombreCompleto")?.asString ?: "",
                    ciudad = equipoObj.get("ciudad")?.asString ?: "",
                    division = Division.of(equipoObj.get("division")?.asString),
                    conferencia = Conferencia.of(equipoObj.get("conferencia")?.asString),
                    fecha = null,
                    isFavorito = false,
                    photo = null
                )
                equipos.add(equipo)
            } catch (e: Exception) {
                // Logear el error y continuar con el siguiente equipo
                Log.e("EquiposJsonDeserializer", "Error al deserializar equipo: ${e.message}")
            }
        }
        return equipos
    }
}
