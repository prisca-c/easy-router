package org.example.routing

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpServer
import org.example.response.StatusResponse

class Router(private val server: HttpServer): StatusResponse() {
    companion object {
        private val routes = mutableMapOf<String, MutableMap<String, String>>()
    }

    private fun addRoute(route: String, method: String, handler: String) {
        if (routes.containsKey(route)) {
            if (routes[route]?.containsKey(method) == true) {
                throw Exception("Route already exists")
            } else {
                routes[route]?.set(method, handler)
            }
        } else {
            routes[route] = mutableMapOf(method to handler)
        }
    }

    fun get(route: String, handler: (exchange: HttpExchange) -> Unit) {
        addRoute(route, "GET", handler.toString())

        server.createContext(route) { exchange ->
            exchange.requestMethod.takeIf { it == "GET" } ?: return@createContext methodNotAllowed(exchange)
            handler(exchange)
        }
    }

    fun post(route: String, handler: (exchange: HttpExchange) -> Unit) {
        addRoute(route, "POST", handler.toString())

        server.createContext(route) { exchange ->
            exchange.requestMethod.takeIf { it == "POST" } ?: return@createContext methodNotAllowed(exchange)
            handler(exchange)
        }
    }

    fun put(route: String, handler: (exchange: HttpExchange) -> Unit) {
        addRoute(route, "PUT", handler.toString())

        server.createContext(route) { exchange ->
            exchange.requestMethod.takeIf { it == "PUT" } ?: return@createContext methodNotAllowed(exchange)
            handler(exchange)
        }
    }

    fun delete(route: String, handler: (exchange: HttpExchange) -> Unit) {
        addRoute(route, "DELETE", handler.toString())

        server.createContext(route) { exchange ->
            exchange.requestMethod.takeIf { it == "DELETE" } ?: return@createContext methodNotAllowed(exchange)
            handler(exchange)
        }
    }
}