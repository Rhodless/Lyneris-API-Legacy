package fr.lyneris.api.commands.rank.rank;

import fr.lyneris.api.API;
import fr.lyneris.api.commands.rank.RankCommand;
import fr.lyneris.api.manager.RankManager;
import fr.lyneris.api.rank.Rank;
import org.bukkit.command.CommandSender;

public class CreateCommand {
    public CreateCommand(CommandSender sender, String[] args) {
        if(args.length != 2) {
            RankCommand.sendHelpMessage(sender);
            return;
        }

        String rankName = args[1];

        if(RankManager.rankExists(rankName)) {
            sender.sendMessage("§cCe grade existe déjà.");
            return;
        }

        Rank rank = API.getAPI().getManager().getRank(rankName);

        rank.createRank();
        sender.sendMessage("§aLe grade " + rank.getName() + " a été créé avec succès.");

    }
}
