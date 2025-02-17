package com.pepinho.pmdm.nba

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.pepinho.pmdm.model.Equipo
import java.io.BufferedReader
import java.io.InputStreamReader

class EquiposFileRepository(context: Context) {

    private val equipos = mutableListOf<Equipo>()


    private fun getEquiposFromAssetsJSON(context: Context, path: String) {
        try {
            // Necesitamos saber el tipo de la lista de equipos.
            val tipo = object : TypeToken<List<Equipo>>(){}.type
            val gson: Gson = GsonBuilder()
                .registerTypeAdapter(tipo, EquiposFileAdapter())
                .create()
            // Abrimos un BufferedReader para leer el archivo JSON.
            // La función use() cierra el recurso automáticamente al finalizar.
            BufferedReader(InputStreamReader(context.assets.open(path))).use {
                equipos.addAll(gson.fromJson(it, tipo))
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}