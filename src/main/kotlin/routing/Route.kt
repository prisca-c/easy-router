package org.example.routing

import kotlinx.serialization.Serializable

@Serializable
data class Route(val route: String, val method: String, val handler: String)