package com.romangraef.jrconfig.variables

import com.romangraef.jrconfig.ConfigProvider
import com.romangraef.jrconfig.TransformerConfigVariable

class StringVariable(provider: ConfigProvider, point: String) :
    TransformerConfigVariable<String>(provider, point) {
    override fun transform(value: String): String {
        return value
    }

    override fun serialize(data: String): String {
        return data
    }
}