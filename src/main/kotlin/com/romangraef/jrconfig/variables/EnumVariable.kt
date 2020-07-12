package com.romangraef.jrconfig.variables

import com.romangraef.jrconfig.ConfigSaveLoadProvider
import com.romangraef.jrconfig.TransformerConfigVariable

class EnumVariable<T : Enum<T>>(val enumClass: Class<T>, provider: ConfigSaveLoadProvider, point: String) :
    TransformerConfigVariable<T>(provider, point) {
    override fun transform(value: String): T {
        return java.lang.Enum.valueOf(enumClass, value)
    }

    override fun serialize(data: T): String {
        return data.name
    }
}