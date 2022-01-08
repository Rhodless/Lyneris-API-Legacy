package fr.lyneris.api.commands.rank;

import fr.lyneris.api.commands.rank.rank.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Locale;

public class RankCommand implements CommandExecutor {

    public static void sendHelpMessage(CommandSender sender) {
        sender.sendMessage("§8§m------------------------------------");
        sender.sendMessage("§f§l» §e/rank create <rank>");
        sender.sendMessage("§f§l» §e/rank info <rank>");
        sender.sendMessage("§f§l» §e/rank list");
        sender.sendMessage("§f§l» §e/rank permission set <rank> <perm> <value>");
        sender.sendMessage("§f§l» §e/rank permission unset <rank> <perm>");
        sender.sendMessage("§f§l» §e/rank setweight <rank> <value>");
        sender.sendMessage("§f§l» §e/rank setprefix <rank> <value>");
        sender.sendMessage("§f§l» §e/rank setdisplayname <rank> <value>");
        sender.sendMessage("§f§l» §e/rank setop <rank> <value>");
        sender.sendMessage("§8§m------------------------------------");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(args.length == 0) {
            sendHelpMessage(sender);
            return true;
        }

        switch (args[0].toLowerCase(Locale.ROOT)) {
            case "create":
                new CreateCommand(sender, args);
                break;
            case "list":
                new ListCommand(sender, args);
                break;
            case "info":
                new InfoCommand(sender, args);
                break;
            case "permission":
                new PermissionCommand(sender, args);
                break;
            case "setprefix":
                new SetPrefixCommand(sender, args);
                break;
            case "setweight":
                new SetWeightCommand(sender, args);
                break;
            case "setdisplayname":
                new SetDisplayNameCommand(sender, args);
                break;
            case "setop":
                new SetOpCommand(sender, args);
                break;
            default:
                sendHelpMessage(sender);
                break;
        }

        return false;
    }
}
