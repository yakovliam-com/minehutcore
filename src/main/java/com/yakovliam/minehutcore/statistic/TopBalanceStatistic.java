package com.yakovliam.minehutcore.statistic;

import com.yakovliam.minehutcore.MineHutCorePlugin;
import com.yakovliam.minehutcore.util.NumberUtil;
import com.yakovliam.spacestatistics.api.Statistic;
import org.bukkit.Bukkit;

import java.util.Set;
import java.util.UUID;

public class TopBalanceStatistic extends Statistic<UUID, Double> {

    /**
     * Mine hut core plugin
     */
    private final MineHutCorePlugin plugin;

    /**
     * Top balance statistic
     *
     * @param plugin  plugin
     * @param uuidSet initialization set
     */
    public TopBalanceStatistic(MineHutCorePlugin plugin, Set<UUID> uuidSet) {
        super("mhc-top-balances");
        this.plugin = plugin;

        update(uuidSet, true, true);
    }

    @Override
    protected Double get(UUID key) {
        return plugin.getEconomy().getBalance(Bukkit.getOfflinePlayer(key));
    }

    @Override
    protected String keyAsString(UUID uuid) {
        return Bukkit.getOfflinePlayer(uuid).getName();
    }

    @Override
    protected String valueAsString(Double aDouble) {
        return NumberUtil.formatToTwoDecimalPlaces(aDouble);
    }
}
