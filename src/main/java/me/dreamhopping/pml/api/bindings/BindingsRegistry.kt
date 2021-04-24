package me.dreamhopping.pml.api.bindings

import me.dreamhopping.pml.api.bindings.client.Minecraft
import me.dreamhopping.pml.api.bindings.client.gui.FontRenderer
import me.dreamhopping.pml.api.bindings.provider.PufferfishAPIProvider

object BindingsRegistry {
    lateinit var apiProvider: PufferfishAPIProvider

    lateinit var minecraft: Minecraft
    lateinit var fontRenderer: FontRenderer
}