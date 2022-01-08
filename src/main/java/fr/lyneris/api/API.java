package fr.lyneris.api;

import fr.lyneris.api.manager.DatabaseManager;
import fr.lyneris.api.manager.Manager;
import fr.lyneris.api.utils.Register;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.ConcurrentMap;

public class API extends JavaPlugin {

    private static API api;
    private Manager manager;
    private DatabaseManager databaseManager;

    @Override
    public void onEnable() {

        api = this;
        manager = new Manager(this);
        databaseManager = new DatabaseManager(this);

        new Register(this);

        manager.getHostManager().createHost();

    }

    public static API getAPI() {
        return api;
    }

    public Manager getManager() {
        return manager;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

}
