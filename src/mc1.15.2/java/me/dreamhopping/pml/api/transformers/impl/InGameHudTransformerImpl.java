package me.dreamhopping.pml.api.transformers.impl;

import me.dreamhopping.pml.api.events.impl.client.chat.ClientChatReceivedEvent;
import me.dreamhopping.pml.api.events.impl.client.gui.ClientRenderGameOverlayEvent;
import me.dreamhopping.pml.events.EventBus;
import me.dreamhopping.pml.api.transformers.InGameHudTransformer;
import net.minecraft.text.Text;

/**
 * An implementation for the InGameHudTransformer class
 *
 * @see InGameHudTransformer
 */
public class InGameHudTransformerImpl {
    public static void addChatMessage(Text text) {
        // Post the chat message event
        EventBus.INSTANCE.post(new ClientChatReceivedEvent(text.getString()));
    }

    public static void render() {
        EventBus.INSTANCE.post(new ClientRenderGameOverlayEvent());
    }
}
