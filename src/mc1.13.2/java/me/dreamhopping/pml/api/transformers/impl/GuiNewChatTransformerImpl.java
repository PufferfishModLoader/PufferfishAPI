package me.dreamhopping.pml.api.transformers.impl;

import me.dreamhopping.pml.api.events.impl.client.chat.ClientChatReceivedEvent;
import me.dreamhopping.pml.events.EventBus;
import net.minecraft.util.text.ITextComponent;

/**
 * An implementation for the GuiNewChatTransformer class
 *
 * @see me.dreamhopping.pml.api.transformers.GuiNewChatTransformer
 */
public class GuiNewChatTransformerImpl {
    public static void printChatMessage(ITextComponent chatComponent) {
        EventBus.INSTANCE.post(new ClientChatReceivedEvent(chatComponent.getUnformattedComponentText()));
    }
}
