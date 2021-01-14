package me.dreamhopping.pml.api.transformers.impl;

import me.dreamhopping.pml.api.events.impl.client.tick.ClientRenderTickEvent;
import me.dreamhopping.pml.api.events.impl.client.tick.ClientTickEvent;
import me.dreamhopping.pml.events.EventBus;

/**
 * An implementation for the MinecraftClientTransformer class
 *
 * @see me.dreamhopping.pml.api.transformers.MinecraftClientTransformer
 */
public class MinecraftClientTransformerImpl {
    public static void tick() {
        EventBus.INSTANCE.post(new ClientTickEvent());
    }

    public static void render() {
        EventBus.INSTANCE.post(new ClientRenderTickEvent());
    }
}
