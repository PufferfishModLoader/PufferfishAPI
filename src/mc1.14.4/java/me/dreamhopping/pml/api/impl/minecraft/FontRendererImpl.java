package me.dreamhopping.pml.api.impl.minecraft;

import me.dreamhopping.pml.api.bindings.client.gui.FontRenderer;
import net.minecraft.client.MinecraftClient;

import java.util.List;

public class FontRendererImpl extends FontRenderer {
    public int drawString(String text, float x, float y, int color, boolean drawShadow) {
        if (!drawShadow) {
            return MinecraftClient.getInstance().textRenderer.draw(text, x, y, color);
        } else {
            return MinecraftClient.getInstance().textRenderer.drawWithShadow(text, x, y, color);
        }
    }

    public int drawString(String text, int x, int y, int color) {
        return MinecraftClient.getInstance().textRenderer.draw(text, x, y, color);
    }

    public int drawStringWithShadow(String text, float x, float y, int color) {
        return MinecraftClient.getInstance().textRenderer.drawWithShadow(text, x, y, color);
    }

    public void drawSplitString(String text, int x, int y, int width, int color) {
        MinecraftClient.getInstance().textRenderer.drawTrimmed(text, x, y, color, width);
    }

    public boolean getUnicodeFlag() {
        return true;
    }

    public void setUnicodeFlag(boolean unicodeFlag) {
        // Do nothing
    }

    public int getStringWidth(String string) {
        return MinecraftClient.getInstance().textRenderer.getStringWidth(string);
    }

    public int getCharWidth(char character) {
        return getStringWidth(String.valueOf(character));
    }

    public int splitStringWidth(String string, int width) {
        return MinecraftClient.getInstance().textRenderer.fontHeight * this.listFormattedStringToWidth(string, width).size();
    }

    public List<String> listFormattedStringToWidth(String string, int width) {
        return MinecraftClient.getInstance().textRenderer.wrapStringToWidthAsList(string, width);
    }

    public String trimStringToWidth(String string, int width) {
        return MinecraftClient.getInstance().textRenderer.trimToWidth(string, width);
    }

    public String trimStringToWidth(String string, int width, boolean var2) {
        return MinecraftClient.getInstance().textRenderer.trimToWidth(string, width, var2);
    }
}