package me.dreamhopping.pml.api.test

import me.dreamhopping.pml.api.bindings.client.Minecraft
import me.dreamhopping.pml.api.events.bus.EventBus
import me.dreamhopping.pml.api.events.bus.EventListener
import me.dreamhopping.pml.api.events.impl.client.chat.ClientChatReceivedEvent
import me.dreamhopping.pml.api.events.impl.client.chat.ClientChatSentEvent
import me.dreamhopping.pml.api.events.impl.client.gui.ClientRenderGameOverlayEvent
import me.dreamhopping.pml.api.events.impl.client.net.ClientDisconnectedEvent
import me.dreamhopping.pml.api.events.impl.client.net.ClientJoinServerEvent
import me.dreamhopping.pml.api.events.impl.client.player.ClientItemDropEvent
import me.dreamhopping.pml.api.events.impl.client.player.ClientPlayerRespawnEvent
import me.dreamhopping.pml.api.events.impl.client.tick.ClientTickEvent
import me.dreamhopping.pml.mods.Mod
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.net.InetSocketAddress

class PAPITestMod : Mod {
    private val logger: Logger = LogManager.getLogger("PAPITestMod")
    private lateinit var mc: Minecraft

    override fun initialize() {
        EventBus.INSTANCE.register(this)

        logger.info("Hello world! PAPITestMod is now awake")
        mc = Minecraft.getMinecraft()
    }

    override fun unload() {}

    @EventListener
    fun onChatReceived(event: ClientChatReceivedEvent) {
        logger.info("TestMod received a chat message: " + event.message)
    }

    @EventListener
    fun onChatSent(event: ClientChatSentEvent) {
        logger.info("Client sent message to server: " + event.message)
    }

    @EventListener
    fun onItemDrop(event: ClientItemDropEvent) {
        logger.info("The client has dropped an item!")
    }

    @EventListener
    fun onPlayerRespawn(event: ClientPlayerRespawnEvent) {
        logger.info("The player has respawned!")
    }

    @EventListener
    fun onJoinServer(event: ClientJoinServerEvent) {
        logger.info("The client has connected to a server! " + if (event.isLocal) "It is a local server! (singleplayer)" else "It is an external server (multiplayer) Address: " + (event.address as InetSocketAddress).hostString)
    }

    @EventListener
    fun onDisconnect(event: ClientDisconnectedEvent) {
        logger.info("The client has been disconnected from the server. Reason: " + event.reason)
    }

    @EventListener
    fun onTick(event: ClientTickEvent) {
        // WARNING: Do not uncomment this unless you want some sweet sweet log spam
        // logger.info("Tick!");
    }

    @EventListener
    fun onRenderTick(event: ClientRenderGameOverlayEvent) {
        mc.fontRenderer.drawString("PufferfishModLoader TestMod", 5f, 5f, -1, true)
        mc.fontRenderer.drawString(
            "Username: " + mc.sessionInfo["X-Minecraft-Username"],
            5f,
            15f,
            -1,
            true
        )
        mc.fontRenderer.drawString("MC Version: " + mc.version.version, 5f, 25f, -1, true)
        mc.fontRenderer.drawString("FPS: " + mc.fps, 5f, 35f, -1, true)
        mc.fontRenderer.drawString("Paused? " + if (mc.isGamePaused) "yes" else "no", 5f, 45f, -1, true)
    }
}