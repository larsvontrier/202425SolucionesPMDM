package com.pepinho.pmdm.model.gson

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import com.pepinho.pmdm.model.Conferencia
import com.pepinho.pmdm.model.Division
import com.pepinho.pmdm.model.Equipo
import java.io.IOException

class EquiposTypeAdapter : TypeAdapter<Equipos?>() {
    @Throws(IOException::class)
    override fun write(jsonWriter: JsonWriter, equipos: Equipos?) {
    }

    @Throws(IOException::class)
    override fun read(jsonReader: JsonReader): Equipos? {
        val equipos = Equipos()
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
                                "division" -> equipo.division = Division.valueOf(jsonReader.nextString())
                                "conference" -> equipo.conferencia = Conferencia.valueOf(jsonReader.nextString())
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
        return equipos
    }
}
