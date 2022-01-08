package fr.lyneris.api.rank;

import java.util.List;

public interface Rank {

    void createRank();

    String getName();
    void setName(String var1);

    String getDisplayName();
    void setDisplayName(String var1);

    String getPrefix();
    void setPrefix(String var1);

    int getWeight();
    void setWeight(int var1);

    List<String> getPermissions();
    void setPermissions(List<String> var1);
    void addPermission(String var1);
    void removePermission(String var1);

    List<String> getBlacklistedPermissions();
    void setBlacklistedPermissions(List<String> var1);
    void addBlacklistedPermission(String var1);
    void removeBlacklistedPermission(String var1);

    boolean isOp();
    void setOp(boolean var1);

}
