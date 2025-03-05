package com.example

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.response.*

class IGDBClient(val clientId: String, val clientSecret: String) {
    //Ejemplo funcionalidad de cliente
    fun getGames(): List<String> {
        return listOf("Game 1", "Game 2", "Game 3") // Ejemplo de respuesta de juegos
    }
}

fun Application.configureRouting(igdbClient: IGDBClient) {
    routing {

        // Rutas para videojuegos usando el cliente
        gameRoutes(igdbClient)

    }
}

// Función personalizada para manejar rutas de videojuegos
fun Routing.gameRoutes(client: IGDBClient) {
    route("/games") {
        get {
            // Obtenemos la lista de juegos desde IGDBClient
            val games = client.getGames()
            call.respond(games)
        }
    }
}