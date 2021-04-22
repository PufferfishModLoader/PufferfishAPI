package me.dreamhopping.pml.api.events.bus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Assigns a method to a specific event
 *
 * @see me.dreamhopping.pml.api.events.Event
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventListener {
    EventPriority priority() default EventPriority.LOW;
}
