package me.dreamhopping.pml.api.bindings.provider

import me.dreamhopping.pml.api.bindings.BindingsRegistry

interface PufferfishAPIProvider {
    fun applyTransformers()
    fun applyBindings()

    companion object : PufferfishAPIProvider by BindingsRegistry.apiProvider {
        @JvmField
        val logChoices = arrayOf(
            "5 more minutes in bed, please? No? Ugh, fine. I'm up! I'm awake!",
            "Good morning! I'm ready for duty!",
            "*yawns* It's too early for this...",
            "Hey PML, PAPI here! Or am I?"
        )
    }
}
