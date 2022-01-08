package fr.lyneris.api.manager;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import fr.lyneris.api.API;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings("unchecked") public class HostManager {

    private final Manager manager;

    public HostManager(Manager manager) {
        this.manager = manager;
    }

    public Manager getManager() {
        return manager;
    }

    public void createHost() {
        if(API.getAPI().getDatabaseManager().getHost().find(new BasicDBObject("host", "host")).one() != null) return;
        API.getAPI().getLogger().info("Creating the host list");
        try {
            Bukkit.getScheduler().runTaskAsynchronously(API.getAPI(), () -> {
                DBObject host = new BasicDBObject("host", "host")
                        .append("list", new ArrayList<>(Collections.singletonList(1000)));
                API.getAPI().getDatabaseManager().getHost().insert(host);
            });
        } catch (Exception e) {
            API.getAPI().getLogger().severe("Failed to create host list...");
            e.printStackTrace();
        }
        API.getAPI().getLogger().info("The host list has been created without any error");
    }

    public List<Integer> getHosts() {
        return (List<Integer>) DatabaseManager.getFromHostCollection("list");
    }

    public void setHosts(List<Integer> var1) {
        CompletableFuture.runAsync(() -> {
            API.getAPI().getDatabaseManager().getHost().update(new BasicDBObject("host", "host"),
                    new BasicDBObject("$set", new BasicDBObject("list", var1)));
        });
    }

    public void addHost(int var1) {
        List<Integer> list = new ArrayList<>(getHosts());
        if(list.isEmpty()) {
            setHosts(Collections.singletonList(var1));
        } else {
            if(!list.contains(var1)) list.add(var1);
        }
        setHosts(list);
    }

    public void removeHost(int var1) {
        List<Integer> list = new ArrayList<>(getHosts());
        list.remove((Object) var1);
        setHosts(list);
    }

}
