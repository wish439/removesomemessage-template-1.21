package com.wishtoday.rsm.Config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.wishtoday.rsm.Unit.ModFileUnits;

import java.io.*;

public class ResConfig {
    private static final File configFile = new File(ModFileUnits.getFile() + "\\" + ModFileUnits.CONFIG_FILE_NAME);
    private static Configs configs;

    static {
        try {
            loadConfigs();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void saveConfigs() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(configFile))) {
            gson.toJson(configs, bw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadConfigs() throws IOException {
        Gson gson = new Gson();
        if (!configFile.exists()) {
            Boolean b = configFile.isFile() ? configFile.createNewFile() : null;
            configs = new Configs();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(configFile))) {
            configs = gson.fromJson(br, Configs.class);
        } catch (JsonParseException e) {
            configs = new Configs();
            saveConfigs();
        }
    }

    public static Configs getConfigs() {
        return configs;
    }

    public static void setConfigs(Configs configs) {
        ResConfig.configs = configs;
        ResConfig.saveConfigs();
    }
}
