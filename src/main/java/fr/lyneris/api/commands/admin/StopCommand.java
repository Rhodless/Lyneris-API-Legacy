package fr.lyneris.api.commands.admin;

import fr.lyneris.api.API;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(args.length == 1 && args[0].equalsIgnoreCase("confirm")) {
            API.getAPI().getServer().shutdown();
            sender.sendMessage("§cFermeture du serveur...");
            return true;
        }

        sender.sendMessage("§cUtilisez /stop confirm pour confirmer la fermeture du serveur.");

        return false;
    }
}
