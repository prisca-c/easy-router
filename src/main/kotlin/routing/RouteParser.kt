package org.example.routing

class RouteParser(route: String, path: String) {
    init {
        if (matchRoute(route, path)) {
            println("Route matched")
        } else {
            println("Route did not match")
        }
    }

    private fun parse(route: String): List<String> {
        return route.split("/")
    }

    private fun matchRoute(route: String, path: String): Boolean {
        val routeParts = parse(route)
        val pathParts = parse(path)

        if (routeParts.size != pathParts.size) {
            return false
        }

        for (i in routeParts.indices) {
            if (routeParts[i] != pathParts[i] && !routeParts[i].startsWith(":")) {
                return false
            }
        }

        return true
    }

}