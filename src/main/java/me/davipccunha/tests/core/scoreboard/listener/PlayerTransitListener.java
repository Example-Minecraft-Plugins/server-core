package me.davipccunha.tests.core.scoreboard.listener;

import lombok.RequiredArgsConstructor;
import me.davipccunha.tests.core.ServerCorePlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@RequiredArgsConstructor
public class PlayerTransitListener implements Listener {
    private final ServerCorePlugin plugin;

    @EventHandler(priority = EventPriority.MONITOR)
    private void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player == null) return;

        plugin.getSurvivalBoardHandler().addBoard(player.getName());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    private void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (player == null) return;

        plugin.getSurvivalBoardHandler().removeBoard(player.getName());
    }
}
