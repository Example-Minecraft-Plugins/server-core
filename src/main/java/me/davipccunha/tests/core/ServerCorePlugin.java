package me.davipccunha.tests.core;

import lombok.Getter;
import me.davipccunha.tests.core.command.CoresCommand;
import me.davipccunha.tests.core.command.FlyCommand;
import me.davipccunha.tests.core.command.SpawnCommand;
import me.davipccunha.tests.core.listener.*;
import me.davipccunha.tests.core.scoreboard.handler.SurvivalBoardHandler;
import me.davipccunha.tests.core.scoreboard.listener.PlayerTransitListener;
import me.davipccunha.tests.economy.api.EconomyAPI;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class ServerCorePlugin extends JavaPlugin {
    private EconomyAPI economyAPI;
    private final SurvivalBoardHandler survivalBoardHandler = new SurvivalBoardHandler(this);

    @Override
    public void onEnable() {
        this.init();
        getLogger().info("Server Core plugin loaded!");
    }

    public void onDisable() {
        getLogger().info("Server Core plugin unloaded!");
    }

    private void init() {
        saveDefaultConfig();

        this.economyAPI = Bukkit.getServicesManager().load(EconomyAPI.class);

        this.registerListeners(
                new AsyncPlayerChatListener(),
                new BlockPistonListener(),
                new BlockRedstoneListener(),
                new EntityExplodeListener(),
                new FoodLevelChangeListener(),
                new PlayerEditBookListener(),
                new PlayerGenerateItemListener(),
                new PlayerInteractListener(),
                new WeatherChangeListener(),

                new PlayerTransitListener(this)
        );
        this.registerCommands();

        this.loadCaches();

        this.startUpdaters();
    }

    private void registerListeners(Listener... listeners) {
        PluginManager pluginManager = getServer().getPluginManager();
        for (Listener listener : listeners) pluginManager.registerEvents(listener, this);
    }

    private void registerCommands() {
        getCommand("cores").setExecutor(new CoresCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
    }

    private void loadCaches() {
    }

    private void startUpdaters() {
        this.survivalBoardHandler.start();
    }
}
