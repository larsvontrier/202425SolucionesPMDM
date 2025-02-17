package com.pepinho.nba.model.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.pepinho.nba.model.Conferencia
import com.pepinho.nba.model.Division
import com.pepinho.nba.model.Equipo

import java.lang.reflect.Type

class EquiposJsonDeserializer(): JsonDeserializer<List<Equipo>> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<Equipo> {
        val equipos = mutableListOf<Equipo>()

        val listaEquipos = json?.asJsonObject?.get("equipos")?.asJsonArray

        for(equipo in listaEquipos!!) {
            val equipoObj = equipo.asJsonObject
            val equipo = Equipo(equipoObj.get("idEquipo").asInt,
                equipoObj.get("abreviatura").asString,
                equipoObj.get("nombre").asString,
                equipoObj.get("ciudad").asString,
                equipoObj.get("ciudad").asString,
                Division.of(equipoObj.get("division").asString),
                Conferencia.of(equipoObj.get("conferencia").asString),
                null, false, null)

            equipos.add(equipo)
        }
        return equipos
    }
}
