package com.pepinho.pmdm.pokemonnav.repository

import com.pepinho.pmdm.pokemonnav.model.PokemonData

class PokemonListRepository : PokemonRepository{
    private val pokemonList = listOf(
        PokemonData(
            id = 1,
            name = "Bulbasaur",
            type = "Planta/Veneno",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
            description = "Una extraña semilla fue plantada en su espalda al nacer. La planta brota y crece con este Pokémon.",
            height = 0.7,
            weight = 6.9,
            stats = mapOf("HP" to 45, "Ataque" to 49, "Defensa" to 49)
        ),
        PokemonData(
            id = 2,
            name = "Ivysaur",
            type = "Planta/Veneno",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png",
            description = "Cuando el bulbo de su espalda crece, parece perder la capacidad de mantenerse en pie sobre sus patas traseras.",
            height = 1.0,
            weight = 13.0,
            stats = mapOf("HP" to 60, "Ataque" to 62, "Defensa" to 63)
        ),
        PokemonData(
            id = 3,
            name = "Venusaur",
            type = "Planta/Veneno",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png",
            description = "La planta florece cuando absorbe energía solar. Se mantiene en movimiento para buscar la luz del sol.",
            height = 2.0,
            weight = 100.0,
            stats = mapOf("HP" to 80, "Ataque" to 82, "Defensa" to 83)
        ),
        PokemonData(
            id = 4,
            name = "Charmander",
            type = "Fuego",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png",
            description = "La llama que arde en la punta de su cola es una indicación de su salud. Cuanto más caliente y grande es la llama, más saludable está el Pokémon.",
            height = 0.6,
            weight = 8.5,
            stats = mapOf("HP" to 39, "Ataque" to 52, "Defensa" to 43)
        ),
        PokemonData(
            id = 5,
            name = "Charmeleon",
            type = "Fuego",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/5.png",
            description = "Cuando agita su cola ardiente, eleva la temperatura a niveles insoportablemente altos.",
            height = 1.1,
            weight = 19.0,
            stats = mapOf("HP" to 58, "Ataque" to 64, "Defensa" to 58)
        ),
        PokemonData(
            id = 6,
            name = "Charizard",
            type = "Fuego/Volador",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6.png",
            description = "Escupe fuego lo suficientemente caliente como para derretir rocas. Es conocido por causar incendios forestales involuntariamente.",
            height = 1.7,
            weight = 90.5,
            stats = mapOf("HP" to 78, "Ataque" to 84, "Defensa" to 78, "Ataque Especial" to 109, "Defensa Especial" to 85, "Velocidad" to 100)
        ),
        PokemonData(
            id = 7,
            name = "Squirtle",
            type = "Agua",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/7.png",
            description = "Cuando retrae su largo cuello en el caparazón, dispara agua a una presión increíble.",
            height = 0.5,
            weight = 9.0,
            stats = mapOf("HP" to 44, "Ataque" to 48, "Defensa" to 65, "Ataque Especial" to 50, "Defensa Especial" to 64, "Velocidad" to 43)
        ),
        PokemonData(
            id = 8,
            name = "Wartortle",
            type = "Agua",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/8.png",
            description = "Se lo considera un Pokémon longevo. Su cola cubierta de pelo se oscurece con la edad.",
            height = 1.0,
            weight = 22.5,
            stats = mapOf("HP" to 59, "Ataque" to 63, "Defensa" to 80, "Ataque Especial" to 65, "Defensa Especial" to 80, "Velocidad" to 58)
        ),
        PokemonData(
            id = 9,
            name = "Blastoise",
            type = "Agua",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/9.png",
            description = "Los chorros de agua que dispara desde los cañones de su caparazón pueden perforar el acero.",
            height = 1.6,
            weight = 85.5,
            stats = mapOf("HP" to 79, "Ataque" to 83, "Defensa" to 100, "Ataque Especial" to 85, "Defensa Especial" to 105, "Velocidad" to 78)
        ),
        PokemonData(
            id = 25,
            name = "Pikachu",
            type = "Eléctrico",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png",
            description = "Tiene pequeñas bolsas eléctricas en sus mejillas. Si se siente amenazado, libera una descarga eléctrica.",
            height = 0.4,
            weight = 6.0,
            stats = mapOf("HP" to 35, "Ataque" to 55, "Defensa" to 40, "Ataque Especial" to 50, "Defensa Especial" to 50, "Velocidad" to 90)
        ),
        PokemonData(
            id = 26,
            name = "Raichu",
            type = "Eléctrico",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/26.png",
            description = "Cuando está bien alimentado, su cuerpo brilla. Se dice que su energía eléctrica puede causar tormentas.",
            height = 0.8,
            weight = 30.0,
            stats = mapOf("HP" to 60, "Ataque" to 90, "Defensa" to 55, "Ataque Especial" to 90, "Defensa Especial" to 80, "Velocidad" to 110)
        ),
        PokemonData(
            id = 133,
            name = "Eevee",
            type = "Normal",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/133.png",
            description = "Eevee tiene una capacidad de evolución única. Puede evolucionar en varias formas dependiendo de las condiciones.",
            height = 0.3,
            weight = 6.5,
            stats = mapOf("HP" to 55, "Ataque" to 55, "Defensa" to 50, "Ataque Especial" to 45, "Defensa Especial" to 50, "Velocidad" to 55)
        ),
        PokemonData(
            id = 134,
            name = "Vaporeon",
            type = "Agua",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/134.png",
            description = "Vaporeon tiene la capacidad de controlar el agua. Puede evaporarse y convertirse en vapor.",
            height = 1.0,
            weight = 29.0,
            stats = mapOf("HP" to 130, "Ataque" to 65, "Defensa" to 60, "Ataque Especial" to 110, "Defensa Especial" to 95, "Velocidad" to 65)
        ),
        PokemonData(
            id = 135,
            name = "Jolteon",
            type = "Eléctrico",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/135.png",
            description = "Jolteon puede liberar electricidad estática. Su pelaje es capaz de acumular electricidad.",
            height = 0.8,
            weight = 24.5,
            stats = mapOf("HP" to 65, "Ataque" to 65, "Defensa" to 60, "Ataque Especial" to 110, "Defensa Especial" to 60, "Velocidad" to 130)
        ),
        PokemonData(
            id = 136,
            name = "Flareon",
            type = "Fuego",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/136.png",
            description = "Flareon tiene un pelaje denso que puede liberar calor. Su cuerpo puede alcanzar temperaturas extremadamente altas.",
            height = 0.9,
            weight = 25.0,
            stats = mapOf("HP" to 65, "Ataque" to 130, "Defensa" to 60, "Ataque Especial" to 95, "Defensa Especial" to 110, "Velocidad" to 65)
        ),
        PokemonData(
            id = 143,
            name = "Snorlax",
            type = "Normal",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/143.png",
            description = "Snorlax es conocido por su gran tamaño y su capacidad para dormir en cualquier lugar. Se despierta solo para comer.",
            height = 2.1,
            weight = 460.0,
            stats = mapOf("HP" to 160, "Ataque" to 110, "Defensa" to 65, "Ataque Especial" to 65, "Defensa Especial" to 110, "Velocidad" to 30)
        ),
        PokemonData(
            id = 151,
            name = "Mew",
            type = "Psíquico",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/151.png",
            description = "Mew es un Pokémon legendario que se dice que tiene el ADN de todos los Pokémon. Es capaz de aprender cualquier movimiento.",
            height = 0.4,
            weight = 4.0,
            stats = mapOf("HP" to 100, "Ataque" to 100, "Defensa" to 100, "Ataque Especial" to 100, "Defensa Especial" to 100, "Velocidad" to 100)
        ),
        PokemonData(
            id = 152,
            name = "Chikorita",
            type = "Planta",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/152.png",
            description = "Chikorita es un Pokémon de tipo planta que tiene una hoja en la cabeza. Es conocido por su naturaleza amistosa.",
            height = 0.9,
            weight = 6.4,
            stats = mapOf("HP" to 45, "Ataque" to 49, "Defensa" to 65, "Ataque Especial" to 49, "Defensa Especial" to 65, "Velocidad" to 45)
        ),
    )

    override suspend fun getAllPokemon(): List<PokemonData> = pokemonList


    override suspend fun getPokemonById(id: Int): PokemonData? = pokemonList.find { it.id == id }

    override suspend fun searchPokemon(query: String): List<PokemonData> {
        return pokemonList.filter {
            it.name.contains(query, ignoreCase = true) ||
                    it.type.contains(query, ignoreCase = true)
        }
    }


}