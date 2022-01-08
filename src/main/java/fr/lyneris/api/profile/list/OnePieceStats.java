package fr.lyneris.api.profile.list;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import fr.lyneris.api.API;
import fr.lyneris.api.manager.DatabaseManager;
import fr.lyneris.api.manager.Manager;
import fr.lyneris.api.profile.Stats;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings({"ConstantConditions", "unused"}) public class OnePieceStats implements Stats {

    private final Player player;
    private final Manager manager;
    private final UUID uniqueId;

    public OnePieceStats(Player player) {
        this.player = player;
        this.manager = API.getAPI().getManager();
        this.uniqueId = player.getUniqueId();
    }

    public Player getPlayer() {
        return player;
    }

    public Manager getManager() {
        return manager;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    @Override
    public void createProfile() {
        if(API.getAPI().getDatabaseManager().getOnepiece().find(new BasicDBObject("uuid", player.getUniqueId().toString())).one() != null) return;
        API.getAPI().getLogger().info("Creating a profile (onepieceStats) for " + uniqueId.toString());
        try {
            Bukkit.getScheduler().runTaskAsynchronously(manager.getAPI(), () -> {
                DBObject profile = new BasicDBObject("_id", uniqueId.toString())
                        .append("name", player.getName())
                        .append("uuid", uniqueId.toString())
                        .append("kills", 0)
                        .append("deaths", 0)
                        .append("wins", 0)
                        .append("played_games", 0)
                        .append("time_played", 0);
                API.getAPI().getDatabaseManager().getOnepiece().insert(profile);
            });
        } catch (Exception e) {
            API.getAPI().getLogger().severe("Failed to create a profile (onepieceStats) for " + uniqueId + "...");
            e.printStackTrace();
        }
        API.getAPI().getLogger().info("The profile (onepieceStats) of " + uniqueId + " has been successfully created.");
    }


    @Override
    public int getKills() {
        return (int) DatabaseManager.getFromOnePieceCollection(uniqueId, "kills");
    }

    @Override
    public void setKills(int var1) {
        CompletableFuture.runAsync(() -> {
            API.getAPI().getDatabaseManager().getOnepiece().update(new BasicDBObject("_id", player.getUniqueId().toString()),
                    new BasicDBObject("$set", new BasicDBObject("kills", var1))); 
        });
    }

    @Override
    public int getDeaths() {
        return (int) DatabaseManager.getFromOnePieceCollection(uniqueId, "deaths");
    }

    @Override
    public void setDeaths(int var1) {
        CompletableFuture.runAsync(() -> {
            API.getAPI().getDatabaseManager().getOnepiece().update(new BasicDBObject("_id", player.getUniqueId().toString()),
                    new BasicDBObject("$set", new BasicDBObject("deaths", var1)));
        });
    }

    @Override
    public int getPlayedGames() {
        return (int) DatabaseManager.getFromOnePieceCollection(uniqueId, "played_games");
    }

    @Override
    public void setPlayedGames(int var1) {
        CompletableFuture.runAsync(() -> {
            API.getAPI().getDatabaseManager().getOnepiece().update(new BasicDBObject("_id", player.getUniqueId().toString()),
                    new BasicDBObject("$set", new BasicDBObject("played_games", var1)));
        });
    }

    @Override
    public int getWins() {
        return (int) DatabaseManager.getFromOnePieceCollection(uniqueId, "wins");
    }

    @Override
    public void setWins(int var1) {
        CompletableFuture.runAsync(() -> {
            API.getAPI().getDatabaseManager().getOnepiece().update(new BasicDBObject("_id", player.getUniqueId().toString()),
                    new BasicDBObject("$set", new BasicDBObject("wins", var1)));
        });
    }

    @Override
    public int getTimePlayed() {
        return (int) DatabaseManager.getFromOnePieceCollection(uniqueId, "time_played");
    }

    @Override
    public void setTimePlayed(int var1) {
        CompletableFuture.runAsync(() -> {
            API.getAPI().getDatabaseManager().getOnepiece().update(new BasicDBObject("_id", player.getUniqueId().toString()),
                    new BasicDBObject("$set", new BasicDBObject("time_played", var1)));
        });
    }

    @Override
    public int getLoose() {
        return getPlayedGames() - getWins();
    }

    @Override
    public double getWinRate() {
        DecimalFormat format = new DecimalFormat("#.##");
        return Double.parseDouble(format.format(getWins() / getLoose()));
    }

    @Override
    public double getKillRate() {
        DecimalFormat format = new DecimalFormat("#.##");
        return Double.parseDouble(format.format(getKills() / getDeaths()));
    }

}
