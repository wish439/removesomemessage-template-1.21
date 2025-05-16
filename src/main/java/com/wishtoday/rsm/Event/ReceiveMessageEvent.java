package com.wishtoday.rsm.Event;

import com.mojang.authlib.GameProfile;
import com.wishtoday.rsm.Config.Configs;
import com.wishtoday.rsm.Config.ResConfig;
import com.wishtoday.rsm.Unit.MatchMode;
import com.wishtoday.rsm.Unit.Strategy.StopMessageStrategy.*;
import com.wishtoday.rsm.Unit.Strategy.StopMessageStrategy.StopMessages;
import com.wishtoday.rsm.RemoveSomeMessage;
import com.wishtoday.rsm.Unit.RemoveStatus;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.util.*;

import static com.wishtoday.rsm.RemoveSomeMessage.LOGGER;

public class ReceiveMessageEvent implements ClientReceiveMessageEvents.AllowGame
        , ClientReceiveMessageEvents.AllowChat
        , ClientReceiveMessageEvents.Chat,
        ClientReceiveMessageEvents.Game {
    private static final List<StopMessages> stopMessages = Arrays.asList(
            new AdvMessages(),
            new JoinGameMessages(),
            new DeathMessages(),
            new QuitGameMessages(),
            new SameTimeMessage()
    );

    @Override
    public boolean allowReceiveGameMessage(Text text, boolean b) {
        boolean yes = true;
        Configs config = ResConfig.getConfigs();
        //固定删除消息
        for (StopMessages message : stopMessages) {
            if (message.intercept(text, config)) {
                LOGGER.info(message.getReturnValue(text.getString()));
                yes = false;
                break;
            }
        }
        yes = ShouldRemoveMessageFromText(text, RemoveStatus.SERVER, config.getRemoveMessage().getMatchMode(), yes);
        return yes;
    }

    @Override
    public boolean allowReceiveChatMessage(Text message
            , @Nullable SignedMessage signedMessage
            , @Nullable GameProfile sender
            , MessageType.Parameters params
            , Instant receptionTimestamp) {
        return ShouldRemoveMessageFromText(Text.of(signedMessage.getSignedContent()), RemoveStatus.PLAYER, ResConfig.getConfigs().getRemoveMessage().getMatchMode());
    }

    @Override
    public void onReceiveChatMessage(Text messageText
            , SignedMessage signedMessage
            , GameProfile sender
            , MessageType.Parameters params
            , Instant receptionTimestamp) {
        LOGGER.info("接收消息:" + signedMessage.getSignedContent());
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

    private static boolean ShouldRemoveMessageFromText(Text text
            , RemoveStatus removeStatus, MatchMode matchMode, boolean b) {
        //如果b为false则返回,确保b必定为true
        if (!b) return false;
        Configs config = ResConfig.getConfigs();
        //如果此输入框的删除状态不可以删除于removeStatus环境,则返回为true(即可显示)
        if (!config.getRemoveMessage().getRemoveStatus().canRemoveFrom(removeStatus)) return true;
        //获取输入框的消息
        List<String> messages = config.getRemoveMessage().get();
        //防止任何消息被屏蔽
        if (messages.isEmpty()) return true;
        //如果文本包含屏蔽词,返回false(即不可显示)
        for (String message : messages) {
            if (matchMode.matches(text.getString(), message)) {
                LOGGER.info(text.getString() + "由" + RemoveSomeMessage.MOD_ID + " Mod 因匹配上 " + message + " 而被拦截");
                b = false;
                break;
            }
        }
        return b;
    }

    private static boolean ShouldRemoveMessageFromText(Text text, RemoveStatus removeStatus, MatchMode matchMode) {
        return ShouldRemoveMessageFromText(text, removeStatus, matchMode, true);
    }
}
