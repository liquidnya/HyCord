package dev.stefu.hycord;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.codec.codecs.map.MapCodec;

import java.util.HashMap;
import java.util.Map;

public class HyCordConfig {
    public static class EventConfig {
        public static final BuilderCodec<EventConfig> CODEC = BuilderCodec.builder(EventConfig.class, EventConfig::new)
                .append(new KeyedCodec("Title", Codec.STRING), (i, v) -> i.title = v, i -> i.title).add()
                .append(new KeyedCodec("Message", Codec.STRING), (i, v) -> ((EventConfig)i).message = v.toString(), i -> ((EventConfig)i).message).add()
                .append(new KeyedCodec("Thumbnail", Codec.STRING, false), (i, v) -> ((EventConfig)i).thumbnail = v.toString(), i -> ((EventConfig)i).thumbnail).add()
                .append(new KeyedCodec("Color", Codec.INTEGER), (i, v) -> ((EventConfig)i).color = (int) v, i -> ((EventConfig)i).color).add()
                .append(new KeyedCodec("CustomWebhook", Codec.STRING, false), (i, v) -> ((EventConfig)i).customWebhook = v.toString(), i -> ((EventConfig)i).customWebhook).add()
                .append(new KeyedCodec("Enabled", Codec.BOOLEAN), (i, v) -> ((EventConfig)i).enabled = (Boolean) v, i -> ((EventConfig)i).enabled).add()
                .build();

        public String title = "";
        public String message = "";
        public String thumbnail = "";
        public int color = 0;
        public String customWebhook = "";
        public Boolean enabled = true;

        public EventConfig() {}

        public EventConfig(String title, String message, String thumbnail, int color, String customWebhook, Boolean enabled) {
            this.title = title;
            this.message = message;
            this.thumbnail = thumbnail;
            this.color = color;
            this.customWebhook = customWebhook;
            this.enabled = enabled;
        }
    }

    public static final BuilderCodec<HyCordConfig> CODEC = BuilderCodec.builder(HyCordConfig.class, HyCordConfig::new)
            .append(new KeyedCodec("Webhook", Codec.STRING, true), (i, v) -> {
                i.webhook = v;
            }, i2 -> {
                return i2.webhook;
            }).add()
            .append(new KeyedCodec("Events", new MapCodec<>(EventConfig.CODEC, HashMap::new), true), (i, v) -> {
                ((HyCordConfig)i).events = (Map<String, EventConfig>) v;
            }, i2 -> {
                return ((HyCordConfig)i2).events;
            }).add()
            .build();

    protected String webhook = "";
    protected Map<String, EventConfig> events = new HashMap<>();

    public HyCordConfig() {
        events.put("OnPlayerJoin", new EventConfig("Player Joined", "{player} has joined the server", "", 3066993, "", true));
        events.put("OnPlayerLeft", new EventConfig("Player left", "{player} has left the server", "", 15105570, "", true));
        events.put("OnPlayerChat", new EventConfig("Chat Message", "{sender}: {message}", "", 3447003, "", true));
        events.put("OnServerStart", new EventConfig("Server Started", "The server is started", "", 3447003, "", true));
        events.put("OnServerStop", new EventConfig("Server Stopped", "The server is shutdown", "", 15548997, "", true));
    }
}