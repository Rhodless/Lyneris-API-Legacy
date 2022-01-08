package fr.lyneris.api.manager;

import com.mongodb.*;
import fr.lyneris.api.API;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class DatabaseManager {

    private final API api;
    private final MongoClient mongoClient;
    private final DB database;
    private final DBCollection ranks;
    private final DBCollection practiceStats;
    private final DBCollection narutoStats;
    private final DBCollection blackCoverStats;
    private final DBCollection profile;
    private final DBCollection classic;
    private final DBCollection onepiece;
    private final DBCollection host;

    public DatabaseManager(API api) {
        this.api = api;
        mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        database = mongoClient.getDB("Lyneris");
        practiceStats = database.getCollection("practiceStats");
        ranks = database.getCollection("ranks");
        narutoStats = database.getCollection("narutoStats");
        blackCoverStats = database.getCollection("blackCoverStats");
        profile = database.getCollection("profile");
        classic = database.getCollection("classic");
        onepiece = database.getCollection("onepiece");
        host = database.getCollection("host");
    }

    public DBCollection getClassic() {
        return classic;
    }

    public DBCollection getOnepiece() {
        return onepiece;
    }

    public API getApi() {
        return api;
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public DB getDatabase() {
        return database;
    }

    public DBCollection getRanks() {
        return ranks;
    }

    public DBCollection getPracticeStats() {
        return practiceStats;
    }

    public DBCollection getNarutoStats() {
        return narutoStats;
    }

    public DBCollection getBlackCoverStats() {
        return blackCoverStats;
    }

    public DBCollection getProfile() {
        return profile;
    }

    public DBCollection getHost() {
        return host;
    }

    public static Object getFromHostCollection(String string) {
        try {
            CompletableFuture<Object> task = new CompletableFuture<>();
            CompletableFuture.runAsync(() -> {
                DBObject query = new BasicDBObject("host", "host");
                DBObject cplayer = API.getAPI().getDatabaseManager().getHost().find(query).one();
                task.complete(cplayer.get(string));
            });
            return task.get(1, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Object getFromProfileCollection(UUID uuid, String string) {
        try {
            CompletableFuture<Object> task = new CompletableFuture<>();
            CompletableFuture.runAsync(() -> {
                DBObject query = new BasicDBObject("uuid", uuid.toString());
                DBObject cplayer = API.getAPI().getDatabaseManager().getProfile().find(query).one();
                task.complete(cplayer.get(string));
            });
            return task.get(1, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Object getFromNarutoCollection(UUID uuid, String string) {
        try {
            CompletableFuture<Object> task = new CompletableFuture<>();
            CompletableFuture.runAsync(() -> {
                DBObject query = new BasicDBObject("uuid", uuid.toString());
                DBObject cplayer = API.getAPI().getDatabaseManager().getNarutoStats().find(query).one();
                task.complete(cplayer.get(string));
            });
            return task.get(1, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Object getFromOnePieceCollection(UUID uuid, String string) {
        try {
            CompletableFuture<Object> task = new CompletableFuture<>();
            CompletableFuture.runAsync(() -> {
                DBObject query = new BasicDBObject("uuid", uuid.toString());
                DBObject cplayer = API.getAPI().getDatabaseManager().getOnepiece().find(query).one();
                task.complete(cplayer.get(string));
            });
            return task.get(1, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Object getFromRankCollection(String rank, String string) {
        try {
            CompletableFuture<Object> task = new CompletableFuture<>();
            CompletableFuture.runAsync(() -> {
                DBObject query = new BasicDBObject("name", rank);
                DBObject cplayer = API.getAPI().getDatabaseManager().getRanks().find(query).one();
                task.complete(cplayer.get(string));
            });
            return task.get(1, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object getFromBlackCoverCollection(UUID uuid, String string) {
        try {
            CompletableFuture<Object> task = new CompletableFuture<>();
            CompletableFuture.runAsync(() -> {
                DBObject query = new BasicDBObject("uuid", uuid.toString());
                DBObject cplayer = API.getAPI().getDatabaseManager().getBlackCoverStats().find(query).one();
                task.complete(cplayer.get(string));
            });
            return task.get(1, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object getFromClassicCollection(UUID uuid, String string) {
        try {
            CompletableFuture<Object> task = new CompletableFuture<>();
            CompletableFuture.runAsync(() -> {
                DBObject query = new BasicDBObject("uuid", uuid.toString());
                DBObject cplayer = API.getAPI().getDatabaseManager().getClassic().find(query).one();
                task.complete(cplayer.get(string));
            });
            return task.get(1, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object getFromPracticeCollection(UUID uuid, String string) {
        try {
            CompletableFuture<Object> task = new CompletableFuture<>();
            CompletableFuture.runAsync(() -> {
                DBObject query = new BasicDBObject("uuid", uuid.toString());
                DBObject cplayer = API.getAPI().getDatabaseManager().getPracticeStats().find(query).one();
                task.complete(cplayer.get(string));
            });
            return task.get(1, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
