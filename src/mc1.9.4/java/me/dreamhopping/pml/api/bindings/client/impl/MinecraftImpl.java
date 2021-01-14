package me.dreamhopping.pml.api.bindings.client.impl;

import me.dreamhopping.pml.api.bindings.client.Minecraft;
import me.dreamhopping.pml.mods.core.MCVersion;

import java.io.File;
import java.util.Map;

public class MinecraftImpl extends Minecraft {
    public MCVersion getVersion() {
        return MCVersion.V1_9_4;
    }

    public boolean is64Bit() {
        return net.minecraft.client.Minecraft.getMinecraft().isJava64bit();
    }

    public boolean isGamePaused() {
        return net.minecraft.client.Minecraft.getMinecraft().isGamePaused();
    }

    public int getFPS() {
        return net.minecraft.client.Minecraft.getDebugFPS();
    }

    public Map<String, String> getSessionInfo() {
        return net.minecraft.client.Minecraft.getSessionInfo();
    }

    public File getMinecraftDirectory() {
        return net.minecraft.client.Minecraft.getMinecraft().mcDataDir;
    }
}
