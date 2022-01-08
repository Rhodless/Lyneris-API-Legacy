package fr.lyneris.api.profile;

public interface Stats {

    void createProfile();

    int getKills();
    void setKills(int var1);

    int getDeaths();
    void setDeaths(int var1);

    int getPlayedGames();
    void setPlayedGames(int var1);

    int getWins();
    void setWins(int var1);

    int getTimePlayed();
    void setTimePlayed(int var1);

    int getLoose();

    double getWinRate();

    double getKillRate();

}
