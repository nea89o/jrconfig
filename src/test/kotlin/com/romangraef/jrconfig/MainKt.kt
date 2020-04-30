package com.romangraef.jrconfig

val token = Config.get<String>("token")
var someOtherOption by Config.get<String>("someOtherOption")

fun main() {
    Config.use(FilePropertiesProvider.create("config.properties"))
    println(token.get())
    println(someOtherOption)
    someOtherOption = "lul"
}