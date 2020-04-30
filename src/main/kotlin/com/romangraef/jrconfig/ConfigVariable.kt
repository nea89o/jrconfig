package com.romangraef.jrconfig

interface ConfigVariable<T> {
    fun get(): T
    fun set(value: T)
}