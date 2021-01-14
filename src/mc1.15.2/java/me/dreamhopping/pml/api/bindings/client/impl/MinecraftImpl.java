package me.dreamhopping.pml.api.bindings.client.impl;

import com.google.common.collect.Maps;
import me.dreamhopping.pml.api.bindings.client.Minecraft;
import me.dreamhopping.pml.mods.core.MCVersion;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Session;

import java.io.File;
import java.util.Map;

public class MinecraftImpl extends Minecraft {
    public MCVersion getVersion() {
        return MCVersion.V1_15_2;
    }

    public boolean is64Bit() {
        return MinecraftClient.getInstance().is64Bit();
    }

    public boolean isGamePaused() {
        return MinecraftClient.getInstance().isPaused();
    }

    public int getFPS() {
        return MinecraftClient.currentFps;
    }

    public Map<String, String> getSessionInfo() {
        final Session session = MinecraftClient.getInstance().getSession();

        Map<String, String> sessionInfo = Maps.newHashMap();
        sessionInfo.put("X-Minecraft-Username", session.getUsername());
        sessionInfo.put("X-Minecraft-UUID", session.getProfile().getId().toString());
        sessionInfo.put("X-Minecraft-Version", getVersion().version);
        return sessionInfo;
    }

    public File getMinecraftDirectory() {
        return MinecraftClient.getInstance().runDirectory;
    }
}