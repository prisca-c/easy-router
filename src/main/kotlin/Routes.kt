package org.example.server

import org.example.response.StatusResponse
import org.example.routing.Router

class Routes(private val router: Router) {
    init {
        init()
    }

    private fun init() {
        router.get("/hello") { exchange, _ ->
            StatusResponse().ok(exchange, "Hello, World!")
        }

        router.get("/hello/:name") { exchange, params ->
            val name = params["name"] ?: "World"
            StatusResponse().ok(exchange, "Hello, $name!")
        }
    }
}