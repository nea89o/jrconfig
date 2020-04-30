package com.romangraef.jrconfig

import kotlin.reflect.KProperty

interface ConfigVariable<T> {
    fun get(): T
    fun set(value: T)
    operator fun getValue(thisRef: Any?, property: KProperty<*>) = get()
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) = set(value)
}