package me.dreamhopping.pml.api.events.impl.client.chat

import me.dreamhopping.pml.api.events.Event

/**
 * Called when a chat message is sent by the client
 *
 * @author dreamhopping
 * @see Event
 */
class ClientChatSentEvent(var message: String) : Event()