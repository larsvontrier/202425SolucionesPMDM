package com.pepinho.pmdm.nba.gson

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import com.pepinho.nba.model.Conferencia
import com.pepinho.nba.model.Division
import com.pepinho.nba.model.Equipo
import com.pepinho.nba.model.enumByNameIgnoreCase
import java.io.IOException


class EquipoTypeAdapter : TypeAdapter<Equipo>() {
    @Throws(IOException::class)
    override fun write(writer: JsonWriter, equipo: Equipo) {
        writer.beginObject()
        writer.name("id").value(equipo.idEquipo)
        writer.name("abbreviation").value(equipo.abreviatura)
        writer.name("city").value(equipo.ciudad)
        writer.name("conference").value(equipo.conferencia.name)
        writer.name("division").value(equipo.division.name)
        writer.name("full_name").value(equipo.nombreCompleto)
        writer.name("name").value(equipo.nombre)
        writer.endObject()
    }

    @Throws(IOException::class)
    override fun read(reader: JsonReader): Equipo {
        val equipo: Equipo = Equipo(photo = null)
        reader.beginObject()
        while (reader.hasNext()) {
            val nombre = reader.nextName()
            when (nombre) {
                "id" -> equipo.idEquipo = reader.nextInt()
                "name" -> equipo.nombre = reader.nextString().orEmpty()
                "division" -> equipo.division = enumByNameIgnoreCase<Division>(reader.nextString()) ?: Division.PACIFIC
                "conference" -> equipo.conferencia = enumByNameIgnoreCase<Conferencia>(reader.nextString()) ?: Conferencia.WEST
                "city" -> equipo.ciudad = reader.nextString().orEmpty()
                "abbreviation" -> equipo.abreviatura = reader.nextString().orEmpty()
                "full_name" -> equipo.nombreCompleto = reader.nextString().orEmpty()
                else -> reader.skipValue()
            }
        }
        reader.endObject()
        return equipo
    }
}
