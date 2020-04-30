package com.romangraef.jrconfig

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class FilePropertiesProvider(private val file: File) : ConfigProvider {
    private var properties: Properties = Properties()

    init {
        try {
            properties.load(FileInputStream(file))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    companion object {
        @JvmStatic
        fun create(fileName: String): ConfigProvider {
            return FilePropertiesProvider(File(fileName))
        }
    }

    override fun provideData(point: String): String? {
        return properties.getProperty(point)
    }

    override fun setData(point: String, data: String) {
        properties.setProperty(point, data)
        save()
    }

    private fun save() {
        try {
            properties.store(FileOutputStream(file), "application config")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


}