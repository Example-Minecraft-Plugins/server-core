package me.davipccunha.tests.core.scoreboard;

import fr.mrmicky.fastboard.FastBoard;
import me.davipccunha.tests.core.ServerCorePlugin;
import me.davipccunha.tests.economy.api.EconomyType;
import me.davipccunha.tests.economy.api.util.EconomyFormatter;
import me.davipccunha.utils.player.PermissionUtils;
import org.bukkit.entity.Player;

public class SurvivalBoard extends FastBoard {
    private final ServerCorePlugin plugin;

    public SurvivalBoard(ServerCorePlugin plugin, Player player) {
        super(player);

        this.plugin = plugin;

        this.updateTitle("§e§lMundo Aberto");

    }

    public void updateLines() {
        final String name = this.getPlayer().getName();
        final String prefix = PermissionUtils.getLuckPermsPrefix(this.getPlayer());
        final double coins = this.plugin.getEconomyAPI().getBalance(name.toLowerCase(), EconomyType.COINS);
        final double cash = this.plugin.getEconomyAPI().getBalance(name.toLowerCase(), EconomyType.CASH);

        final int online = this.plugin.getServer().getOnlinePlayers().size();
        final String ip = "jogar.pluncky.com";

        this.updateLines(
                " ",
                "§7Nick: §f" + name,
                "§7Grupo: " + prefix.substring(0, Math.min(prefix.length(), 21)),
                " ",
                "§7Coins: §a" + EconomyFormatter.suffixFormat(coins),
                "§7Cash: §6$" + EconomyFormatter.suffixFormat(cash),
                " ",
                "§7Clã: §f" + "Nenhum",
                " ",
                "§7Online: §f" + online,
                "§7IP: §f" + ip
        );
    }
}
