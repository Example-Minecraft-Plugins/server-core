package me.davipccunha.tests.core.listener;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChatListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    private void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        String message = ChatColor.translateAlternateColorCodes('&', event.getMessage());
        event.setMessage(message);
    }
}
