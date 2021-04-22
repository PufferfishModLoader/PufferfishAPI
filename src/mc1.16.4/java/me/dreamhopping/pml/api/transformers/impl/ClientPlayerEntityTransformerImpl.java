package me.dreamhopping.pml.api.transformers.impl;

import me.dreamhopping.pml.api.events.bus.EventBus;
import me.dreamhopping.pml.api.events.impl.client.chat.ClientChatSentEvent;
import me.dreamhopping.pml.api.events.impl.client.player.ClientItemDropEvent;
import me.dreamhopping.pml.api.events.impl.client.player.ClientPlayerRespawnEvent;
import me.dreamhopping.pml.api.transformers.ClientPlayerEntityTransformer;

/**
 * An implementation for the ClientPlayerEntityTransformer class
 *
 * @see ClientPlayerEntityTransformer
 */
public class ClientPlayerEntityTransformerImpl {
    public static void sendChatMessage(String message) {
        EventBus.INSTANCE.post(new ClientChatSentEvent(message));
    }

    public static void dropSelectedItem(boolean dropAllItems) {
        // TODO: Use dropAllItems (needs further research on when it's true / false)
        EventBus.INSTANCE.post(new ClientItemDropEvent());
    }

    public static void respawn() {
        EventBus.INSTANCE.post(new ClientPlayerRespawnEvent());
    }
}
