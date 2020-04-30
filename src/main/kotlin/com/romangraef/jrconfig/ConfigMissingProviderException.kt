package com.romangraef.jrconfig

class ConfigMissingProviderException(val clazz: Class<*>) :
    RuntimeException("You are missing a config provider for the type ${clazz.canonicalName}")
