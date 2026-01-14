package dev.stefu.hycord;

import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.event.events.ShutdownEvent;
import com.hypixel.hytale.server.core.event.events.player.PlayerChatEvent;
import com.hypixel.hytale.server.core.event.events.player.PlayerDisconnectEvent;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;

import java.util.Objects;

public class GameEvents {
    public static void onStartEvent() {
        HyCordConfig.EventConfig eventConfig = HyCordPlugin.cfg.get().events.get("OnServerStart");

        if (!eventConfig.enabled) return;

        String webhook = "";

        if (!Objects.equals(eventConfig.customWebhook, "")) {
            webhook = eventConfig.customWebhook;
        } else {
            webhook = HyCordPlugin.cfg.get().webhook;
        }

        DiscordWebhook.DiscordMessage message = new DiscordWebhook.DiscordMessage()
                .addEmbed(new DiscordWebhook.Embed()
                        .setTitle(eventConfig.title)
                        .setDescription(eventConfig.message)
                        .setThumbnail(eventConfig.thumbnail)
                        .setColor(eventConfig.color));

        DiscordWebhook.send(webhook, message);
    }

    public static void onShutdownEvent(ShutdownEvent event) {
        HyCordConfig.EventConfig eventConfig = HyCordPlugin.cfg.get().events.get("OnServerStop");

        if (!eventConfig.enabled) return;

        String webhook = "";

        if (!Objects.equals(eventConfig.customWebhook, "")) {
            webhook = eventConfig.customWebhook;
        } else {
            webhook = HyCordPlugin.cfg.get().webhook;
        }

        DiscordWebhook.DiscordMessage message = new DiscordWebhook.DiscordMessage()
                .addEmbed(new DiscordWebhook.Embed()
                        .setTitle(eventConfig.title)
                        .setDescription(eventConfig.message)
                        .setThumbnail(eventConfig.thumbnail)
                        .setColor(eventConfig.color));

        DiscordWebhook.send(webhook, message);
    }

    public static void onPlayerReady(PlayerReadyEvent event) {
        HyCordConfig.EventConfig eventConfig = HyCordPlugin.cfg.get().events.get("OnPlayerJoin");

        if (!eventConfig.enabled) return;

        Player player = event.getPlayer();

        String webhook = "";

        if (!Objects.equals(eventConfig.customWebhook, "")) {
            webhook = eventConfig.customWebhook;
        } else {
            webhook = HyCordPlugin.cfg.get().webhook;
        }

        DiscordWebhook.DiscordMessage message = new DiscordWebhook.DiscordMessage()
                .addEmbed(new DiscordWebhook.Embed()
                        .setTitle(eventConfig.title)
                        .setDescription(eventConfig.message.replace("{player}", player.getDisplayName()))
                        .setThumbnail(eventConfig.thumbnail)
                        .setColor(eventConfig.color));

        DiscordWebhook.send(webhook, message);
    }

    public static void onPlayerDisconnected(PlayerDisconnectEvent event) {
        if (event.getDisconnectReason().getClientDisconnectType() == null) {
            return;
        }

        HyCordConfig.EventConfig eventConfig = HyCordPlugin.cfg.get().events.get("OnPlayerLeft");

        if (!eventConfig.enabled) return;

        String username = event.getPlayerRef().getUsername();

        String webhook = "";

        if (!Objects.equals(eventConfig.customWebhook, "")) {
            webhook = eventConfig.customWebhook;
        } else {
            webhook = HyCordPlugin.cfg.get().webhook;
        }

        DiscordWebhook.DiscordMessage message = new DiscordWebhook.DiscordMessage()
                .addEmbed(new DiscordWebhook.Embed()
                        .setTitle(eventConfig.title)
                        .setDescription(eventConfig.message.replace("{player}", username))
                        .setThumbnail(eventConfig.thumbnail)
                        .setColor(eventConfig.color));

        DiscordWebhook.send(webhook, message);
    }

    public static void onPlayerChatEvent(PlayerChatEvent event) {
        HyCordConfig.EventConfig eventConfig = HyCordPlugin.cfg.get().events.get("OnPlayerChat");

        if (!eventConfig.enabled) return;

        String username = event.getSender().getUsername();
        String chatMessage = event.getContent();

        String webhook = "";

        if (!Objects.equals(eventConfig.customWebhook, "")) {
            webhook = eventConfig.customWebhook;
        } else {
            webhook = HyCordPlugin.cfg.get().webhook;
        }

        DiscordWebhook.DiscordMessage message = new DiscordWebhook.DiscordMessage()
                .addEmbed(new DiscordWebhook.Embed()
                        .setTitle(eventConfig.title)
                        .setDescription(eventConfig.message.replace("{sender}", username).replace("{message}", chatMessage))
                        .setThumbnail(eventConfig.thumbnail)
                        .setColor(eventConfig.color));

        DiscordWebhook.send(webhook, message);
    }
}
