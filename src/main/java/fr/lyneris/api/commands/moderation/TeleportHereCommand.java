package fr.lyneris.api.commands.moderation;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportHereCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player)) return true;

        Player player = (Player) sender;

        if(args.length != 1) {
            player.sendMessage("§cUsage: /tphere <joueur>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if(target == null) {
            player.sendMessage("§cCe joueur n'est pas connecté.");
            return true;
        }

        Bukkit.getConsoleSender().sendMessage("§6" + player.getName() + " §ra téléporté à sa position §6" + target.getName());
        target.teleport(player.getLocation());
        player.sendMessage("§aVous avez téléporté à votre position " + target.getName());

        return false;
    }
}
