package fr.lyneris.api.commands.rank.rank;

import fr.lyneris.api.API;
import fr.lyneris.api.commands.rank.RankCommand;
import fr.lyneris.api.manager.RankManager;
import fr.lyneris.api.rank.Rank;
import fr.lyneris.api.utils.CC;
import org.bukkit.command.CommandSender;

public class SetOpCommand {
    public SetOpCommand(CommandSender sender, String[] args) {

        if(args.length != 3) {
            RankCommand.sendHelpMessage(sender);
            return;
        }

        String rankName = args[1];

        if(!RankManager.rankExists(rankName)) {
            sender.sendMessage("§cCe rank n'existe pas. Utilisez /rank create <nom> pour en créer un.");
            return;
        }

        Rank rank = API.getAPI().getManager().getRank(rankName);

        if(!(args[2].equalsIgnoreCase("true") || args[2].equalsIgnoreCase("false"))) {
            RankCommand.sendHelpMessage(sender);
            return;
        }

        rank.setOp(Boolean.parseBoolean(args[2]));

        sender.sendMessage("§aLe grade " + rankName + " a maintenant comme valeur pour \"op\" \"" + args[2] + "\"");

    }
}
