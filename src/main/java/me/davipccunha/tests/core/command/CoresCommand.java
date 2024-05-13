package me.davipccunha.tests.core.command;

import me.davipccunha.utils.messages.ErrorMessages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CoresCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("utils.commands.cores")) {
            sender.sendMessage(ErrorMessages.NO_PERMISSION.getMessage());
            return true;
        }

        final String[] colors = {
                "0 - §0Preto §r| 1 - §1Azul Escuro §r| 2 - §2Verde Escuro §r| 3 - §3Ciano Escuro",
                "4 - §4Vermelho Escuro§r| 5 - §5Roxo Escuro §r| 6 - §6Dourado §r| 7 - §7Cinza",
                "8 - §8Cinza Escuro §r| 9 - §9Azul §r| a - §aVerde §r| b - §bCiano",
                "c - §cVermelho §r| d - §dRoxo Claro §r| e - §eAmarelo §r| f - §fBranco"
        };

        sender.sendMessage(colors);

        return true;
    }
}
