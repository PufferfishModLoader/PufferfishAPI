package me.dreamhopping.pml.api.transformers.impl;

import me.dreamhopping.pml.api.events.impl.client.tick.ClientRenderTickEvent;
import me.dreamhopping.pml.api.events.impl.client.tick.ClientTickEvent;
import me.dreamhopping.pml.events.EventBus;

/**
 * An implementation for the MinecraftTransformer class
 *
 * @see me.dreamhopping.pml.api.transformers.MinecraftTransformer
 */
public class MinecraftTransformerImpl {
    public static void runTick() {
        EventBus.INSTANCE.post(new ClientTickEvent());
    }

    public static void runGameLoop() {
        EventBus.INSTANCE.post(new ClientRenderTickEvent());
    }
}
