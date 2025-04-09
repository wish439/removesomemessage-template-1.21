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
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.wishtoday.rsm.RemoveSomeMessage.LOGGER;

public class ReceiveMessageEvent implements ClientReceiveMessageEvents.AllowGame, ClientReceiveMessageEvents.AllowChat {
    private static final List<StopMessages> stopMessages = Arrays.asList(
            new AdvMessages(),
            new JoinGameMessages(),
            new MusicMessages(),
            new QuitGameMessages()
    );

    @Override
    public boolean allowReceiveGameMessage(Text text, boolean b) {
        Configs config = ResConfig.getConfigs();
        //固定删除消息
        for (StopMessages message : stopMessages) {
            if (message.intercept(text, b, config)) {
                LOGGER.info(message.getReturnValue(text.getString()));
                return false;
            }
        }
        //根据输入框输入删除消息
        List<String> messages = config.getRemoveMessages();
        //修复
        if (messages.isEmpty()) return true;
        for (String message : messages) {
            if (text.getString().contains(message.trim())) {
                LOGGER.info(text.getString() + "由" + RemoveSomeMessage.MOD_ID + " Mod 因匹配上 " + message + " 而被拦截");
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean allowReceiveChatMessage(Text message, @Nullable SignedMessage signedMessage, @Nullable GameProfile sender, MessageType.Parameters params, Instant receptionTimestamp) {
        Configs config = ResConfig.getConfigs();
        List<String> list = new ArrayList<>(config.getPlayerChatMessages());
        if (list.isEmpty()) return true;
        for (String s : list) {
            if (message.getString().contains(s.trim())) {
                LOGGER.info(message.getString() + "由" + RemoveSomeMessage.MOD_ID + " Mod 因匹配上玩家聊天关键词 " + s + " 而被拦截");
                return false;
            }
        }
        return true;
    }
}
