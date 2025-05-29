package com.wishtoday.rsm.Event;

import com.mojang.authlib.GameProfile;
import com.wishtoday.rsm.Config.Configs;
import com.wishtoday.rsm.Config.ResConfig;
import com.wishtoday.rsm.RemoveSomeMessage;
import com.wishtoday.rsm.Unit.MatchMode;
import com.wishtoday.rsm.Unit.RemoveStatus;
import com.wishtoday.rsm.Unit.Strategy.StopMessageStrategy.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static com.wishtoday.rsm.RemoveSomeMessage.LOGGER;

@Environment(EnvType.CLIENT)
public class AllowReceiveMessageEvent implements ClientReceiveMessageEvents.AllowChat
        , ClientReceiveMessageEvents.AllowGame {

    private static final List<StopMessages> stopMessages = Arrays.asList(
            new AdvMessages(),
            new JoinGameMessages(),
            new DeathMessages(),
            new QuitGameMessages(),
            new SameTimeMessage()
    );
    @Override
    public boolean allowReceiveChatMessage(Text message
            , @Nullable SignedMessage signedMessage
            , @Nullable GameProfile sender
            , MessageType.Parameters params
            , Instant receptionTimestamp) {
        return ShouldRemoveMessageFromText(Text.of(signedMessage.getSignedContent()), RemoveStatus.PLAYER, ResConfig.getConfigs().getRemoveMessage().getMatchMode());
    }
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
