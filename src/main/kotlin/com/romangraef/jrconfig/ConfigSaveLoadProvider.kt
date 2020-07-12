package com.romangraef.jrconfig

interface ConfigSaveLoadProvider : ConfigProvider {
    fun setData(point: String, data: String): Boolean
}