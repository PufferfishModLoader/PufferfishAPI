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

import me.dreamhopping.pml.launch.loader.PMLClassLoader
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type
import java.lang.reflect.Method

/**
 * Used to store information about events and index them so they can be easily accessed by ASM
 */
class EventSubscriber(val instance: Any, val method: Method, val priority: EventPriority) {
    private val objName = this.instance.javaClass.simpleName.replace(".", "_")
    private val methodName = this.method.name

    // ASM manufactured event handler.
    private var handler: EventHandler? = null
    operator fun invoke(event: Any?) {
        handler!!.handle(event)
    }

    fun copy(instance: Any, method: Method, priority: EventPriority): EventSubscriber {
        return EventSubscriber(instance, method, priority)
    }

    override fun toString(): String {
        return "EventSubscriber(instance=$instance, method=$method, priority=$priority)"
    }

    override fun hashCode(): Int {
        return (instance.hashCode() * 31 + method.hashCode()) * 31 + priority.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return if (this !== other) {
            if (other is EventSubscriber) {
                return instance == other.instance && method == other.method && priority == other.priority
            }
            false
        } else {
            true
        }
    }

    private fun createHandler(callback: Method): Class<*> {
        // ClassName$methodName_EventClass_XXX
        val name = objName + "$" + callback.name + "_" + callback.parameters[0].type.simpleName + "_" + ID++
        val eventType = Type.getInternalName(callback.parameterTypes[0])
        val cw = ClassWriter(ClassWriter.COMPUTE_FRAMES)
        val desc = name.replace(".", "/")
        val instanceClassName = instance.javaClass.name.replace(".", "/")

        cw.visit(
            Opcodes.V1_6,
            Opcodes.ACC_PUBLIC or Opcodes.ACC_SUPER,
            desc,
            null,
            "java/lang/Object",
            arrayOf("me/dreamhopping/pml/api/events/bus/EventSubscriber\$EventHandler")
        )
        cw.visitSource(".dynamic", null)
        cw.visitField(Opcodes.ACC_PUBLIC, "instance", "Ljava/lang/Object;", null, null).visitEnd()

        var mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "(Ljava/lang/Object;)V", null, null)
        mv.visitCode()
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false)
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitVarInsn(Opcodes.ALOAD, 1)
        mv.visitFieldInsn(Opcodes.PUTFIELD, desc, "instance", "Ljava/lang/Object;")
        mv.visitInsn(Opcodes.RETURN)
        mv.visitMaxs(2, 2)
        mv.visitEnd()

        mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "handle", "(Ljava/lang/Object;)V", null, null)
        mv.visitCode()
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(Opcodes.GETFIELD, desc, "instance", "Ljava/lang/Object;")
        mv.visitTypeInsn(Opcodes.CHECKCAST, instanceClassName)
        mv.visitVarInsn(Opcodes.ALOAD, 1)
        mv.visitTypeInsn(Opcodes.CHECKCAST, eventType)
        mv.visitMethodInsn(
            Opcodes.INVOKEVIRTUAL,
            instanceClassName,
            callback.name,
            Type.getMethodDescriptor(callback),
            false
        )
        mv.visitInsn(Opcodes.RETURN)
        mv.visitMaxs(2, 2)
        mv.visitEnd()
        cw.visitEnd()

        val handlerClassBytes = cw.toByteArray()
        return (javaClass.classLoader as PMLClassLoader).defineClass(name, handlerClassBytes)
    }

    interface EventHandler {
        fun handle(event: Any?)
    }

    companion object {
        private var ID = 0
    }

    init {
        try {
            handler = createHandler(method).getConstructor(Any::class.java).newInstance(instance) as EventHandler
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}