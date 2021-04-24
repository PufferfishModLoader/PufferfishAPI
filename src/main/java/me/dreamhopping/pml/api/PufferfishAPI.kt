package me.dreamhopping.pml.api

import me.dreamhopping.pml.api.bindings.provider.PufferfishAPIProvider
import me.dreamhopping.pml.mods.Mod
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import kotlin.system.exitProcess

class PufferfishAPI : Mod {
    val logger: Logger = LogManager.getLogger("PufferfishAPI")

    override fun initialize() {
        logger.info("Waking up PufferfishAPIProvider")

        try {
            Class.forName("me.dreamhopping.pml.api.impl.PufferfishAPIProviderImpl", true, this.javaClass.classLoader)
        } catch (e: Exception) {
            logger.error(
                "PufferfishAPIProviderImpl doesn't want to wake up... They seemed pretty angry... This is what they said:",
                e
            )
            exitProcess(-1)
        }

        // Do some required things like transformers, class bindings, etc.
        PufferfishAPIProvider.applyTransformers()
        PufferfishAPIProvider.applyBindings()
    }

    override fun unload() {
        // TODO: Maybe do some things
    }
}