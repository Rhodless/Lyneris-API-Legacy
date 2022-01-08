package fr.lyneris.api.manager;

import fr.lyneris.api.API;
import fr.lyneris.api.profile.Profile;
import fr.lyneris.api.rank.Rank;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Manager {

    private final API api;
    private final HostManager hostManager;

    public Manager(API api) {
        this.api = api;
        this.hostManager = new HostManager(this);
    }

    public HostManager getHostManager() {
        return hostManager;
    }

    public API getAPI() {
        return api;
    }

    public StatsManager getProfileManager(Player player) {
        return new StatsManager(player);
    }

    public void createProfile(Player player) {
        new ProfileManager(player).createProfile();
    }

    public Rank getRank(Player player) {
        return new ProfileManager(player).getRank();
    }

    public Profile getProfile(Player player) {
        return new ProfileManager(player);
    }

    public Profile getProfile(UUID uuid) {
        return new ProfileManager(uuid);
    }

    public Rank getRank(String name) {
        return new RankManager(name);
    }

}
