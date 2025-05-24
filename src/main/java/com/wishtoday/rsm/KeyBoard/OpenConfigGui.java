package com.wishtoday.rsm.KeyBoard;

import com.wishtoday.rsm.GUI.ModMenuGui;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class OpenConfigGui {
    public static KeyBinding configGui;
    public static void ListeningKey() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
           while (configGui.wasPressed()) {
               client.setScreen(new ModMenuGui(client.currentScreen));
           }
        });
    }
    public static void registerKeyBindings() {
        configGui = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "rsm.key.ocg"
                        , InputUtil.Type.KEYSYM
                        , GLFW.GLFW_KEY_V
                        , "category.rsm"
                )
        );
    }
}
