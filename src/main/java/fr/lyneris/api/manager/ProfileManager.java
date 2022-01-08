package fr.lyneris.api.manager;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import fr.lyneris.api.API;
import fr.lyneris.api.profile.Profile;
import fr.lyneris.api.rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class ProfileManager implements Profile {

    private final UUID uniqueId;

    protected ProfileManager(Player player) {
        uniqueId = player.getUniqueId();
    }

    public ProfileManager(UUID uuid) {
        this.uniqueId = uuid;
    }

    @Override
    public void createProfile() {
        if(API.getAPI().getDatabaseManager().getProfile().find(new BasicDBObject("uuid", uniqueId.toString())).one() != null) return;
        API.getAPI().getLogger().info("Creating a profile (profile) for " + uniqueId.toString());
        try {
            Bukkit.getScheduler().runTaskAsynchronously(API.getAPI(), () -> {
                DBObject profile = new BasicDBObject("_id", uniqueId.toString())
                        .append("uuid", uniqueId.toString())
                        .append("grant", "joueur")
                        .append("coins", "0")
                        .append("host", "0")
                        .append("ignored", new ArrayList<>())
                        .append("messages", true)
                        .append("friends", true)
                        .append("profile", true)
                        .append("party", true)
                        .append("ban", new ArrayList<>());
                API.getAPI().getDatabaseManager().getProfile().insert(profile);
            });
        } catch (Exception e) {
            API.getAPI().getLogger().severe("Failed to create a profile (profile) for " + uniqueId + "...");
            e.printStackTrace();
        }
        API.getAPI().getLogger().info("The profile (profile) of " + uniqueId + " has been successfully created.");
    }

    @Override
    public Rank getRank() {
        String rankName = (String) DatabaseManager.getFromProfileCollection(uniqueId, "grant");
        return API.getAPI().getManager().getRank(rankName);
    }
    @Override
    public void setRank(Rank var1) {
        CompletableFuture.runAsync(() -> {
            API.getAPI().getDatabaseManager().getProfile().update(new BasicDBObject("_id", uniqueId.toString()),
                    new BasicDBObject("$set", new BasicDBObject("grant", var1.getName())));
            });
    }

    @Override
    public int getCoins() {
        String string = (String) DatabaseManager.getFromProfileCollection(uniqueId, "coins");
        return Integer.parseInt(string);
    }

    @Override
    public void setCoins(int var1) {
        CompletableFuture.runAsync(() -> {
            API.getAPI().getDatabaseManager().getProfile().update(new BasicDBObject("_id", uniqueId.toString()),
                    new BasicDBObject("$set", new BasicDBObject("coins", var1)));
        });
    }

    @Override
    public int getHost() {
        String string = (String) DatabaseManager.getFromProfileCollection(uniqueId, "host");
        return Integer.parseInt(string);
    }

    @Override
    public void setHost(int var1) {
        CompletableFuture.runAsync(() -> {
            API.getAPI().getDatabaseManager().getProfile().update(new BasicDBObject("_id", uniqueId.toString()),
                    new BasicDBObject("$set", new BasicDBObject("host", var1)));
        });
    }

    @Override
    public List<String> ignoredPlayers() {
        return (List<String>) DatabaseManager.getFromProfileCollection(uniqueId, "ignored");
    }

    @Override
    public void setIgnoredPlayers(List<String> var1) {
        CompletableFuture.runAsync(() -> {
            API.getAPI().getDatabaseManager().getProfile().update(new BasicDBObject("_id", uniqueId.toString()),
                    new BasicDBObject("$set", new BasicDBObject("ignored", var1)));
        });
    }

    @Override
    public void addIgnoredPlayer(String var1) {
        List<String> list = ignoredPlayers();
        list.add(var1);
        setIgnoredPlayers(list);
    }

    @Override
    public void removeIgnoredPlayer(String var1) {
        List<String> list = ignoredPlayers();
        list.remove(var1);
        setIgnoredPlayers(list);
    }

    @Override
    public boolean hasMessage() {
        return (boolean) DatabaseManager.getFromProfileCollection(uniqueId, "messages");
    }

    @Override
    public void setMessage(boolean var1) {
        CompletableFuture.runAsync(() -> {
            API.getAPI().getDatabaseManager().getProfile().update(new BasicDBObject("_id", uniqueId.toString()),
                    new BasicDBObject("$set", new BasicDBObject("messages", var1)));
        });
    }

    @Override
    public boolean hasFriendRequests() {
        return (boolean) DatabaseManager.getFromProfileCollection(uniqueId, "friends");
    }

    @Override
    public void setFriendRequests(boolean var1) {
        CompletableFuture.runAsync(() -> {
            API.getAPI().getDatabaseManager().getProfile().update(new BasicDBObject("_id", uniqueId.toString()),
                    new BasicDBObject("$set", new BasicDBObject("friends", var1)));
        });
    }

    @Override
    public boolean hasProfileShown() {
        return (boolean) DatabaseManager.getFromProfileCollection(uniqueId, "profile");
    }

    @Override
    public void setProfileShown(boolean var1) {
        CompletableFuture.runAsync(() -> {
            API.getAPI().getDatabaseManager().getProfile().update(new BasicDBObject("_id", uniqueId.toString()),
                    new BasicDBObject("$set", new BasicDBObject("profile", var1)));
        });
    }

    @Override
    public boolean hasParty() {
        return (boolean) DatabaseManager.getFromProfileCollection(uniqueId, "party");
    }

    @Override
    public void setParty(boolean var1) {
        CompletableFuture.runAsync(() -> {
            API.getAPI().getDatabaseManager().getProfile().update(new BasicDBObject("_id", uniqueId.toString()),
                    new BasicDBObject("$set", new BasicDBObject("party", var1)));
        });
    }

    @Override
    public List<String> bannedPlayers() {
        return (List<String>) DatabaseManager.getFromProfileCollection(uniqueId, "ban");
    }

    @Override
    public void setBannedPlayers(List<String> var1) {
        CompletableFuture.runAsync(() -> {
            API.getAPI().getDatabaseManager().getProfile().update(new BasicDBObject("_id", uniqueId.toString()),
                    new BasicDBObject("$set", new BasicDBObject("ban", var1)));
        });
    }

    @Override
    public void ban(String var1) {
        List<String> list = bannedPlayers();
        list.add(var1);
        setBannedPlayers(list);
    }

    @Override
    public void unban(String var1) {
        List<String> list = bannedPlayers();
        list.remove(var1);
        setBannedPlayers(list);
    }

}
