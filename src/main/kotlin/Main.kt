package org.example

import com.sun.net.httpserver.HttpServer
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
    val route = Router(server)
    route.get("/") { exchange ->
        val response = "Home"
        exchange.sendResponseHeaders(200, response.length.toLong())
        exchange.responseBody.use { it.write(response.toByteArray()) }
    }
    route.get("/hello") { exchange ->
        val response = "Hello, World!"
        exchange.sendResponseHeaders(200, response.length.toLong())
        exchange.responseBody.use { it.write(response.toByteArray()) }
    }
    server.executor = null
    server.start()
}