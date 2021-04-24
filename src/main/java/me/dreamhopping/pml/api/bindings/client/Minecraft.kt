package me.dreamhopping.pml.api.bindings.client

import me.dreamhopping.pml.api.bindings.BindingsRegistry
import me.dreamhopping.pml.api.bindings.client.gui.FontRenderer
import me.dreamhopping.pml.mods.core.MCVersion

interface Minecraft {
    val version: MCVersion
    val isGamePaused: Boolean
    val fps: Int
    val sessionInfo: Map<String, String>

    fun is64Bit(): Boolean

    @JvmDefault
    val fontRenderer: FontRenderer
        get() = BindingsRegistry.fontRenderer

    companion object {
        @JvmStatic
        fun getMinecraft() = BindingsRegistry.minecraft
    }
}