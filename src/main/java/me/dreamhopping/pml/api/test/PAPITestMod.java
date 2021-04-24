package me.dreamhopping.pml.api.test;

import me.dreamhopping.pml.api.bindings.client.Minecraft;
import me.dreamhopping.pml.api.events.bus.EventBus;
import me.dreamhopping.pml.api.events.bus.EventListener;
import me.dreamhopping.pml.api.events.impl.client.chat.ClientChatReceivedEvent;
import me.dreamhopping.pml.api.events.impl.client.chat.ClientChatSentEvent;
import me.dreamhopping.pml.api.events.impl.client.gui.ClientRenderGameOverlayEvent;
import me.dreamhopping.pml.api.events.impl.client.net.ClientDisconnectedEvent;
import me.dreamhopping.pml.api.events.impl.client.net.ClientJoinServerEvent;
import me.dreamhopping.pml.api.events.impl.client.player.ClientItemDropEvent;
import me.dreamhopping.pml.api.events.impl.client.player.ClientPlayerRespawnEvent;
import me.dreamhopping.pml.api.events.impl.client.tick.ClientTickEvent;
import me.dreamhopping.pml.mods.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetSocketAddress;

public class PAPITestMod implements Mod {
    public final Logger logger = LogManager.getLogger("PAPITestMod");
    private Minecraft mc;

    public void initialize() {
        EventBus.INSTANCE.register(this);

        logger.info("Hello world! PAPITestMod is now awake");
        mc = Minecraft.getInstance();
    }

    public void unload() {

    }

    @EventListener
    public void onChatReceived(ClientChatReceivedEvent event) {
        logger.info("TestMod received a chat message: " + event.message);
    }

    @EventListener
    public void onChatSent(ClientChatSentEvent event) {
        logger.info("Client sent message to server: " + event.message);
    }

    @EventListener
    public void onItemDrop(ClientItemDropEvent event) {
        logger.info("The client has dropped an item!");
    }

    @EventListener
    public void onPlayerRespawn(ClientPlayerRespawnEvent event) {
        logger.info("The player has respawned!");
    }

    @EventListener
    public void onJoinServer(ClientJoinServerEvent event) {
        logger.info("The client has connected to a server! " + (event.isLocal ? "It is a local server! (singleplayer)" : "It is an external server (multiplayer) Address: " + ((InetSocketAddress) event.address).getHostString()));
    }

    @EventListener
    public void onDisconnect(ClientDisconnectedEvent event) {
        logger.info("The client has been disconnected from the server. Reason: " + event.reason);
    }

    @EventListener
    public void onTick(ClientTickEvent event) {
        // WARNING: Do not uncomment this unless you want some sweet sweet log spam
        // logger.info("Tick!");
    }

    @EventListener
    public void onRenderTick(ClientRenderGameOverlayEvent event) {
        mc.getFontRenderer().drawString("PufferfishModLoader TestMod", 5, 5, -1, true);
        mc.getFontRenderer().drawString("Username: " + mc.getSessionInfo().get("X-Minecraft-Username"), 5, 15, -1, true);
        mc.getFontRenderer().drawString("MC Version: " + mc.getVersion().getVersion(), 5, 25, -1, true);
        mc.getFontRenderer().drawString("FPS: " + mc.getFPS(), 5, 35, -1, true);
        mc.getFontRenderer().drawString("Paused? " + (mc.isGamePaused() ? "yes" : "no"), 5, 45, -1, true);
    }
}
