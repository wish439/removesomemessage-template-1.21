package com.wishtoday.rsm.Config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.wishtoday.rsm.RemoveSomeMessage;
import com.wishtoday.rsm.Unit.ModFileUnits;
import lombok.Getter;

import java.io.*;

public class ResConfig {
    private static final File configFile = new File(ModFileUnits.getFile() + "\\" + ModFileUnits.CONFIG_FILE_NAME);
    @Getter
    private static Configs configs;

    static {
        try {
            loadConfigs();
        } catch (IOException e) {
            configs = new Configs();
            saveConfigs();
            RemoveSomeMessage.LOGGER.warn("Load configs failed");
        }
    }
    public static void saveConfigs() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(configFile))) {
            gson.toJson(configs, bw);
        } catch (IOException e) {
            RemoveSomeMessage.LOGGER.warn("Save configs failed");
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
            RemoveSomeMessage.LOGGER.warn("ParseJson failed");
        }
    }

    public static void setConfigs(Configs configs) {
        ResConfig.configs = configs;
        ResConfig.saveConfigs();
    }
}
