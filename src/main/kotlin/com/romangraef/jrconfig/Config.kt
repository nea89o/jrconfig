package com.romangraef.jrconfig

import com.romangraef.jrconfig.variables.*

typealias ConfigVariableProvider<T> = (ConfigSaveLoadProvider, String) -> ConfigVariable<T>

object Config {
    private var configProvider = mutableListOf<ConfigProvider>()
    private val map: MutableMap<Class<*>, ConfigVariableProvider<*>> = mutableMapOf()
    private val proxyProvider: ConfigSaveLoadProvider = object : ConfigSaveLoadProvider {

        override fun provideData(point: String): String? =
            configProvider.map { it.provideData(point) }.firstOrNull()


        override fun setData(point: String, data: String): Boolean =
            configProvider.asSequence().mapNotNull { it as? ConfigSaveLoadProvider }.map { it.setData(point, data) }
                .firstOrNull() ?: false

    }

    init {
        map[Double::class.java] = ::DoubleVariable
        map[Float::class.java] = ::FloatVariable
        map[Int::class.java] = ::IntegerVariable
        map[Long::class.java] = ::LongVariable
        map[String::class.java] = ::StringVariable
    }


    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    fun <T> get(clazz: Class<out T>, point: String): ConfigVariable<T> {
        return (map[clazz] as? ConfigVariableProvider<T>)?.invoke(proxyProvider, point)
            ?: throw ConfigMissingProviderException(clazz)
    }

    @JvmStatic
    fun <T : Enum<T>> getEnum(enumClass: Class<T>, point: String): ConfigVariable<T> {
        return EnumVariable(enumClass, proxyProvider, point)
    }

    inline fun <reified T> get(point: String) = get(T::class.java, point)
    inline fun <reified T : Enum<T>> getEnum(point: String) = getEnum(T::class.java, point)

    @JvmStatic
    fun <T> registerConfigVariableProvider(clazz: Class<T>, provider: ConfigVariableProvider<T>) {
        map[clazz] = provider
    }

    inline fun <reified T> registerConfigVariableProvider(noinline provider: ConfigVariableProvider<T>) =
        registerConfigVariableProvider(T::class.java, provider)


    @JvmStatic
    fun use(provider: ConfigProvider) {
        configProvider.add(provider)
    }

    @JvmStatic
    fun useFirst(provider: ConfigProvider) {
        configProvider.add(0, provider)
    }

    @JvmStatic
    fun getString(point: String): ConfigVariable<String> {
        return get(String::class.java, point)
    }

}