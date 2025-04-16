package com.wishtoday.rsm;

import com.wishtoday.rsm.Event.ReceiveMessageEvent;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoveSomeMessage implements ModInitializer {
	public static final String MOD_ID = "removesomemessage";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
        ClientReceiveMessageEvents.ALLOW_GAME.register(new ReceiveMessageEvent());
		ClientReceiveMessageEvents.ALLOW_CHAT.register(new ReceiveMessageEvent());
		ClientReceiveMessageEvents.GAME.register(ReceiveMessageEvent::MessageReceive);
	}
}