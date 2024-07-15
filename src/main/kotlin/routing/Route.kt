package org.example.routing

import com.sun.net.httpserver.HttpExchange
import org.example.response.StatusResponse


open class Route: StatusResponse() {
    private fun parsePath(path: String): String {
        return if (path.endsWith("/")) path.dropLast(1) else path
    }

    protected fun matchRoute(
        routes: MutableList<Triple<String, (HttpExchange, Map<String, String>) -> Unit, String>>,
        exchange: HttpExchange
    ): Triple<String, (HttpExchange, Map<String, String>) -> Unit, String>? {
        val requestPath = parsePath(exchange.requestURI.path)
        val method = exchange.requestMethod
        return routes.find { (pattern, _, requestMethod) ->
            val regexPattern = pattern.replace(Regex(":\\w+"), "([^/]+)").toRegex()
            val match = requestPath.matches(regexPattern) && method.equals(requestMethod, ignoreCase = true)
            match
        }
    }

    protected fun String.extractParams(pattern: String): Map<String, String>? {
        val patternRegex = pattern.replace(Regex(":\\w+"), "(\\\\w+)").toRegex()
        val matchResult = patternRegex.matchEntire(this) ?: return null
        val paramNames = Regex(":\\w+").findAll(pattern).map { it.value.trim(':') }.toList()
        val paramValues = matchResult.groupValues.drop(1) // First element is the entire match, drop it
        return paramNames.zip(paramValues).toMap()
    }
}