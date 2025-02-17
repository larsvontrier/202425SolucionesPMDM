package com.pepinho.pmdm.nba

import android.util.Log
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import com.pepinho.pmdm.model.Conferencia
import com.pepinho.pmdm.model.Division
import com.pepinho.pmdm.model.Equipo
import java.lang.reflect.Type

class EquiposFileAdapter(): JsonDeserializer<List<Equipo>> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<Equipo> {
        val equipos: List<Equipo> = mutableListOf()

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
            equipos.plus(equipo)
        }

        return equipos
    }
}