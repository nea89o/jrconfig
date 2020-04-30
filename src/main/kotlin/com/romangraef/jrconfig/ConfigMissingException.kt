package com.romangraef.jrconfig

class ConfigMissingException(val point: String) : RuntimeException("The point $point is missing from your config.") {

}
