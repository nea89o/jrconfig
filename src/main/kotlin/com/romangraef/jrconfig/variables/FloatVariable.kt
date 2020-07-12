package com.romangraef.jrconfig.variables

import com.romangraef.jrconfig.ConfigSaveLoadProvider
import com.romangraef.jrconfig.TransformerConfigVariable

class FloatVariable(provider: ConfigSaveLoadProvider, point: String) :
    TransformerConfigVariable<Float>(provider, point) {
    override fun transform(value: String): Float {
        return value.toFloat()
    }

    override fun serialize(data: Float): String {
        return data.toString()
    }
}