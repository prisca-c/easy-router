package org.example.routing

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpServer

class Router(private val server: HttpServer): Route() {
    private val routesWithParams = mutableListOf<Triple<String, (HttpExchange, Map<String, String>) -> Unit, String>>()

    fun get(pattern: String, handler: (HttpExchange, Map<String, String>) -> Unit) {
        routesWithParams.add(Triple(pattern, handler, "GET"))
    }

    fun post(pattern: String, handler: (HttpExchange, Map<String, String>) -> Unit) {
        routesWithParams.add(Triple(pattern, handler, "POST"))
    }

    fun put(pattern: String, handler: (HttpExchange, Map<String, String>) -> Unit) {
        routesWithParams.add(Triple(pattern, handler, "PUT"))
    }

    fun delete(pattern: String, handler: (HttpExchange, Map<String, String>) -> Unit) {
        routesWithParams.add(Triple(pattern, handler, "DELETE"))
    }

    fun handleRequests() {
        server.createContext("/") { exchange ->
            val requestPath = exchange.requestURI.path
            val matchedRoute = matchRoute(routesWithParams, exchange)

            if (matchedRoute != null) {
                val (pattern, handler, _) = matchedRoute
                val params = requestPath.extractParams(pattern) ?: emptyMap()
                handler(exchange, params)
            } else {
                notFound(exchange)
            }
        }
    }
}