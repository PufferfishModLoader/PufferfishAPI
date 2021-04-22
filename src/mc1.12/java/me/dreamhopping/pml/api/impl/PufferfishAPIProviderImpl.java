package me.dreamhopping.pml.api.impl;

import me.dreamhopping.pml.api.bindings.client.Minecraft;
import me.dreamhopping.pml.api.bindings.client.gui.FontRenderer;
import me.dreamhopping.pml.api.impl.minecraft.FontRendererImpl;
import me.dreamhopping.pml.api.impl.minecraft.MinecraftImpl;
import me.dreamhopping.pml.api.provider.PufferfishAPIProvider;
import me.dreamhopping.pml.api.transformers.*;
import me.dreamhopping.pml.launch.loader.PMLClassLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class PufferfishAPIProviderImpl extends PufferfishAPIProvider {
    private static final Logger LOGGER = LogManager.getLogger("PufferfishAPIProvider");

    static {
        final Random random = new Random();

        // Log a random message
        LOGGER.info(logChoices[random.nextInt(logChoices.length)]);
        PufferfishAPIProvider.setInstance(new PufferfishAPIProviderImpl());
    }

    public void applyTransformers() {
        LOGGER.info("Applying transformers");

        PMLClassLoader classLoader = (PMLClassLoader) Thread.currentThread().getContextClassLoader();
        classLoader.addTransformer(new GuiIngameTransformer());
        classLoader.addTransformer(new GuiNewChatTransformer());
        classLoader.addTransformer(new EntityPlayerSPTransformer());
        classLoader.addTransformer(new NetHandlerPlayClientTransformer());
        classLoader.addTransformer(new MinecraftTransformer());
    }

    public void applyBindings() {
        LOGGER.info("Applying bindings");

        Minecraft.setInstance(new MinecraftImpl());
        FontRenderer.setInstance(new FontRendererImpl());
    }
}
