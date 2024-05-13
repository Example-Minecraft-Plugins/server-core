package me.davipccunha.tests.core.listener;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.HashMap;

public class PlayerGenerateItemListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    private void onBlockBreak(BlockBreakEvent event) {

        final Player player = event.getPlayer();
        final Block block = event.getBlock();

        if (player == null || block == null) return;
        if (player.getGameMode().equals(GameMode.CREATIVE)) return;

        final Collection<ItemStack> drops = block.getDrops(player.getItemInHand());
        final Location source = block.getLocation();

        this.handleItemAdd(player, drops, source);

        block.setType(Material.AIR);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    private void onEntityDeath(EntityDeathEvent event) {
        final Player player = event.getEntity().getKiller();
        if (player == null) return;

        final Collection<ItemStack> drops = event.getDrops();
        final Location source = event.getEntity().getLocation();

        this.handleItemAdd(player, drops, source);

        event.getDrops().clear();
    }

    private void handleItemAdd(Player player, Collection<ItemStack> drops, Location source) {
        for (ItemStack drop : drops) {
            HashMap<Integer, ItemStack> remainingItems = player.getInventory().addItem(drop);
            for (ItemStack remainingItem : remainingItems.values()) {
                player.getWorld().dropItemNaturally(source, remainingItem);
            }
        }
    }
}
