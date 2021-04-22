package me.dreamhopping.pml.api.events.bus;

public enum EventPriority {
    HIGH(0),
    NORMAL(1),
    LOW(2);

    private final int priority;

    EventPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
