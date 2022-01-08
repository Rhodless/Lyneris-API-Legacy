package fr.lyneris.api.commands.rank.rank;

import fr.lyneris.api.API;
import fr.lyneris.api.commands.rank.RankCommand;
import fr.lyneris.api.manager.RankManager;
import fr.lyneris.api.rank.Rank;
import org.bukkit.command.CommandSender;

public class PermissionCommand {
    public PermissionCommand(CommandSender sender, String[] args) {

        if(args.length < 4) {
            RankCommand.sendHelpMessage(sender);
            return;
        }

        String rankName = args[2];

        if(!RankManager.rankExists(rankName)) {
            sender.sendMessage("§cCe grade n'existe pas. Utilisez /rank create <nom> pour en créer un.");
            return;
        }

        Rank rank = API.getAPI().getManager().getRank(rankName);

        if(args[1].equalsIgnoreCase("set")) {

            String permission = args[3];

            if(args.length != 5) {
                RankCommand.sendHelpMessage(sender);
                return;
            }

            if(!(args[4].equalsIgnoreCase("true") || args[4].equalsIgnoreCase("false"))) {
                RankCommand.sendHelpMessage(sender);
                return;
            }

            boolean value = Boolean.parseBoolean(args[4]);

            if(value) {
                rank.removeBlacklistedPermission(permission);
                rank.addPermission(permission);
                sender.sendMessage("§aLa permission \"" + args[3] + "\" a été set à true.");
            } else {
                sender.sendMessage("§cLa permission \"" + args[3] + "\" a été set à false.");
                rank.removePermission(permission);
                rank.addBlacklistedPermission(permission);
            }

        } else if(args[1].equalsIgnoreCase("unset")) {

            rank.removePermission(args[3]);
            rank.removeBlacklistedPermission(args[3]);

            sender.sendMessage("§cLa permission \"" + args[3] + "\" a été unset.");

        }

    }
}
