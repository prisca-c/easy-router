package org.example.server.server

import com.sun.net.httpserver.HttpServer
import org.example.routing.Router
import java.net.InetSocketAddress
import kotlin.system.exitProcess

class Server {
    private val router: Router

    init {
        val server = HttpServer.create(InetSocketAddress(8080), 0)
        router = Router(server)
        router.handleRequests()
        server.executor = null
        server.start()
    }

    fun start() {
        println("Server started at http://localhost:8080")
        println("Press any key to exit")
        readlnOrNull()
        println("Server stopped")
        exitProcess(0)
    }

    fun getRouter(): Router {
        return router
    }
}