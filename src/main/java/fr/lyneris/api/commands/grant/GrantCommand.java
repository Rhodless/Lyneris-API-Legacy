package fr.lyneris.api.commands.grant;

import fr.lyneris.api.API;
import fr.lyneris.api.manager.RankManager;
import fr.lyneris.api.rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GrantCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(args.length != 2) {
            sender.sendMessage("§cUsage: /grant <joueur> <grade>");
            return true;
        }

        Player player = Bukkit.getPlayer(args[0]);

        if(player == null) {
            sender.sendMessage("§cCe joueur n'est pas connecté.");
            return true;
        }

        String rankName = args[1];

        if(!RankManager.rankExists(rankName)) {
            sender.sendMessage("§cCe grade n'existe pas. Utilisez /rank create <nom> pour en créer un.");
            return true;
        }

        Rank rank = API.getAPI().getManager().getRank(rankName);

        API.getAPI().getManager().getProfile(player).setRank(rank);
        player.kickPlayer("§cVotre grade a été changé. \nReconnectez-vous pour l'avoir.");
        sender.sendMessage("§aLe nouveau grade de " + player.getName() + " est désormais " + rank.getName() + " §8(" + rank.getDisplayName() + "§8)");

        return false;
    }
}
