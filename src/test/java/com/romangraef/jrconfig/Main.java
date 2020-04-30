package com.romangraef.jrconfig;

public class Main {

    public static ConfigVariable<String> token = Config.getString("token");

    public static void main(String[] args) {
        Config.use(FilePropertiesProvider.create("config.properties"));
        System.out.println(token.get());
    }
}
