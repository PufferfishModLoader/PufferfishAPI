package me.dreamhopping.pml.api.events.impl.client.chat;

import me.dreamhopping.pml.events.Event;

/**
 * Called when a chat message is receieved by the client
 *
 * @author dreamhopping
 * @see Event
 */
public class ClientChatReceivedEvent extends Event {
    public String message;

    // TODO: Change this to use chat component
    public ClientChatReceivedEvent(String message) {
        this.message = message;
    }
}
