/*
 *       Copyright (C) 2018-present Hyperium <https://hyperium.cc/>
 *
 *       This program is free software: you can redistribute it and/or modify
 *       it under the terms of the GNU Lesser General Public License as published
 *       by the Free Software Foundation, either version 3 of the License, or
 *       (at your option) any later version.
 *
 *       This program is distributed in the hope that it will be useful,
 *       but WITHOUT ANY WARRANTY; without even the implied warranty of
 *       MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *       GNU Lesser General Public License for more details.
 *
 *       You should have received a copy of the GNU Lesser General Public License
 *       along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package me.dreamhopping.pml.api.events.bus

import com.google.common.reflect.TypeToken
import java.util.concurrent.CopyOnWriteArrayList
import java.util.function.Consumer

class EventBus {
    private val subscriptions = HashMap<Class<*>, CopyOnWriteArrayList<EventSubscriber>>()

    /**
     * Registers all methods of a class into the event system with
     * the [EventListener] annotation
     *
     * @param obj An instance of the class which you would like to register as an event
     * @see EventListener
     */
    fun register(obj: Any) {
        // also contains the class itself
        val token: TypeToken<*> = TypeToken.of(obj.javaClass)
        val superClasses: Set<*> = token.types.rawTypes()

        // we also want to loop over the super classes, since declaredMethods only gets method in the class itself
        for (temp in superClasses) {
            val clazz = temp as Class<*>

            // iterates though all the methods in the class
            for (method in clazz.declaredMethods) {
                // all the information and error checking before the method is added such
                // as if it even is an event before the element even touches the HashMap
                if (method.getAnnotation(EventListener::class.java) == null) {
                    continue
                }
                requireNotNull(method.parameters[0]) { "Couldn't find parameter inside of " + method.name + "!" }
                val event = method.parameters[0].type
                val priority: EventPriority = method.getAnnotation(EventListener::class.java).priority
                method.isAccessible = true

                // where the method gets added to the event key inside of the subscription hashmap
                // the arraylist is either sorted or created before the element is added
                if (subscriptions.containsKey(event)) {
                    // sorts array on insertion
                    subscriptions[event]?.add(EventSubscriber(obj, method, priority))
                    subscriptions[event]?.sortBy { it.priority.priority }
                } else {
                    // event hasn't been added before so it creates a new instance
                    // sorting does not matter here since there is no other elements to compete against
                    subscriptions[event] = CopyOnWriteArrayList()
                    subscriptions[event]?.add(EventSubscriber(obj, method, priority))
                    subscriptions[event]?.sortBy { it.priority.priority }
                }
            }
        }
    }

    /**
     * Unregisters all methods of the class instance from the event system
     * inside of [.subscriptions]
     *
     * @param obj An instance of the class which you would like to register as an event
     */
    fun unregister(obj: Any) {
        subscriptions.values.forEach(Consumer { map: CopyOnWriteArrayList<EventSubscriber> -> map.removeIf { it: EventSubscriber -> it.instance === obj } })
    }

    /**
     * Unregisters all methods of the class from the event system
     * inside of [.subscriptions]
     *
     * @param clazz An instance of the class which you would like to register as an event
     */
    fun unregister(clazz: Class<*>) {
        subscriptions.values.forEach(Consumer { map: CopyOnWriteArrayList<EventSubscriber> -> map.removeIf { it: EventSubscriber -> it.instance.javaClass == clazz } })
    }

    /**
     * Invokes all of the methods which are inside of the classes
     * registered to the event
     *
     * @param event Event that is being posted
     */
    fun post(event: Any?) {
        if (event == null) {
            return
        }
        subscriptions.getOrDefault(event.javaClass, CopyOnWriteArrayList()).forEach(
            Consumer { sub: EventSubscriber ->
                try {
                    sub.invoke(event)
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            })
    }

    companion object {
        @JvmField
        val INSTANCE = EventBus()
    }
}