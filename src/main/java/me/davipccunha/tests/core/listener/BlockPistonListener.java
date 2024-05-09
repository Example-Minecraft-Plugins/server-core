package me.davipccunha.tests.core.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;

public class BlockPistonListener implements Listener {

    @EventHandler
    private void onBlockPistonRetract(BlockPistonRetractEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    private void onBlockPistonExtend(BlockPistonExtendEvent event) {
        event.setCancelled(true);
    }
}
