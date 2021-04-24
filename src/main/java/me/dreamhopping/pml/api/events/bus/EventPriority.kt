package me.dreamhopping.pml.api.events.bus

enum class EventPriority(val priority: Int) {
    HIGH(0),
    NORMAL(1),
    LOW(2);
}