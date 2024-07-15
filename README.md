# Easy Router

Welcome to the Easy Router project. It aims to provide a simple and easy-to-use Kotlin router for your application.

## Installation

```
NOT YET AVAILABLE SINCE THE PROJECT IS STILL IN BEGINNING STAGES
```

## Usage

To create a router, you need to create an instance of the `Router` class and pass an instance of `HttpServer` to it. Then, you can add routes to the router using the `get`, `post`, `put`, `delete`, `patch`, and `options` methods.

```kotlin
val server = HttpServer.create(InetSocketAddress(8080), 0)
val router = Router(server)

router.get("/hello") { exchange ->
    StatusResponse().ok(exchange, "Hello, world!")
} // /hello -> Hello, world!

router.get("/hello/:name") { exchange, params ->
    val name = params["name"]
    StatusResponse().ok(exchange, "Hello, $name!")
} // /hello/john -> Hello, john!
```
