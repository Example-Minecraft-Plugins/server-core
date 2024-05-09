package me.davipccunha.tests.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cApenas jogadores podem executar este comando.");
            return true;
        }

        if (args.length < 1) {
            Bukkit.dispatchCommand(sender, "spawn " + sender.getName());
            return true;
        }

        if (!sender.hasPermission("essentials.forcespawn")) {
            sender.sendMessage("§cVocê não tem permissão para executar este comando.");
            return true;
        }

        final String name = args[0];
        final Player player = Bukkit.getPlayer(name);
        if (player == null) {
            sender.sendMessage("§cUsuário não encontrado.");
            return true;
        }

        player.teleport(player.getWorld().getSpawnLocation());

        player.sendMessage("§aTeleportado com sucesso para o spawn.");

        return true;
    }
}
