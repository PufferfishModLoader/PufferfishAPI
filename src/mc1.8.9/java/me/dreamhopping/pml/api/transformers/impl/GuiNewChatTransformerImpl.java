package me.dreamhopping.pml.api.transformers.impl;

import me.dreamhopping.pml.api.events.bus.EventBus;
import me.dreamhopping.pml.api.events.impl.client.chat.ClientChatReceivedEvent;
import net.minecraft.util.IChatComponent;

/**
 * An implementation for the GuiNewChatTransformer class
 *
 * @see me.dreamhopping.pml.api.transformers.GuiNewChatTransformer
 */
public class GuiNewChatTransformerImpl {
    public static void printChatMessage(IChatComponent chatComponent) {
        // Post the chat message event
        EventBus.INSTANCE.post(new ClientChatReceivedEvent(chatComponent.getUnformattedText()));
    }
}
