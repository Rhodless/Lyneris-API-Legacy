package fr.lyneris.api.manager;

import fr.lyneris.api.API;
import fr.lyneris.api.profile.Stats;
import fr.lyneris.api.profile.list.*;
import org.bukkit.entity.Player;

import java.util.UUID;

public class StatsManager {

    private final Manager manager;
    private final Player player;
    private final UUID uniqueId;
    private final Stats narutoStats;
    private final Stats blackCoverStats;
    private final Stats practiceStats;
    private final Stats classicStats;
    private final Stats onePieceStats;

    protected StatsManager(Player player) {
        this.manager = API.getAPI().getManager();
        this.player = player;
        this.uniqueId = player.getUniqueId();
        narutoStats = new NarutoStats(player);
        blackCoverStats = new BlackCoverStats(player);
        practiceStats = new PracticeStats(player);
        classicStats = new ClassicStats(player);
        onePieceStats = new OnePieceStats(player);
    }

    public Manager getManager() {
        return manager;
    }

    public Player getPlayer() {
        return player;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public Stats getNarutoStats() {
        return narutoStats;
    }

    public Stats getBlackCoverStats() {
        return blackCoverStats;
    }

    public Stats getPracticeStats() {
        return practiceStats;
    }

    public Stats getClassicStats() {
        return classicStats;
    }

    public Stats getOnePieceStats() {
        return onePieceStats;
    }

    public void createAllProfiles() {
        getNarutoStats().createProfile();
        getBlackCoverStats().createProfile();
        getPracticeStats().createProfile();
        getOnePieceStats().createProfile();
        getClassicStats().createProfile();
    }

}
