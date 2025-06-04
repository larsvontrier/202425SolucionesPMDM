package com.pepinho.pokemonapi.model

import java.util.Locale

data class PokemonDataResponse(
    val id: Int,
    val name: String,
    val height: Int, // En decímetros (Tengo que dividir entre 10)
    val weight: Int, // En hectogramos (Tengo que dividir entre 10)
    val types: List<PokemonType>,
    val stats: List<PokemonStat>,
    val sprites: PokemonSprites
) {
//    fun toPokemon(description: String): PokemonData {
//        return PokemonData(
//            id = id,
//            name = name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
//            type = types.joinToString("/") { it.type.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }},
//            imageUrl = sprites.front_default ?: "",
//            description = description,
//            height = height / 10.0,
//            weight = weight / 10.0,
//            stats = stats.associate { stat ->
//                when(stat.stat.name) {
//                    "hp" -> "HP" to stat.base_stat
//                    "attack" -> "Attack" to stat.base_stat
//                    "defense" -> "Defense" to stat.base_stat
//                    "special-attack" -> "Sp. Atk" to stat.base_stat
//                    "special-defense" -> "Sp. Def" to stat.base_stat
//                    "speed" -> "Speed" to stat.base_stat
//                    else -> stat.stat.name.capitalize() to stat.base_stat
//                }
//            }
//        )
//    }
}

data class PokemonType(
    val type: Type
)

data class Type(
    val name: String
)

data class PokemonStat(
    val base_stat: Int,
    val stat: Stat
)

data class Stat(
    val name: String
)

data class PokemonSprites(
    val front_default: String?
)

fun PokemonDataResponse.toPokemonData(): PokemonData {
    return PokemonData(
        id = id,
        name = name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
        type = types.firstOrNull()?.type?.name ?: "unknown",
        imageUrl = sprites.front_default ?: "",
        description = "Descripción no disponible en esta versión", // O haz una segunda llamada si quieres detalles
        height = height / 10.0,
        weight = weight / 10.0,
        stats = stats.associate { it.stat.name to it.base_stat }
    )
}
