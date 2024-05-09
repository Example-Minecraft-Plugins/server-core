package me.davipccunha.tests.core.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EntityExplodeListener implements Listener {

    @EventHandler
    private void onEntityExplode(EntityExplodeEvent event) {
        event.setCancelled(true);
    }
}
