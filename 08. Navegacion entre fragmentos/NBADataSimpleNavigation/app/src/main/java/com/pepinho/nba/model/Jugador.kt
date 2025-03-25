package com.pepinho.nba.model

/**
 *     {
 *         "id": 19,
 *         "first_name": "Stephen",
 *         "last_name": "Curry",
 *         "position": "G",
 *         "height": "6-2",
 *         "weight": "185",
 *         "jersey_number": "30",
 *         "college": "Davidson",
 *         "country": "USA",
 *         "draft_year": 2009,
 *         "draft_round": 1,
 *         "draft_number": 7,
 *         "team": {
 *             "id": 10,
 *             "conference": "West",
 *             "division": "Pacific",
 *             "city": "Golden State",
 *             "name": "Warriors",
 *             "full_name": "Golden State Warriors",
 *             "abbreviation": "GSW"
 *         }
 */
data class Jugador(
    val idJugador: Int,
    val nombre: String,
    val apellidos: String,
    val posicion: String,
    val altura: String,
    val peso: Int,
    val numero: Short,
    val universidad: String,
    val pais: String,
    val anoDraft: Short,
    val rondaDraft: Short,
    val numeroDraft: Short,
    val equipo: Equipo
)