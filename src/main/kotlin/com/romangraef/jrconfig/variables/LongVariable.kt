package com.romangraef.jrconfig.variables

import com.romangraef.jrconfig.ConfigSaveLoadProvider
import com.romangraef.jrconfig.TransformerConfigVariable

class LongVariable(provider: ConfigSaveLoadProvider, point: String) :
    TransformerConfigVariable<Long>(provider, point) {
    override fun transform(value: String): Long {
        return value.toLong()
    }

    override fun serialize(data: Long): String {
        return data.toString()
    }
}