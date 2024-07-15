package org.example.response

import com.sun.net.httpserver.HttpExchange

open class StatusResponse() {
    fun ok(exchange: HttpExchange, response: String = "ok") {
        exchange.sendResponseHeaders(200, response.length.toLong())
        exchange.responseBody.use { it.write(response.toByteArray()) }
        exchange.close()
    }

    fun created(exchange: HttpExchange, response: String = "created") {
        exchange.sendResponseHeaders(201, response.length.toLong())
        exchange.responseBody.use { it.write(response.toByteArray()) }
        exchange.close()
    }

    fun badRequest(exchange: HttpExchange, response: String = "bad request") {
        exchange.sendResponseHeaders(400, response.length.toLong())
        exchange.responseBody.use { it.write(response.toByteArray()) }
        exchange.close()
    }

    fun unauthorized(exchange: HttpExchange, response: String = "unauthorized") {
        exchange.sendResponseHeaders(401, response.length.toLong())
        exchange.responseBody.use { it.write(response.toByteArray()) }
        exchange.close()
    }

    fun forbidden(exchange: HttpExchange, response: String = "forbidden") {
        exchange.sendResponseHeaders(403, response.length.toLong())
        exchange.responseBody.use { it.write(response.toByteArray()) }
        exchange.close()
    }

    fun notFound(exchange: HttpExchange, response: String = "not found") {
        exchange.sendResponseHeaders(404, response.length.toLong())
        exchange.responseBody.use { it.write(response.toByteArray()) }
        exchange.close()
    }

    fun methodNotAllowed(exchange: HttpExchange, response: String = "method not allowed") {
        exchange.sendResponseHeaders(405, response.length.toLong())
        exchange.responseBody.use { it.write(response.toByteArray()) }
        exchange.close()
    }

    fun internalServerError(exchange: HttpExchange, response: String = "internal server error") {
        exchange.sendResponseHeaders(500, response.length.toLong())
        exchange.responseBody.use { it.write(response.toByteArray()) }
        exchange.close()
    }
}