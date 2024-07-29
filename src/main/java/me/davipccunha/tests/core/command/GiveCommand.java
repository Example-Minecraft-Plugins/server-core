package me.davipccunha.tests.core.command;

import me.davipccunha.utils.inventory.InventoryUtil;
import me.davipccunha.utils.item.ItemName;
import me.davipccunha.utils.messages.ErrorMessages;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class GiveCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("essentials.give")) {
            sender.sendMessage(ErrorMessages.NO_PERMISSION.getMessage());
            return false;
        }

        if (args.length < 2) {
            sender.sendMessage("§cUso: /give <jogador> <id[:data] | nome[:data]> [quantidade]");
            return false;
        }

        final String playerName = args[0];
        final Player player = Bukkit.getPlayer(playerName);
        if (player == null) {
            sender.sendMessage(ErrorMessages.PLAYER_NOT_ONLINE.getMessage());
            return false;
        }

        final String[] item = args[1].split(":");
        final int id = NumberUtils.toInt(item[0], -1);
        final short data = item.length > 1 ? NumberUtils.toShort(item[1], (short) 0) : 0;
        final int amount = args.length > 2 ? NumberUtils.toInt(args[2], 1) : 1;

        if (amount <= 0 || amount > 2304) {
            sender.sendMessage(ErrorMessages.INVALID_AMOUNT.getMessage());
            return false;
        }

        final ItemStack itemStack = id == -1 ? getItem(item[0], data, amount) : getItem(id, data, amount);

        if (itemStack == null) {
            sender.sendMessage(ErrorMessages.INVALID_ITEM.getMessage());
            return false;
        }

        final int missingAmount = InventoryUtil.getMissingAmount(player.getInventory(), itemStack);

        if (missingAmount >= amount) {
            player.getInventory().addItem(itemStack);
            sender.sendMessage(String.format("§aDado %d %s para %s.", amount, ItemName.valueOf(itemStack), player.getName()));
        } else {
            sender.sendMessage(ErrorMessages.INVENTORY_FULL.getMessage());
        }

        return true;
    }

    @SuppressWarnings("deprecation")
    private @Nullable ItemStack getItem(int id, short data, int amount) {
        final ItemStack itemStack = new ItemStack(id, amount, data);
        return itemStack.getType() == null || itemStack.getType() == Material.AIR ? null : itemStack;
    }

    private @Nullable ItemStack getItem(String name, short data, int amount) {
        final Material material = Material.getMaterial(name.toUpperCase());
        if (material == null) return null;

        final ItemStack itemStack = new ItemStack(material, amount, data);
        return itemStack.getType() == null || itemStack.getType() == Material.AIR ? null : itemStack;
    }
}
