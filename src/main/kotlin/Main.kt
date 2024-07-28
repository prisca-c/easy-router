package org.example.server

import org.example.server.server.Server

fun main() {
    val server = Server()
    Routes(server.getRouter())
    server.start()
}