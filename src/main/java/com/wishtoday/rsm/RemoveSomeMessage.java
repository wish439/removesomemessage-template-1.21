package com.wishtoday.rsm;

import com.wishtoday.rsm.Event.AllowReceiveMessageEvent;
import com.wishtoday.rsm.Event.ReceiveMessageEvent;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoveSomeMessage implements ModInitializer {
	public static final String MOD_ID = "removesomemessage";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        ClientReceiveMessageEvents.ALLOW_GAME.register(new AllowReceiveMessageEvent());
		ClientReceiveMessageEvents.ALLOW_CHAT.register(new AllowReceiveMessageEvent());
		ClientReceiveMessageEvents.CHAT.register(new ReceiveMessageEvent());
		ClientReceiveMessageEvents.GAME.register(new ReceiveMessageEvent());
	}
}