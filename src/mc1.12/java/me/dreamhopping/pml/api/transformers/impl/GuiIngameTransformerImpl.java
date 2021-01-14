package me.dreamhopping.pml.api.transformers.impl;

import me.dreamhopping.pml.api.events.impl.client.gui.ClientRenderGameOverlayEvent;
import me.dreamhopping.pml.api.transformers.GuiIngameTransformer;
import me.dreamhopping.pml.events.EventBus;

/**
 * An implementation for the GuiIngameTransformer class
 *
 * @see GuiIngameTransformer
 */
public class GuiIngameTransformerImpl {
    public static void renderGameOverlay() {
        EventBus.INSTANCE.post(new ClientRenderGameOverlayEvent());
    }
}
