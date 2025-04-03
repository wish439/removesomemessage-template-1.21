package com.wishtoday.rsm.GUI;

import com.wishtoday.rsm.Config.ResConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;

import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

import java.util.*;
import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public class ModMenuGui extends Screen {
    private TextFieldWidget fieldWidget;
    //消除一些if
    private static final Map<String, Supplier<Boolean>> TEXTSTATEMAP = Map.of(
            "isRemoveMusicMessages",
            () -> ResConfig.getConfigs().isRemoveMusicMessages(),
            "isRemoveJoinGameMessages",
            () -> ResConfig.getConfigs().isRemoveJoinGameMessages(),
            "isRemoveQuitGameMessages",
            () -> ResConfig.getConfigs().isRemoveQuitGameMessages(),
            "isRemoveAdvMessages",
            () -> ResConfig.getConfigs().isRemoveAdvMessages()
    );

    private final Screen parentScreen;

    public ModMenuGui(Screen parentScreen) {
        super(Text.literal("配置界面"));
        this.parentScreen = parentScreen;
    }


    // 根据当前状态获取按钮文本
    private static Text getTextBasedOnState(String state) {
        return Optional
                .ofNullable(TEXTSTATEMAP.get(state))
                .map(s -> Text.of(s.get() ? "开" : "关"))
                .orElse(Text.of(""));
    }

    @Override
    protected void init() {
        fieldWidget = new TextFieldWidget(MinecraftClient.getInstance().textRenderer, width / 2 - 120, 50, 150, 20, Text.of("需要屏蔽的文本"));
        fieldWidget.setText(String.join(",",ResConfig.getConfigs().getRemoveMessages()));
        ButtonWidget removeMusicButton = RegisterButton(width / 2 - 120, 60, 150, 20, "isRemoveMusicMessages", "是否屏蔽音乐消息");
        ButtonWidget removeJoinButton = RegisterButton(width / 2 - 120, 40, 150, 20, "isRemoveJoinGameMessages", "是否屏蔽进入游戏消息");
        ButtonWidget removeQuitButton = RegisterButton(width / 2 - 120, 20, 150, 20, "isRemoveQuitGameMessages", "是否屏蔽退出游戏消息");
        ButtonWidget removeAdvButton = RegisterButton(width / 2 - 120, 0, 150, 20, "isRemoveAdvMessages", "是否屏蔽进度消息");


        ButtonWidget modMenuButton = ButtonWidget.builder(Text.of("退出"), button -> this.close())
                .dimensions(width / 2 - 70, 200, 150, 20)
                .tooltip(Tooltip.of(Text.of("返回上个界面")))
                .build();
        fieldWidget.setChangedListener(text -> ResConfig.getConfigs().setRemoveMessages(StringToList(text)));
        addDrawableChild(fieldWidget);
        addDrawableChild(removeAdvButton);
        addDrawableChild(removeMusicButton);
        addDrawableChild(removeJoinButton);
        addDrawableChild(removeQuitButton);
        addDrawableChild(modMenuButton);

    }

    @Override
    public void close() {
        if (this.client != null) {
            this.client.setScreen(this.parentScreen);
        } else {
            MinecraftClient.getInstance().setScreen(null);
        }
    }
    private static ButtonWidget RegisterButton(int x, int y, int width, int height, String bool, String tip) {
        return ButtonWidget.builder(getTextBasedOnState(bool), button -> {
                    // 切换状态
                    switch (bool) {
                        case "isRemoveMusicMessages": {
                            ResConfig.getConfigs().setRemoveMusicMessages(!ResConfig.getConfigs().isRemoveMusicMessages());
                            break;
                        }
                        case "isRemoveJoinGameMessages": {
                            ResConfig.getConfigs().setRemoveJoinGameMessages(!ResConfig.getConfigs().isRemoveJoinGameMessages());
                            break;
                        }
                        case "isRemoveQuitGameMessages": {
                            ResConfig.getConfigs().setRemoveQuitGameMessages(!ResConfig.getConfigs().isRemoveQuitGameMessages());
                            break;
                        }
                        case "isRemoveAdvMessages": {
                            ResConfig.getConfigs().setRemoveAdvMessages(!ResConfig.getConfigs().isRemoveAdvMessages());
                            break;
                        }
                    }


                    // 更新按钮文本
                    button.setMessage(getTextBasedOnState(bool));
                })
                .dimensions(x, y, width, height)
                .tooltip(Tooltip.of(Text.of(tip)))
                .build();
    }
    @Override
    public void resize(MinecraftClient client, int width, int height) {
        String string = fieldWidget.getText();
        this.init(client, width, height);
        fieldWidget.setText(string);
    }

    private static List<String> StringToList(String string) {
        return new ArrayList<>(Arrays.asList(string.split(",")));
    }
}