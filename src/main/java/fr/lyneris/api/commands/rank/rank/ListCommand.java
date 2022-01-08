package fr.lyneris.api.commands.rank.rank;

import fr.lyneris.api.API;
import fr.lyneris.api.manager.RankManager;
import fr.lyneris.api.rank.Rank;
import fr.lyneris.api.utils.CC;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class ListCommand {
    public ListCommand(CommandSender sender, String[] args) {
        if(RankManager.getAllRanks().isEmpty()) {
            sender.sendMessage("§cIl n'y a aucun grade. Utilisez /rank create <nom> pour en créer un.");
            return;
        }

        sender.sendMessage("§8§m------------------------------------");
        RankManager.getAllRanks().forEach(s -> {

            Rank rank = API.getAPI().getManager().getRank(s);

            TextComponent component = new TextComponent(" §3" + rank.getName() + " §8(§rHover pour plus d'infos§8");
            component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[] { new TextComponent(
                    "§3Nom: §f" + rank.getName() + "\n" +
                    "§3Display Name: §f" + CC.translate(rank.getDisplayName()) + "\n" +
                    "§3Prefix: §r" + CC.translate(rank.getPrefix()) + "\n" +
                    "§3Weight: §f" + rank.getWeight() + "\n" +
                    "§3Op: §f" + rank.isOp() + "\n" +
                    " " + "\n" +
                    "§3Clique pour plus d'informations"
            )}));
            component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rank info " + rank.getName()));

            if(sender instanceof Player) {
                ((Player) sender).spigot().sendMessage(component);
            } else {
                sender.sendMessage(" §3" + rank.getName());
            }

        });
        sender.sendMessage("§8§m------------------------------------");

    }
}
