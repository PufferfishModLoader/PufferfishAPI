package me.dreamhopping.pml.api.transformers.impl;

import me.dreamhopping.pml.api.events.impl.client.net.ClientDisconnectedEvent;
import me.dreamhopping.pml.api.events.impl.client.net.ClientJoinServerEvent;
import me.dreamhopping.pml.events.EventBus;
import net.minecraft.text.Text;

import java.net.SocketAddress;

/**
 * An implementation for the ClientPlayNetworkHandlerTransformer class
 *
 * @see me.dreamhopping.pml.api.transformers.ClientPlayNetworkHandlerTransformer
 */
public class ClientPlayNetworkHandlerTransformerImpl {
    public static void onGameJoin(boolean isLocal, SocketAddress address) {
        EventBus.INSTANCE.post(new ClientJoinServerEvent(isLocal, address));
    }

    public static void onDisconnected(Text reason) {
        EventBus.INSTANCE.post(new ClientDisconnectedEvent(reason.getString()));
    }
}
