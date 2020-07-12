package com.romangraef.jrconfig;

public class Main {

    public static ConfigVariable<String> token = Config.getString("token");
    public static ConfigVariable<String> token2 = Config.getString("token2").defaultValue("token");

    public static void main(String[] args) {
        Config.use(FilePropertiesProvider.create("config.properties"));
        System.out.println(token.get());
        System.out.println(token2.get());
    }
}
