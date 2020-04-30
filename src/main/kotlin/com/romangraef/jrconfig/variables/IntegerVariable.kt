package com.romangraef.jrconfig.variables

import com.romangraef.jrconfig.ConfigProvider
import com.romangraef.jrconfig.TransformerConfigVariable

class IntegerVariable(provider: ConfigProvider, point: String) :
    TransformerConfigVariable<Int>(provider, point) {
    override fun transform(value: String): Int {
        return value.toInt()
    }

    override fun serialize(data: Int): String {
        return data.toString()
    }
}