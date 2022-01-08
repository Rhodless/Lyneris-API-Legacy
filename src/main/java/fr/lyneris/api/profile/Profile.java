package fr.lyneris.api.profile;

import fr.lyneris.api.rank.Rank;

import java.util.List;
import java.util.UUID;

public interface Profile {

    void createProfile();

    int getCoins();
    void setCoins(int var1);

    int getHost();
    void setHost(int var1);

    List<String> ignoredPlayers();
    void setIgnoredPlayers(List<String> var1);
    void addIgnoredPlayer(String var1);
    void removeIgnoredPlayer(String var1);

    boolean hasMessage();
    void setMessage(boolean var1);

    boolean hasFriendRequests();
    void setFriendRequests(boolean var1);

    boolean hasProfileShown();
    void setProfileShown(boolean var1);

    boolean hasParty();
    void setParty(boolean var1);

    Rank getRank();
    void setRank(Rank var1);

    List<String> bannedPlayers();
    void setBannedPlayers(List<String> var1);
    void ban(String var1);
    void unban(String var1);

}
