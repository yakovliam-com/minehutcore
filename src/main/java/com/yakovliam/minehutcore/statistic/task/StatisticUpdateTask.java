package com.yakovliam.minehutcore.statistic.task;

import com.yakovliam.minehutcore.MineHutCorePlugin;
import com.yakovliam.minehutcore.api.task.RepeatingTask;

import java.util.Collections;

public class StatisticUpdateTask extends RepeatingTask {

    /**
     * Repeating task
     *
     * @param plugin plugin
     */
    public StatisticUpdateTask(MineHutCorePlugin plugin) {
        // every 1 minute
        super(plugin, 1200L, true);
    }

    /**
     * Run method
     * <p>
     * Implemented by children
     */
    @Override
    public void run() {
        plugin.getTopBalancesStatistic().update(Collections.emptySet(), false, true);
        plugin.getTopDeathsStatistic().update(Collections.emptySet(), false, true);
        plugin.getTopKillsStatistic().update(Collections.emptySet(), false, true);
    }
}
