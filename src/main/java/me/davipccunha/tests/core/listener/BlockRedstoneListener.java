package me.davipccunha.tests.core.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

public class BlockRedstoneListener implements Listener {

    @EventHandler
    private void onBlockRedstone(BlockRedstoneEvent event) {
        event.setNewCurrent(0);
    }
}
