package fr.lyneris.api.utils;

import fr.lyneris.api.API;
import fr.lyneris.api.commands.admin.StopCommand;
import fr.lyneris.api.commands.moderation.TeleportCommand;
import fr.lyneris.api.commands.grant.GrantCommand;
import fr.lyneris.api.commands.moderation.TeleportHereCommand;
import fr.lyneris.api.commands.rank.RankCommand;
import fr.lyneris.api.events.PlayerEvents;
import org.bukkit.plugin.PluginManager;

public class Register {

    public Register(API api) {

        PluginManager pm = api.getServer().getPluginManager();

        pm.registerEvents(new PlayerEvents(), api);

        api.getCommand("rank").setExecutor(new RankCommand());
        api.getCommand("grant").setExecutor(new GrantCommand());
        api.getCommand("tp").setExecutor(new TeleportCommand());
        api.getCommand("tphere").setExecutor(new TeleportHereCommand());
        api.getCommand("stop").setExecutor(new StopCommand());

    }

}
