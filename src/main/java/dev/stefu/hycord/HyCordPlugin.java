package dev.stefu.hycord;

import com.hypixel.hytale.server.core.event.events.ShutdownEvent;
import com.hypixel.hytale.server.core.event.events.player.PlayerChatEvent;
import com.hypixel.hytale.server.core.event.events.player.PlayerDisconnectEvent;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.util.Config;

import javax.annotation.Nonnull;

public class HyCordPlugin extends JavaPlugin {
    public static Config<HyCordConfig> cfg = null;

    public HyCordPlugin(@Nonnull JavaPluginInit init) {
        super(init);
        cfg = withConfig("HyCordPlugin", HyCordConfig.CODEC);
    }

    @Override
    protected void setup() {
        cfg.save();

        GameEvents.onStartEvent();
        this.getEventRegistry().registerGlobal(ShutdownEvent.class, GameEvents::onShutdownEvent);
        this.getEventRegistry().registerGlobal(PlayerReadyEvent.class, GameEvents::onPlayerReady);
        this.getEventRegistry().registerGlobal(PlayerDisconnectEvent.class, GameEvents::onPlayerDisconnected);
        this.getEventRegistry().registerGlobal(PlayerChatEvent.class, GameEvents::onPlayerChatEvent);
    }
}
