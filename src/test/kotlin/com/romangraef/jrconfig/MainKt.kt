package com.romangraef.jrconfig

val token = Config.get<String>("token")

fun main() {
    Config.use(FilePropertiesProvider.create("config.properties"))
    println(token.get())
}