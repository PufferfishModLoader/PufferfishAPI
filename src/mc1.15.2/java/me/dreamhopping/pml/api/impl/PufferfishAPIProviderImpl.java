package me.dreamhopping.pml.api.impl;

import me.dreamhopping.pml.api.bindings.BindingsRegistry;
import me.dreamhopping.pml.api.bindings.provider.PufferfishAPIProvider;
import me.dreamhopping.pml.api.impl.minecraft.FontRendererImpl;
import me.dreamhopping.pml.api.impl.minecraft.MinecraftImpl;
import me.dreamhopping.pml.api.transformers.ClientPlayNetworkHandlerTransformer;
import me.dreamhopping.pml.api.transformers.ClientPlayerEntityTransformer;
import me.dreamhopping.pml.api.transformers.InGameHudTransformer;
import me.dreamhopping.pml.api.transformers.MinecraftClientTransformer;
import me.dreamhopping.pml.launch.loader.PMLClassLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class PufferfishAPIProviderImpl implements PufferfishAPIProvider {
    private static final Logger LOGGER = LogManager.getLogger("PufferfishAPIProvider");

    static {
        final Random random = new Random();

        // Log a random message
        BindingsRegistry.apiProvider = new PufferfishAPIProviderImpl();
        LOGGER.info(logChoices[random.nextInt(logChoices.length)]);
    }

    public void applyTransformers() {
        LOGGER.info("Applying transformers");

        PMLClassLoader classLoader = (PMLClassLoader) Thread.currentThread().getContextClassLoader();
        classLoader.addTransformer(new ClientPlayerEntityTransformer());
        classLoader.addTransformer(new ClientPlayNetworkHandlerTransformer());
        classLoader.addTransformer(new InGameHudTransformer());
        classLoader.addTransformer(new MinecraftClientTransformer());
    }

    public void applyBindings() {
        LOGGER.info("Applying bindings");

        BindingsRegistry.minecraft = new MinecraftImpl();
        BindingsRegistry.fontRenderer = new FontRendererImpl();
    }
}
