package me.dreamhopping.pml.api.events.impl.client.net

import me.dreamhopping.pml.api.events.Event

/**
 * Fired when the client is disconnected from the server
 *
 * This is client side only, for server, see [me.dreamhopping.pml.api.events.impl.server.net.ServerDisconnectEvent]
 * @param reason the reason the client was disconnected from the server
 */
class ClientDisconnectedEvent(val reason: String) : Event()