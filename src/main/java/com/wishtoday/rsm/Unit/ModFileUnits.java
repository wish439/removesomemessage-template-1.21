package com.wishtoday.rsm.Unit;

import net.minecraft.client.MinecraftClient;

import java.io.File;

public abstract class ModFileUnits {
    public static final String CONFIG_FILE_NAME = "rsm.json";
    public static File getFile() {
        return new File(MinecraftClient.getInstance().runDirectory, "config");
    }
}
