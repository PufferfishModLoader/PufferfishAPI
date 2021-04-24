package me.dreamhopping.pml.api.impl.minecraft;

import me.dreamhopping.pml.api.bindings.client.Minecraft;
import me.dreamhopping.pml.mods.core.MCVersion;

import java.util.Map;

public class MinecraftImpl implements Minecraft {
    public MCVersion getVersion() {
        return MCVersion.V1_8_9;
    }

    public boolean is64Bit() {
        return net.minecraft.client.Minecraft.getMinecraft().isJava64bit();
    }

    public boolean isGamePaused() {
        return net.minecraft.client.Minecraft.getMinecraft().isGamePaused();
    }

    public int getFps() {
        return net.minecraft.client.Minecraft.getDebugFPS();
    }

    public Map<String, String> getSessionInfo() {
        return net.minecraft.client.Minecraft.getSessionInfo();
    }
}
