package me.dreamhopping.pml.api.events.impl.client.chat

import me.dreamhopping.pml.api.events.Event

/**
 * Called when a chat message is receieved by the client
 * TODO: Change this to use chat component
 *
 * @author dreamhopping
 * @see Event
 */
class ClientChatReceivedEvent(var message: String) : Event()