package com.romangraef.jrconfig

interface ConfigProvider {
    fun provideData(point: String): String?
    fun setData(point: String, data: String)
}