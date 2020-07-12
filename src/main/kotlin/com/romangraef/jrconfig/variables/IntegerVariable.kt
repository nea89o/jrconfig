package com.romangraef.jrconfig.variables

import com.romangraef.jrconfig.ConfigSaveLoadProvider
import com.romangraef.jrconfig.TransformerConfigVariable

class IntegerVariable(provider: ConfigSaveLoadProvider, point: String) :
    TransformerConfigVariable<Int>(provider, point) {
    override fun transform(value: String): Int {
        return value.toInt()
    }

    override fun serialize(data: Int): String {
        return data.toString()
    }
}