package me.davipccunha.tests.core.command;

import me.davipccunha.utils.messages.ErrorMessages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ErrorMessages.EXECUTOR_NOT_PLAYER.getMessage());
            return false;
        }

        final Player player = (Player) sender;

        if (!player.hasPermission("essentials.fly")) {
            player.sendMessage(ErrorMessages.NO_PERMISSION.getMessage());
            return false;
        }

        final boolean currentAllowFlight = player.getAllowFlight();

        player.setAllowFlight(!currentAllowFlight);
        player.sendMessage(String.format("§aModo vôo %s §acom sucesso.", currentAllowFlight ? "§cdesativado" : "ativado"));

        return true;
    }
}
