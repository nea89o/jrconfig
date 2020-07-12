package com.romangraef.jrconfig

interface ConfigProvider {
    fun provideData(point: String): String?
}