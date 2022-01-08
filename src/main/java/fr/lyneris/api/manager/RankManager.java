package fr.lyneris.api.manager;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import fr.lyneris.api.API;
import fr.lyneris.api.rank.Rank;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RankManager implements Rank {

    private final Manager manager;
    private final DatabaseManager databaseManager;
    private final String name;

    protected RankManager(String name) {
        this.manager = API.getAPI().getManager();
        this.databaseManager = API.getAPI().getDatabaseManager();
        this.name = name;
    }

    public static List<String> getAllRanks() {
        List<String> list = new ArrayList<>();
        DBCursor cursor = API.getAPI().getDatabaseManager().getRanks().find();
        while (cursor.hasNext()) {
            DBObject obj = cursor.next();
            list.add((String) obj.get("name"));
        }
        return list;
    }
    public static boolean rankExists(String rankName) {
        for (String s : getAllRanks()) {
            if (s.equals(rankName)) return true;
        }
        return false;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public String getName() {
        return name;
    }

    @Override
    public void setName(String var1) {
        CompletableFuture.runAsync(() -> {
            API.getAPI().getDatabaseManager().getRanks().update(new BasicDBObject("name", name),
                    new BasicDBObject("$set", new BasicDBObject("name", var1)));
        });
    }

    @Override
    public void createRank() {
        {
            if(API.getAPI().getDatabaseManager().getRanks().find(new BasicDBObject("name", name)).one() != null) return;
            API.getAPI().getLogger().info("Creating the rank " + name);
            try {
                Bukkit.getScheduler().runTaskAsynchronously(manager.getAPI(), () -> {
                    DBObject profile = new BasicDBObject("name", name)
                            .append("display_name", name)
                            .append("prefix", name)
                            .append("weight", 0)
                            .append("permissions", new ArrayList<>())
                            .append("blacklisted_permissions", new ArrayList<>())
                            .append("op", false);
                    API.getAPI().getDatabaseManager().getRanks().insert(profile);
                });
            } catch (Exception e) {
                API.getAPI().getLogger().severe("Failed to create the rank " + name + "...");
                e.printStackTrace();
            }
            API.getAPI().getLogger().info("The rank " + name + " has been successfully created.");
        }
    }

    @Override
    public String getPrefix() {
        return (String) DatabaseManager.getFromRankCollection(name, "prefix");
    }

    @Override
    public void setPrefix(String var1) {
        CompletableFuture.runAsync(() -> {
            API.getAPI().getDatabaseManager().getRanks().update(new BasicDBObject("name", name),
                    new BasicDBObject("$set", new BasicDBObject("prefix", var1)));
        });
    }

    @Override
    public String getDisplayName() {
        return (String) DatabaseManager.getFromRankCollection(name, "display_name");
    }

    @Override
    public void setDisplayName(String var1) {
        CompletableFuture.runAsync(() -> {
            API.getAPI().getDatabaseManager().getRanks().update(new BasicDBObject("name", name),
                    new BasicDBObject("$set", new BasicDBObject("display_name", var1)));
        });
    }

    @Override
    public int getWeight() {
        return (int) DatabaseManager.getFromRankCollection(name, "weight");
    }

    @Override
    public void setWeight(int var1) {
        CompletableFuture.runAsync(() -> {
            API.getAPI().getDatabaseManager().getRanks().update(new BasicDBObject("name", name),
                    new BasicDBObject("$set", new BasicDBObject("weight", var1)));
        });
    }

    @Override
    public List<String> getPermissions() {
        return (List<String>) DatabaseManager.getFromRankCollection(name, "permissions");
    }
    @Override
    public void setPermissions(List<String> var1) {
        CompletableFuture.runAsync(() -> {
            API.getAPI().getDatabaseManager().getRanks().update(new BasicDBObject("name", name),
                    new BasicDBObject("$set", new BasicDBObject("permissions", var1)));
        });
    }
    public void addPermission(String var1) {
        List<String> list = getPermissions();
        if(list.isEmpty()) {
            setPermissions(Collections.singletonList(var1));
        } else {
            if(!list.contains(var1)) {
                list.add(var1);
                setPermissions(list);
            }
        }

    }
    public void removePermission(String var1) {
        List<String> list = getPermissions();
        list.remove(var1);
        setPermissions(list);
    }

    @Override
    public List<String> getBlacklistedPermissions() {
        return (List<String>) DatabaseManager.getFromRankCollection(name, "blacklisted_permissions");
    }

    @Override
    public void setBlacklistedPermissions(List<String> var1) {
        CompletableFuture.runAsync(() -> {
            API.getAPI().getDatabaseManager().getRanks().update(new BasicDBObject("name", name),
                    new BasicDBObject("$set", new BasicDBObject("blacklisted_permissions", var1)));
        });
    }
    public void addBlacklistedPermission(String var1) {
        List<String> list = getBlacklistedPermissions();
        if(list.isEmpty()) {
            setBlacklistedPermissions(Collections.singletonList(var1));
        } else {
            if(!list.contains(var1)) {
                list.add(var1);
                setBlacklistedPermissions(list);
            }
        }
    }
    public void removeBlacklistedPermission(String var1) {
        List<String> list = getBlacklistedPermissions();
        list.remove(var1);
        setBlacklistedPermissions(list);
    }

    @Override
    public boolean isOp() {
        return (boolean) DatabaseManager.getFromRankCollection(name, "op");
    }

    @Override
    public void setOp(boolean var1) {
        CompletableFuture.runAsync(() -> {
            API.getAPI().getDatabaseManager().getRanks().update(new BasicDBObject("name", name),
                    new BasicDBObject("$set", new BasicDBObject("op", var1)));
        });
    }

    public Manager getManager() {
        return manager;
    }

}
