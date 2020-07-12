package com.romangraef.jrconfig

class DefaultConfigVariable<T>(private val internal: ConfigVariable<T>, private val default: T) : ConfigVariable<T> {
    override fun get(): T = try {
        internal.get()
    } catch (e: Exception) {
        default
    }

    override fun set(value: T): Unit = internal.set(value)
    override fun defaultValue(t: T?): ConfigVariable<T> = internal.defaultValue(t)
}