package me.dreamhopping.pml.api.transformers.impl;

import me.dreamhopping.pml.api.events.bus.EventBus;
import me.dreamhopping.pml.api.events.impl.client.gui.ClientRenderGameOverlayEvent;
import me.dreamhopping.pml.api.transformers.GuiIngameTransformer;

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
