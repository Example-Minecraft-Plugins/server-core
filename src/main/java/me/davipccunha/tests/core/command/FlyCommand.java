package me.davipccunha.tests.core.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cApenas jogadores podem executar este comando.");
            return false;
        }

        final Player player = (Player) sender;

        if (!player.hasPermission("essentials.fly")) {
            player.sendMessage("§cVocê não tem permissão para executar este comando.");
            return false;
        }

        final boolean currentAllowFlight = player.getAllowFlight();

        player.setAllowFlight(!currentAllowFlight);
        player.sendMessage("§aModo voo " + (currentAllowFlight ? "§cdesativado§a" : "ativado") + " com sucesso.");

        return true;
    }
}
