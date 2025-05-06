package com.wishtoday.rsm.GUI;

import com.wishtoday.rsm.Config.Configs;
import com.wishtoday.rsm.Config.ResConfig;
import com.wishtoday.rsm.Unit.Config.DefaultConfigEnum;
import com.wishtoday.rsm.Unit.RemoveStatus;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.MultilineText;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;

import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

import java.util.*;
import java.util.List;
import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
public class ModMenuGui extends Screen {
    private TextFieldWidget fieldWidget;
    //private TextFieldWidget PlayerChatFieldWidget;
    private TextFieldWidget MessageAndCommandField;
    TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
    private final Screen parentScreen;

    public ModMenuGui(Screen parentScreen) {
        super(Text.literal("配置界面"));
        this.parentScreen = parentScreen;
    }


    // 根据当前状态获取按钮文本
    private static Text getTextBasedOnState(DefaultConfigEnum state) {
        return Text.translatable(state.getValue() ? "rsm.gui.opentext" : "rsm.gui.closetext");
    }

    private static Text getTextBasedOnState(RemoveStatus status) {
        if (status == null) return Text.translatable("rsm.gui.all");
        return Text.translatable(status.getKey());
    }

    @Override
    protected void init() {
        registerButton();
        registerTextField();
    }

    @Override
    public void close() {
        if (this.client != null) {
            this.client.setScreen(this.parentScreen);
        } else {
            MinecraftClient.getInstance().setScreen(null);
        }
    }

    private static ButtonWidget RegisterButton(int x, int y, int width, int height, DefaultConfigEnum bool, String tipKey) {
        return ButtonWidget.builder(getTextBasedOnState(bool), button -> {
                    bool.setValue(!bool.getValue());
                    // 更新按钮文本
                    button.setMessage(getTextBasedOnState(bool));
                })
                .dimensions(x, y, width, height)
                .tooltip(Tooltip.of(Text.translatable(tipKey)))
                .build();
    }

    @Override
    public void resize(MinecraftClient client, int width, int height) {
        String string = fieldWidget.getText();
        this.init(client, width, height);
        fieldWidget.setText(string);
    }

    private static List<String> StringToList(String string) {
        return new ArrayList<>(Arrays.asList(Arrays
                .stream(string.split(","))
                //修复
                .filter(s -> !s.isEmpty())
                .map(String::trim)
                .toArray(String[]::new)));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        //MultilineText multilineText = MultilineText.create(textRenderer, Text.of("请输入你要屏蔽的服务器消息，用,隔开(英文逗号)"));
        MultilineText multilineText = MultilineText.create(textRenderer, Text.translatable("rsm.gui.modmenu.text1"));
        multilineText.drawWithShadow(context, width / 2 - 150, 120, 16, 0xffffff);
        //MultilineText multilineText1 = MultilineText.create(textRenderer, Text.of("请输入你要屏蔽的玩家聊天消息，用,隔开(英文逗号)"));
        //MultilineText multilineText1 = MultilineText.create(textRenderer, Text.translatable("rsm.gui.modmenu.text2"));
        //multilineText1.drawWithShadow(context, width / 2 - 150, 140, 16, 0xffffff);
        MultilineText multilineText2 = MultilineText.create(textRenderer, Text.translatable("rsm.gui.modmenu.text3"));
        multilineText2.drawWithShadow(context, width / 2 - 150, 180, 16, 0xffffff);
    }

    private void registerTextField() {
        fieldWidget = new TextFieldWidget(textRenderer, width / 2 - 150, 100, 300, 20, Text.of("需要屏蔽的文本"));
        //修改最大长度
        fieldWidget.setMaxLength(Integer.MAX_VALUE);
        fieldWidget.setTooltip(Tooltip.of(Text.translatable("rsm.gui.modmenu.text1")));
        fieldWidget.setText(String.join(",", ResConfig.getConfigs().getRemoveMessage().get()));
        fieldWidget.setChangedListener(ModMenuGui::FieldWidgetChanged);
        addDrawableChild(fieldWidget);

        MessageAndCommandField = new TextFieldWidget(textRenderer, width / 2 - 150, 160, 300, 20, Text.of("收到消息时,发送的指令"));
        //修改最大长度
        MessageAndCommandField.setMaxLength(Integer.MAX_VALUE);
        MessageAndCommandField.setTooltip(Tooltip.of(Text.translatable("rsm.gui.modmenu.text3")));
        MessageAndCommandField.setText(ResConfig.getConfigs().getReceiveMessageAndCommand().getText());
        MessageAndCommandField.setChangedListener(ModMenuGui::MessageAndCommandFieldChanged);
        addDrawableChild(MessageAndCommandField);
    }

