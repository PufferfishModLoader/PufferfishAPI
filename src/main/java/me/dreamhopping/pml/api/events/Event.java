package me.dreamhopping.pml.api.events;

import me.dreamhopping.pml.api.events.bus.EventBus;

/**
 * A basic event class
 * <p>
 * For a cancellable event:
 *
 * @see CancellableEvent
 */
public class Event {
    /**
     * Informs the event bus to post this event, all EventListeners will be notified soon after this is executed
     */
    public void postEvent() {
        EventBus.INSTANCE.post(this);
    }
}
