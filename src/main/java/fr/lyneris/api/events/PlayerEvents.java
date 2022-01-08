package fr.lyneris.api.events;

import fr.lyneris.api.API;
import fr.lyneris.api.rank.Rank;
import fr.lyneris.api.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.permissions.PermissionAttachment;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class PlayerEvents implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {

        Player player = event.getPlayer();

        final List<String> permissions = new ArrayList<>();
        final List<String> blacklisted = new ArrayList<>();

        Bukkit.getScheduler().runTaskAsynchronously(API.getAPI(), () -> {
            Rank rank = API.getAPI().getManager().getRank(player);

            permissions.addAll(rank.getPermissions());
            blacklisted.addAll(rank.getBlacklistedPermissions());

        });

        PermissionAttachment pa = player.addAttachment(API.getAPI());
        permissions.forEach(s -> pa.setPermission(s, true));
        blacklisted.forEach(s -> pa.setPermission(s, false));

    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        String message = event.getMessage();

        Rank rank = API.getAPI().getManager().getRank(player);
        String prefix = CC.translate(rank.getPrefix());
        if (message.contains("%")) {
            player.sendMessage("§7▎ §cCe caractère n'est pas autorisé.");
            event.setCancelled(true);
            return;
        }

        if (rank.getName().equals("joueur")) {
            event.setFormat(prefix + "%s §8» §7" + message);
        } else {
            event.setFormat(prefix + " %s §8» §f" + message);
        }

    }

}
