package me.dreamhopping.pml.api.events.bus

/**
 * Assigns a method to a specific event
 *
 * @see EventBus
 * @see me.dreamhopping.pml.api.events.Event
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
annotation class EventListener(val priority: EventPriority = EventPriority.LOW)
