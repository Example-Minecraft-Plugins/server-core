package me.davipccunha.tests.core.scoreboard.handler;

import fr.mrmicky.fastboard.FastBoard;
import lombok.RequiredArgsConstructor;
import me.davipccunha.tests.core.ServerCorePlugin;
import me.davipccunha.tests.core.scoreboard.SurvivalBoard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class SurvivalBoardHandler {
    private final ServerCorePlugin plugin;
    private final Map<String, SurvivalBoard> scoreboards = new HashMap<>();

    public void addBoard(String name) {
        final Player player = Bukkit.getPlayer(name);
        if (player == null) return;
        this.scoreboards.put(name, new SurvivalBoard(this.plugin, player));
    }

    public void removeBoard(String name) {
        this.scoreboards.remove(name);
    }

    public void start() {
        Bukkit.getScheduler().runTaskTimer(this.plugin, () -> {
            for (SurvivalBoard board : this.scoreboards.values()) {
                board.updateLines();
            }
        }, 0, 20);
    }
}
