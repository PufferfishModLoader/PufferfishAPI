package me.dreamhopping.pml.api.bindings.client.impl;

import com.google.common.collect.Maps;
import me.dreamhopping.pml.api.bindings.client.Minecraft;
import me.dreamhopping.pml.mods.core.MCVersion;
import net.minecraft.util.Session;

import java.io.File;
import java.util.Map;

public class MinecraftImpl extends Minecraft {
    public MCVersion getVersion() {
        return MCVersion.V1_10_2;
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
        final Session session = net.minecraft.client.Minecraft.getMinecraft().getSession();

        Map<String, String> sessionInfo = Maps.newHashMap();
        sessionInfo.put("X-Minecraft-Username", session.getUsername());
        sessionInfo.put("X-Minecraft-UUID", session.getPlayerID());
        sessionInfo.put("X-Minecraft-Version", getVersion().version);
        return sessionInfo;
    }

    public File getMinecraftDirectory() {
        return net.minecraft.client.Minecraft.getMinecraft().mcDataDir;
    }
}