package com.pepinho.nbajsondata.model.adapters

import com.pepinho.nbajsondata.model.Conferencia
import com.pepinho.nbajsondata.model.Division
import com.pepinho.nbajsondata.model.Equipo

import android.util.Log
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import com.pepinho.nbajsondata.model.enumByNameIgnoreCase
import java.io.IOException

class EquiposJsonTypeAdapter : TypeAdapter<List<Equipo>>() {
    @Throws(IOException::class)
    override fun write(jsonWriter: JsonWriter, equipos: List<Equipo>) {
        // TODO: de momento sólo leemos. No precisamos escribir
    }

    @Throws(IOException::class)
    override fun read(jsonReader: JsonReader): List<Equipo> {
        val equipos = mutableListOf<Equipo>()
        if(jsonReader.peek() == JsonToken.BEGIN_OBJECT) {
            jsonReader.beginObject()
            while (jsonReader.hasNext()) {
                when (jsonReader.nextName()) {
                    "equipos" -> {
                        jsonReader.beginArray()
                        while (jsonReader.hasNext()) {
                            val equipo = Equipo()
                            jsonReader.beginObject()
                            while (jsonReader.hasNext()) {
                                when (jsonReader.nextName()) {
                                    "idEquipo" -> equipo.idEquipo = jsonReader.nextInt()
                                    "nombre" -> equipo.nombre = jsonReader.nextString().orEmpty()
                                    "division" -> equipo.division = enumByNameIgnoreCase<Division>(jsonReader.nextString()) ?: Division.PACIFIC
                                    "conferencia" -> equipo.conferencia = enumByNameIgnoreCase<Conferencia>(jsonReader.nextString()) ?: Conferencia.WEST
                                    "ciudad" -> equipo.ciudad = jsonReader.nextString().orEmpty()
                                    "abreviatura" -> equipo.abreviatura = jsonReader.nextString().orEmpty()
                                    "nombreCompleto" -> equipo.nombreCompleto = jsonReader.nextString().orEmpty()
                                    else -> jsonReader.skipValue()
                                }
                            }
                            jsonReader.endObject()
                            equipos.add(equipo)
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



