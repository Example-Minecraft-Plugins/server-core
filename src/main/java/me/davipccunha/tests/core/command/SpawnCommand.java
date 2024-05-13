package me.davipccunha.tests.core.command;

import me.davipccunha.utils.messages.ErrorMessages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) && args.length < 1) {
            sender.sendMessage("§cUso: /spawn <jogador>");
            return true;
        }

        if (args.length < 1) {
            Bukkit.dispatchCommand(sender, "spawn " + sender.getName());
            return true;
        }

        final String name = args[0].toLowerCase();

        final boolean self = name.equals(sender.getName().toLowerCase());

        if (!self && !sender.hasPermission("essentials.forcespawn")) {
            sender.sendMessage(ErrorMessages.NO_PERMISSION.getMessage());
            return true;
        }

        final Player player = Bukkit.getPlayer(name);

        if (player == null) {
            sender.sendMessage(ErrorMessages.PLAYER_NOT_FOUND.getMessage());
            return true;
        }

        player.teleport(Bukkit.getWorld("spawn").getSpawnLocation().clone().add(0.5, 0, 0.5));

        sender.sendMessage(String.format("§a%s foi teleportado com sucesso para o spawn.", self ? "Você" : player.getName()));

        return true;
    }
}