    private static void FieldWidgetChanged(String text) {
        Configs configs = ResConfig.getConfigs();
        configs.getRemoveMessage().set(StringToList(text));
    }

    private static void MessageAndCommandFieldChanged(String message) {
        Configs configs = ResConfig.getConfigs();
        configs.getReceiveMessageAndCommand().setText(message);
        configs.getReceiveMessageAndCommand().set(StringToMap(message));
    }

    private void registerButton() {
        ButtonWidget removeMusicButton = RegisterButton(width / 2, 40, 150, 20, DefaultConfigEnum.MUSIC, "rsm.gui.modmenu.removemusic");
        ButtonWidget removeQuitButton = RegisterButton(width / 2 - 150, 40, 150, 20, DefaultConfigEnum.QUITGAME, "rsm.gui.modmenu.removequitgame");
        ButtonWidget removeJoinButton = RegisterButton(width / 2 - 150, 20, 150, 20, DefaultConfigEnum.JOINGAME, "rsm.gui.modmenu.removejoingame");
        ButtonWidget removeAdvButton = RegisterButton(width / 2, 20, 150, 20, DefaultConfigEnum.ADV, "rsm.gui.modmenu.removeadvmessage");
        ButtonWidget removeSameTimeMessageButton = RegisterButton(width / 2 - 150, 60, 150, 20, DefaultConfigEnum.SAMETIME, "rsm.gui.modmenu.removesametimemessage");
        //ButtonWidget RemoveChooseStatusButton = RegisterButton(width / 2, 80, 150, 20, ResConfig.getConfigs().getRemoveMessage().getRemoveStatus(), "rsm.gui.modmenu.removechoosestatus");
        ButtonWidget RemoveChooseStatusButton = ButtonWidget.builder(
                        getTextBasedOnState(ResConfig.getConfigs().getRemoveMessage().getRemoveStatus())
                        , ModMenuGui::RemoveChooseStatusAction)
                .dimensions(width / 2, 80, 150, 20)
                .tooltip(Tooltip.of(Text.translatable("rsm.gui.modmenu.removechoosestatus")))
                .build();
        ButtonWidget MeAnCoChooseStatusButton = ButtonWidget.builder(
                        getTextBasedOnState(ResConfig.getConfigs().getReceiveMessageAndCommand().getRemoveStatus())
                        , ModMenuGui::MeAnCoChooseStatusAction)
                .dimensions(width / 2, 140, 150, 20)
                .tooltip(Tooltip.of(Text.translatable("rsm.gui.modmenu.removechoosestatus")))
                .build();
        ButtonWidget modMenuButton = ButtonWidget.builder(Text.translatable("rsm.gui.modmenu.quit"), button -> this.close())
                .dimensions(width / 2 - 70, 220, 150, 20)
                //.tooltip(Tooltip.of(Text.of("返回上个界面")))
                .tooltip(Tooltip.of(Text.translatable("rsm.gui.modmenu.modmenuquit.tooltip")))
                .build();

        addDrawableChild(removeAdvButton);
        addDrawableChild(removeMusicButton);
        addDrawableChild(removeJoinButton);
        addDrawableChild(removeQuitButton);
        addDrawableChild(removeSameTimeMessageButton);
        addDrawableChild(RemoveChooseStatusButton);
        addDrawableChild(MeAnCoChooseStatusButton);
        addDrawableChild(modMenuButton);
    }

    private static void MeAnCoChooseStatusAction(ButtonWidget button) {
        RemoveStatus removeStatus = ResConfig.getConfigs().getReceiveMessageAndCommand().getRemoveStatus();
        ResConfig.getConfigs().getReceiveMessageAndCommand().setRemoveStatus(removeStatus.getNext());
        button.setMessage(getTextBasedOnState(ResConfig.getConfigs().getReceiveMessageAndCommand().getRemoveStatus()));
    }

    private static void RemoveChooseStatusAction(ButtonWidget button) {
        RemoveStatus removeStatus = ResConfig.getConfigs().getRemoveMessage().getRemoveStatus();
        ResConfig.getConfigs().getRemoveMessage().setRemoveStatus(removeStatus.getNext());
        button.setMessage(getTextBasedOnState(ResConfig.getConfigs().getRemoveMessage().getRemoveStatus()));
    }

    private static Map<String, String> StringToMap(String s) {
        Map<String, String> map = new HashMap<>();
        String[] split = s.split(",");
        for (String string : split) {
            if (string.startsWith("[") && string.endsWith("]")) {
                string = string.substring(1, string.length() - 1);
            }
            String[] split1 = string.split(":");
            if (split1.length >= 2 && (split1[0] != null && split1[1] != null)) {
                map.put(split1[0], split1[1]);
            }
        }
        return map;
    }
}