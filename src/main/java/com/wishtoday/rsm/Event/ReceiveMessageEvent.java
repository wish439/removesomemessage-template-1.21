package com.wishtoday.rsm.Event;

import com.mojang.authlib.GameProfile;
import com.wishtoday.rsm.Config.Configs;
import com.wishtoday.rsm.Config.ResConfig;
import com.wishtoday.rsm.Util.Config.MatchMode;
import com.wishtoday.rsm.Util.Config.RemoveStatus;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.text.Text;

import java.time.Instant;
import java.util.*;

@Environment(EnvType.CLIENT)
public class ReceiveMessageEvent implements ClientReceiveMessageEvents.Chat,
        ClientReceiveMessageEvents.Game {

    @Override
    public void onReceiveChatMessage(Text messageText
            , SignedMessage signedMessage
            , GameProfile sender
            , MessageType.Parameters params
            , Instant receptionTimestamp) {
        messageAndCommandReceive(Text.of(signedMessage.getSignedContent()), RemoveStatus.PLAYER);
    }

    @Override
    public void onReceiveGameMessage(Text messageText, boolean b) {
        messageAndCommandReceive(messageText, RemoveStatus.SERVER);
    }

    private static void messageAndCommandReceive(Text messageText, RemoveStatus status) {
        String message = messageText.getString();
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        Configs configs = ResConfig.getConfigs();
        RemoveStatus removeStatus = configs.getReceiveMessageAndCommand().getRemoveStatus();
        if (removeStatus.canRemoveFrom(status)) {
            MessageReceive(message, player, configs, configs.getReceiveMessageAndCommand().getMatchMode());
        }
    }

    //接收消息,并发送Command
    private static void MessageReceive(String message
            , ClientPlayerEntity player, Configs configs
            , MatchMode matchMode) {
        Map<String, String> messageAndCommand = configs.getReceiveMessageAndCommand().get();
        Set<String> strings = messageAndCommand.keySet();
        for (String s : strings) {
            if (matchMode.matches(message, s)) {
                if (player != null) {
                    String command = messageAndCommand.get(s);
                    if (!command.startsWith("/"))
                        player.networkHandler.sendCommand(command);
                    else player.networkHandler.sendCommand(command.substring(1));
                }
            }
        }
    }
}
