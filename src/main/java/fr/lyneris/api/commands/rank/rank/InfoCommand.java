package fr.lyneris.api.commands.rank.rank;

import fr.lyneris.api.API;
import fr.lyneris.api.commands.rank.RankCommand;
import fr.lyneris.api.manager.RankManager;
import fr.lyneris.api.rank.Rank;
import fr.lyneris.api.utils.CC;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

public class InfoCommand {
    public InfoCommand(CommandSender sender, String[] args) {

        if(args.length != 2) {
            RankCommand.sendHelpMessage(sender);
            return;
        }

        String rankName = args[1];

        if(!RankManager.rankExists(rankName)) {
            sender.sendMessage("§cCe rank n'existe pas. Utilisez /rank create <nom> pour en créer un.");
            return;
        }

        Rank rank = API.getAPI().getManager().getRank(rankName);

        sender.sendMessage("§8§m------------------------------------");
        sender.sendMessage(" §3Nom: §r" + rank.getName());
        sender.sendMessage(" §3Display Name: §r" + CC.translate(rank.getDisplayName()));
        sender.sendMessage(" §3Prefix: §r" + CC.translate(rank.getPrefix()));
        sender.sendMessage(" §3Weight: §r" + rank.getWeight());
        sender.sendMessage(" §3Op: §r" + rank.isOp());
        if(rank.getPermissions().isEmpty() && rank.getBlacklistedPermissions().isEmpty()) {
            sender.sendMessage(" §3Permission(s): §cAucune");
        } else {
            sender.sendMessage(" §3Permission(s): §8(§a" + rank.getPermissions().size() + "§8)");
            rank.getPermissions().forEach(s -> {
                sender.sendMessage("  §a" + s);
            });
            rank.getBlacklistedPermissions().forEach(s -> {
                sender.sendMessage("  §c" + s);
            });
        }

        sender.sendMessage("§8§m------------------------------------");

    }
}
