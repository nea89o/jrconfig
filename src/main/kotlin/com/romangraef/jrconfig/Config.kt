package com.romangraef.jrconfig

import com.romangraef.jrconfig.variables.IntegerVariable
import com.romangraef.jrconfig.variables.StringVariable

typealias ConfigVariableProvider<T> = (ConfigProvider, String) -> ConfigVariable<T>

object Config {
    private var configProvider: ConfigProvider? = null
    private val map: MutableMap<Class<*>, ConfigVariableProvider<*>> = mutableMapOf()
    private val proxyProvider: ConfigProvider = object : ConfigProvider {

        override fun provideData(point: String): String? =
            configProvider?.provideData(point)


        override fun setData(point: String, data: String) =
            configProvider?.setData(point, data) ?: Unit
    }

    init {
        map[Int::class.java] = ::IntegerVariable
        map[String::class.java] = ::StringVariable
    }


    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    fun <T> get(clazz: Class<out T>, point: String): ConfigVariable<T> {
        return (map[clazz] as? ConfigVariableProvider<T>)?.invoke(proxyProvider, point)
            ?: throw ConfigMissingProviderException(clazz)
    }

    inline fun <reified T> get(point: String) = get(T::class.java, point)

    @JvmStatic
    fun <T> registerConfigVariableProvider(clazz: Class<T>, provider: ConfigVariableProvider<T>) {
        map[clazz] = provider
    }

    inline fun <reified T> registerConfigVariableProvider(noinline provider: ConfigVariableProvider<T>) =
        registerConfigVariableProvider(T::class.java, provider)


    @JvmStatic
    fun use(provider: ConfigProvider?) {
        configProvider = provider
    }

    @JvmStatic
    fun getString(point: String): ConfigVariable<String> {
        return get(String::class.java, point)
    }

}