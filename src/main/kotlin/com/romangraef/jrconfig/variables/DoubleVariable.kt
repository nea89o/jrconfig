package com.romangraef.jrconfig.variables

import com.romangraef.jrconfig.ConfigSaveLoadProvider
import com.romangraef.jrconfig.TransformerConfigVariable

class DoubleVariable(provider: ConfigSaveLoadProvider, point: String) :
    TransformerConfigVariable<Double>(provider, point) {
    override fun transform(value: String): Double {
        return value.toDouble()
    }

    override fun serialize(data: Double): String {
        return data.toString()
    }
}