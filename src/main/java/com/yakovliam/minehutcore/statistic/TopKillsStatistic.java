package com.yakovliam.minehutcore.statistic;

import com.yakovliam.minehutcore.MineHutCorePlugin;
import com.yakovliam.spacestatistics.api.Statistic;
import org.bukkit.Bukkit;

import java.util.Set;
import java.util.UUID;

public class TopKillsStatistic extends Statistic<UUID, Integer> {

    /**
     * Mine hut core plugin
     */
    private final MineHutCorePlugin plugin;

    /**
     * Mine hut core plugin
     *
     * @param plugin  plugin
     * @param uuidSet initialization set
     */
    public TopKillsStatistic(MineHutCorePlugin plugin, Set<UUID> uuidSet) {
        super("mhc-top-kills");
        this.plugin = plugin;

        update(uuidSet, true, true);
    }

    @Override
    protected Integer get(UUID key) {
        return plugin.getUserCache().getCache().get(key).join().getKills();
    }

    @Override
    protected String keyAsString(UUID uuid) {
        return Bukkit.getOfflinePlayer(uuid).getName();
    }

    @Override
    protected String valueAsString(Integer integer) {
        return integer.toString();
    }
}