package com.romangraef.jrconfig.variables

import com.romangraef.jrconfig.ConfigSaveLoadProvider
import com.romangraef.jrconfig.TransformerConfigVariable

class StringVariable(provider: ConfigSaveLoadProvider, point: String) :
    TransformerConfigVariable<String>(provider, point) {
    override fun transform(value: String): String {
        return value
    }

    override fun serialize(data: String): String {
        return data
    }
}