package com.wishtoday.rsm.Event;

import com.mojang.authlib.GameProfile;
import com.wishtoday.rsm.Config.Configs;
import com.wishtoday.rsm.Config.ResConfig;
import com.wishtoday.rsm.Event.EventValues.InterceptMessages.AdvMessages;
import com.wishtoday.rsm.Event.EventValues.InterceptMessages.JoinGameMessages;
import com.wishtoday.rsm.Event.EventValues.InterceptMessages.MusicMessages;
import com.wishtoday.rsm.Event.EventValues.InterceptMessages.QuitGameMessages;
import com.wishtoday.rsm.Event.EventValues.StopMessages;
import com.wishtoday.rsm.RemoveSomeMessage;
import com.wishtoday.rsm.Unit.RemoveStatus;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.sql.Time;
import java.time.Instant;
import java.util.*;

import static com.wishtoday.rsm.RemoveSomeMessage.LOGGER;

public class ReceiveMessageEvent implements ClientReceiveMessageEvents.AllowGame
        , ClientReceiveMessageEvents.AllowChat
        , ClientReceiveMessageEvents.Chat,
        ClientReceiveMessageEvents.Game {
    private static long TimeSecond = Instant.now().getEpochSecond();
    private static final List<StopMessages> stopMessages = Arrays.asList(
            new AdvMessages(),
            new JoinGameMessages(),
            new MusicMessages(),
            new QuitGameMessages()
    );

    @Override
    public boolean allowReceiveGameMessage(Text text, boolean b) {
        //Instant now = Instant.now();
        //LOGGER.info(now.getEpochSecond() + "");
        Configs config = ResConfig.getConfigs();
        if ((TimeSecond == Instant.now().getEpochSecond())
                && config.getRemoveSameTimeMessage().get()) {
            LOGGER.info(text.getString() + "由" + RemoveSomeMessage.MOD_ID + " Mod 同时间消息拦截器发起的拦截");
            return false;
        } else {
            TimeSecond = Instant.now().getEpochSecond();
        }
        //固定删除消息
        for (StopMessages message : stopMessages) {
            if (message.intercept(text, b, config)) {
                LOGGER.info(message.getReturnValue(text.getString()));
                return false;
            }
        }
        RemoveStatus removeStatus = config.getRemoveMessage().getRemoveStatus();
        if (removeStatus == RemoveStatus.ALL
                || removeStatus == RemoveStatus.SERVER) {
            //根据输入框输入删除消息
            List<String> messages = config.getRemoveMessage().get();
            //修复
            if (messages.isEmpty()) return true;
            for (String message : messages) {
                if (text.getString().contains(message)) {
                    LOGGER.info(text.getString() + "由" + RemoveSomeMessage.MOD_ID + " Mod 因匹配上 " + message + " 而被拦截");
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean allowReceiveChatMessage(Text message, @Nullable SignedMessage signedMessage, @Nullable GameProfile sender, MessageType.Parameters params, Instant receptionTimestamp) {
        Configs configs = ResConfig.getConfigs();
        RemoveStatus removeStatus = configs.getRemoveMessage().getRemoveStatus();
        if (removeStatus == RemoveStatus.ALL
                || removeStatus == RemoveStatus.PLAYER) {
            List<String> list = configs.getRemoveMessage().get();
            if (list.isEmpty()) return true;
            for (String s : list) {
                if (message.getString().contains(s)) {
                    LOGGER.info(message.getString() + "由" + RemoveSomeMessage.MOD_ID + " Mod 因匹配上 " + s + " 而被拦截");
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onReceiveChatMessage(Text messageText, SignedMessage signedMessage, GameProfile sender, MessageType.Parameters params, Instant receptionTimestamp) {
        String message = messageText.getString();
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        Configs configs = ResConfig.getConfigs();
        RemoveStatus removeStatus = configs.getReceiveMessageAndCommand().getRemoveStatus();
        if (removeStatus == RemoveStatus.ALL || removeStatus == RemoveStatus.PLAYER) {
            MessageReceive(message, player, configs);
        }
    }

    @Override
    public void onReceiveGameMessage(Text messageText, boolean b) {
        String message = messageText.getString();
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        Configs configs = ResConfig.getConfigs();
        RemoveStatus removeStatus = configs.getReceiveMessageAndCommand().getRemoveStatus();
        if (removeStatus == RemoveStatus.ALL || removeStatus == RemoveStatus.SERVER) {
            MessageReceive(message, player, configs);
        }
    }

    private static void MessageReceive(String message, ClientPlayerEntity player, Configs configs) {
        Map<String, String> messageAndCommand = configs.getReceiveMessageAndCommand().get();
        Set<String> strings = messageAndCommand.keySet();
        for (String s : strings) {
            if (message.contains(s)) {
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
