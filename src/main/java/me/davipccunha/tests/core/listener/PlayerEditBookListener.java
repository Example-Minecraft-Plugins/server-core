package me.davipccunha.tests.core.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;

public class PlayerEditBookListener implements Listener {

    @EventHandler
    private void onPlayerEditBook(PlayerEditBookEvent event) {
        event.setCancelled(true);
    }
}
