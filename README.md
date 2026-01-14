# HyCord

**Simple Discord Webhooks for Hytale.**

HyCord is a lightweight plugin that connects your Hytale server activity to Discord in real-time.

---

## ✨ Features
* **Join/Leave Notifications:** Track player activity.
* **Chat Bridge:** Send in-game messages to a Discord channel.
* **Server Status:** Alerts for server start and shutdown.
* **Fully Customizable:** Change colors, titles, and messages.

## ⚙️ Configuration
The configuration file is automatically generated the **first time your world starts**. You can find it in your world save directory at:

`Saves/YourWorld/mods/dev.stefu_HyCord/HyCordPlugin.json`

```json
{
    "Webhook": "https://discord.com/api/webhooks/YOUR_ID/YOUR_TOKEN",
    "Events": {
        "OnPlayerChat": {
            "Title": "Chat Message",
            "Message": "{sender}: {message}",
            "Thumbnail": "https://example.com/image.png",
            "Color": 3447003,
            "CustomWebhook": "",
            "Enabled": true
        },
        "OnPlayerJoin": {
            "Title": "Player Joined",
            "Message": "{player} has joined the server",
            "Thumbnail": "https://example.com/join-icon.png",
            "Color": 3066993,
            "CustomWebhook": "",
            "Enabled": true
        }
    }
}
```

### 🖼️ Using Thumbnails

The "Thumbnail" field allows you to add a small image to the top-right corner of the Discord embed.
* **How to use:** Paste a direct link to an image (ending in .png, .jpg, or .gif).
* **Leave empty:** If you don't want an image, keep it as "".
* **Customization:** You can use different icons for different events (e.g., a green "plus" icon for joins and a chat bubble icon for messages).

### 🎨 Color Code Reference (Decimal)
Use these values in the `"Color"` field of your config to change the embed sidebar:
* **Green:** `3066993` (Success/Join)
* **Blue:** `3447003` (Info/Chat)
* **Red:** `15548997` (Shutdown/Error)
* **Orange:** `15105570` (Leave/Warning)
* **Purple:** `10181046` (Special)

---
**Created by StefuDev**