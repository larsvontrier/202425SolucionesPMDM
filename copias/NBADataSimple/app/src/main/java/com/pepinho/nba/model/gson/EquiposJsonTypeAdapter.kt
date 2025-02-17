package com.pepinho.nba.model.gson

import android.util.Log
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import com.pepinho.nba.model.Conferencia
import com.pepinho.nba.model.Division
import com.pepinho.nba.model.Equipo
import com.pepinho.nba.model.enumByNameIgnoreCase
import java.io.IOException

class EquiposJsonTypeAdapter : TypeAdapter<List<Equipo>>() {

    @Throws(IOException::class)
    override fun write(jsonWriter: JsonWriter, equipos: List<Equipo>) {
        // TODO: Implementar si es necesario
    }

    @Throws(IOException::class)
    override fun read(jsonReader: JsonReader): List<Equipo> {
        val equipos = mutableListOf<Equipo>()

        if (jsonReader.peek() == JsonToken.BEGIN_OBJECT) {
            jsonReader.beginObject()
            while (jsonReader.hasNext()) {
                when (jsonReader.nextName()) {
                    "equipos" -> {
                        jsonReader.beginArray()
                        while (jsonReader.hasNext()) {
                            val equipo = parseEquipo(jsonReader)
                            equipos.add(equipo)
                        }
                        jsonReader.endArray()
                    }
                    else -> jsonReader.skipValue()
                }
            }
            jsonReader.endObject()
        } else {
            Log.w("EquiposJsonTypeAdapter", "JSON no tiene la estructura esperada")
        }

        return equipos
    }

    @Throws(IOException::class)
    private fun parseEquipo(jsonReader: JsonReader): Equipo {
        val equipo = Equipo()

        jsonReader.beginObject()
        while (jsonReader.hasNext()) {
            when (jsonReader.nextName()) {
                "idEquipo" -> equipo.idEquipo = jsonReader.nextInt()
                "nombre" -> equipo.nombre = jsonReader.nextString()
                "division" -> equipo.division = enumByNameIgnoreCase<Division>(jsonReader.nextString()) ?: Division.PACIFIC
                "conferencia" -> equipo.conferencia = enumByNameIgnoreCase<Conferencia>(jsonReader.nextString()) ?: Conferencia.WEST
                "ciudad" -> equipo.ciudad = jsonReader.nextString()
                "abreviatura" -> equipo.abreviatura = jsonReader.nextString()
                "nombreCompleto" -> equipo.nombreCompleto = jsonReader.nextString()
                else -> jsonReader.skipValue()
            }
        }
        jsonReader.endObject()

        return equipo
    }
}



