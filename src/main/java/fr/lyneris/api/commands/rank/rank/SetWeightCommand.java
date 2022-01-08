package fr.lyneris.api.commands.rank.rank;

import fr.lyneris.api.API;
import fr.lyneris.api.commands.rank.RankCommand;
import fr.lyneris.api.manager.RankManager;
import fr.lyneris.api.rank.Rank;
import fr.lyneris.api.utils.CC;
import org.bukkit.command.CommandSender;

public class SetWeightCommand {
    public SetWeightCommand(CommandSender sender, String[] args) {

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

        int i = 0;

        try {
            i = Integer.parseInt(args[2]);
        } catch (Exception e) {
            RankCommand.sendHelpMessage(sender);
            return;
        }

        rank.setWeight(i);

        sender.sendMessage("§aLe nouveau weight du grade " + rankName + " est " + i);

    }
}
