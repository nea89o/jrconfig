package com.romangraef.jrconfig

import java.util.function.Function

abstract class TransformerConfigVariable<T>(private val provider: ConfigSaveLoadProvider, private val point: String) :
    ConfigVariable<T> {
    protected abstract fun transform(value: String): T
    protected abstract fun serialize(data: T): String

    override fun get(): T {
        return transform(provider.provideData(point) ?: throw ConfigMissingException(point))
    }


    override fun set(value: T) {
        provider.setData(point, serialize(value))
    }

    override fun defaultValue(t: T?): ConfigVariable<T> = if (t == null) {
        this
    } else {
        DefaultConfigVariable(this, t)
    }

    companion object {
        /**
         * Quick and dirty. Please consider using a subclass.
         */
        fun <T> getVariable(
            provider: ConfigSaveLoadProvider,
            point: String,
            transform: Function<String, T>,
            serialize: Function<T, String>
        ): TransformerConfigVariable<T> {
            return object : TransformerConfigVariable<T>(provider, point) {
                override fun transform(value: String): T {
                    return transform.apply(value)
                }

                override fun serialize(data: T): String {
                    return serialize.apply(data)
                }
            }
        }
    }

}