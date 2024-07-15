package org.example

import com.sun.net.httpserver.HttpServer
import org.example.response.StatusResponse
import org.example.routing.Router
import java.net.InetSocketAddress
import kotlin.system.exitProcess

fun main() {
    runHttpServer()
    println("Server started at http://localhost:8080")
    println("Press any key to exit")
    readlnOrNull()
    println("Server stopped")
    exitProcess(0)
}

fun runHttpServer() {
    val server = HttpServer.create(InetSocketAddress(8080), 0)
    val router = Router(server)
    router.get("/hello") { exchange, _ ->
        StatusResponse().ok(exchange, "Hello, World!")
    }

    router.get("/hello/:name") { exchange, params ->
        val name = params["name"] ?: "World"
        StatusResponse().ok(exchange, "Hello, $name!")
    }

    router.handleRequests()
    server.executor = null
    server.start()
}