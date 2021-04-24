package me.dreamhopping.pml.api.events.impl.client.net

import me.dreamhopping.pml.api.events.Event
import java.net.SocketAddress

/**
 * Fired when the client has joined a server
 * @param isLocal if the server is hosted locally (a singleplayer world)
 * @param address the [SocketAddress] of the server
 */
class ClientJoinServerEvent(val isLocal: Boolean, val address: SocketAddress) : Event()