package com.pepinho.nba.gson

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

class EquiposTypeAdapter : TypeAdapter<Equipos?>() {
    @Throws(IOException::class)
    override fun write(jsonWriter: JsonWriter, equipos: Equipos?) {
    }

    @Throws(IOException::class)
    override fun read(jsonReader: JsonReader): Equipos? {
        val equipos = Equipos()

        if(jsonReader.peek() == JsonToken.BEGIN_OBJECT) {
            jsonReader.beginObject()
            while (jsonReader.hasNext()) {
                when (jsonReader.nextName()) {
                    "data" -> {
                        jsonReader.beginArray()
                        while (jsonReader.hasNext()) {
                            val equipo = Equipo(photo = null)
                            jsonReader.beginObject()
                            while (jsonReader.hasNext()) {
                                when (jsonReader.nextName()) {
                                    "id" -> equipo.idEquipo = jsonReader.nextInt()
                                    "name" -> equipo.nombre = jsonReader.nextString().orEmpty()
                                    "division" -> equipo.division = enumByNameIgnoreCase<Division>(jsonReader.nextString()) ?: Division.PACIFIC
                                    "conference" -> equipo.conferencia = enumByNameIgnoreCase<Conferencia>(jsonReader.nextString()) ?: Conferencia.WEST
                                    "city" -> equipo.ciudad = jsonReader.nextString().orEmpty()
                                    "abbreviation" -> equipo.abreviatura = jsonReader.nextString().orEmpty()
                                    "full_name" -> equipo.nombreCompleto = jsonReader.nextString().orEmpty()
                                    else -> jsonReader.skipValue()
                                }
                            }
                            jsonReader.endObject()
                            equipos.equipos.add(equipo)
                        }
                        jsonReader.endArray()
                    }
                    else -> jsonReader.skipValue()
                }
            }
            jsonReader.endObject()
        } else if(jsonReader.peek()== JsonToken.NAME) {
            Log.i("TOKEN: ", jsonReader.nextName())
        }

        return equipos
    }


}
