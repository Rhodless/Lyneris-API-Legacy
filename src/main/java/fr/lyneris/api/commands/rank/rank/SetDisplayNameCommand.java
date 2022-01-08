package fr.lyneris.api.commands.rank.rank;

import fr.lyneris.api.API;
import fr.lyneris.api.commands.rank.RankCommand;
import fr.lyneris.api.manager.RankManager;
import fr.lyneris.api.rank.Rank;
import fr.lyneris.api.utils.CC;
import org.bukkit.command.CommandSender;

public class SetDisplayNameCommand {
    public SetDisplayNameCommand(CommandSender sender, String[] args) {

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

        rank.setDisplayName(args[2]);

        sender.sendMessage("§aLe nouveau display name du grade " + rankName + " est " + CC.translate(args[2]));

    }
}
