## romangraefs Java Config library.
I intend to mostly use this library for personal projects, so documentation might be lacking in parts. If you have questions, feel free to hit me up on [Twitter](https://twitter.com/romangraef89). That being said:

## Usage
Java: 
```java
public class ConfigurableClass1 {
    public ConfigVariable<String> someConfigProp = Config.getString("someProp"); // Define a config property
        
    public ConfigurableClass1() {
        Config.use(FilePropertiesProvider.create("config.properties")); // Define a config source.
    }

    public void someMethod() {
        System.out.println(someConfigProp.get()); // Load data from the config.
        someConfigProp.set("New value");
    }
}
```

Kotlin: 
```kotlin 
val someConfigProp = Config.get<String>("someProp")
var someOtherOption by Config.get<String>("someOtherOption")


fun main() {
    Config.use(FilePropertiesProvider.create("config.properties"))
    println(someConfigProp.get())
    println(someOtherOption)
    someOtherOption = "lul"
}
```

### Api breakdown

The API is split up into 3 parts:

 - Config variables.
 
These are what you interact with, for most of your code. You obtain an instance by either calling 
`Config.get(someClazz, "propName")` or you can use `Config.get<SomeClass>("propName")` in Kotlin. 
Some Common Types like `String` have shortcut methods like `Config.getString("propName")`.

 - Variable Transformers.
 
These parse / serialize a string value obtained from a config source. There are some default implementations 
for some types (`String` and `Integer` as of right now), but you can manually create transformers 
by extending `TransformerConfigVariable<T>`.

 - Config providers.
 
You usually install one config provider at the very start of your main using `Config.use(provider)`. Currently there 
is only one Provider, which is the `FilePropertiesProvider` which directly utilizes standard java properties. If
you want to create your own provider, you can implement `ConfigProvider`

## Installation

Gradle via [Jitpack](https://jitpack.io/): 
```groovy
repositories {
    maven { url "https://jitpack.io" }
}

dependencies {
    implementation("com.github.romangraef", "jrconfig", "v0.4")
}
```
The version can be either a git shortref, or a [tag](https://github.com/romangraef/jrconfig/tags).

Alternatively, a uberjar/fatjar/ shadow/shadedjar can be obtained from the [releases](https://github.com/romangraef/jrconfig/releases).
