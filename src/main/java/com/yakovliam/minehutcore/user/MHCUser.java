package com.yakovliam.minehutcore.user;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class MHCUser {

    /**
     * UUID
     */
    private final UUID uuid;

    /**
     * Kills
     */
    private int kills;

    /**
     * Deaths
     */
    private int deaths;

    /**
     * MHC user
     *
     * @param uuid   uuid
     * @param kills  kills
     * @param deaths deaths
     */
    public MHCUser(UUID uuid, int kills, int deaths) {
        this.uuid = uuid;
        this.kills = kills;
        this.deaths = deaths;
    }

    /**
     * Returns uuid
     *
     * @return uuid
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * Returns kills
     *
     * @return kills
     */
    public int getKills() {
        return kills;
    }

    /**
     * Sets kills
     *
     * @param kills kills
     */
    public void setKills(int kills) {
        this.kills = kills;
    }

    /**
     * Returns deaths
     *
     * @return deaths
     */
    public int getDeaths() {
        return deaths;
    }

    /**
     * Sets deaths
     *
     * @param deaths deaths
     */
    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    /**
     * Returns the applicable player
     *
     * @return player
     */
    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    /**
     * Calculates a user's kdr
     *
     * @return kdr
     */
    public double calculateKDR() {
        int kills = getKills();
        int deaths = getDeaths();

        kills = kills == 0 ? 1 : kills;
        deaths = deaths == 0 ? 1 : deaths;

        return (double) kills / deaths;
    }
}
