package com.yakovliam.minehutcore;

import com.yakovliam.minehutcore.api.AbstractMineHutCorePlugin;
import com.yakovliam.minehutcore.api.message.Message;
import com.yakovliam.minehutcore.command.CommandManager;
import com.yakovliam.minehutcore.config.MHCConfig;
import com.yakovliam.minehutcore.expansion.MineHutCoreExpansion;
import com.yakovliam.minehutcore.listener.DeathListener;
import com.yakovliam.minehutcore.listener.PlayerListener;
import com.yakovliam.minehutcore.message.Messages;
import com.yakovliam.minehutcore.statistic.TopDeathsStatistic;
import com.yakovliam.minehutcore.statistic.TopKillsStatistic;
import com.yakovliam.minehutcore.statistic.task.StatisticUpdateTask;
import com.yakovliam.minehutcore.storage.Storage;
import com.yakovliam.minehutcore.user.UserCache;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class MineHutCorePlugin extends AbstractMineHutCorePlugin {

    /**
     * MHC Config
     */
    private MHCConfig mhcConfig;

    /**
     * Storage
     */
    private Storage storage;

    /**
     * User cache
     */
    private UserCache userCache;

    /**
     * Messages
     */
    private Messages messages;

    /**
     * Top deaths statistics
     */
    private TopDeathsStatistic topDeathsStatistic;

    /**
     * Top kills statistic
     */
    private TopKillsStatistic topKillsStatistic;

    /**
     * Economy
     */
    private Economy economy;

    @Override
    public void onEnable() {
        Message.initAudience(this);

        this.mhcConfig = new MHCConfig(this, provideConfigAdapter("config.yml"));

        this.getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        this.getServer().getPluginManager().registerEvents(new DeathListener(this), this);

        // register commands
        new CommandManager(this);

        this.storage = new Storage(this);

        // initialize caches
        this.userCache = new UserCache(this);

        this.messages = new Messages();

        // register statistics
        Set<UUID> offline = Arrays.stream(Bukkit.getOfflinePlayers())
                .map(OfflinePlayer::getUniqueId)
                .collect(Collectors.toSet());

        this.topDeathsStatistic = new TopDeathsStatistic(this, offline);
        topDeathsStatistic.register();
        this.topKillsStatistic = new TopKillsStatistic(this, offline);
        topKillsStatistic.register();

        // register expansion
        new MineHutCoreExpansion(this).register();

        // setup economy
        if (!setupEconomy()) {
            getLogger().severe("Not able to find an Economy provider!");
        }

        // statistics update task
        new StatisticUpdateTask(this).start();
    }

    /**
     * Returns the mhc config
     *
     * @return mhc config
     */
    public MHCConfig getMhcConfig() {
        return mhcConfig;
    }

    /**
     * Returns storage
     *
     * @return storage
     */
    public Storage getStorage() {
        return storage;
    }

    /**
     * Returns the user cache
     *
     * @return user cache
     */
    public UserCache getUserCache() {
        return userCache;
    }

    /**
     * Returns messages
     *
     * @return messages
     */
    public Messages getMessages() {
        return messages;
    }

    /**
     * Returns top deaths statistic
     *
     * @return top deaths
     */
    public TopDeathsStatistic getTopDeathsStatistic() {
        return topDeathsStatistic;
    }

    /**
     * Returns top kills statistic
     *
     * @return top kills
     */
    public TopKillsStatistic getTopKillsStatistic() {
        return topKillsStatistic;
    }

    /**
     * Returns economy
     *
     * @return economy
     */
    public Economy getEconomy() {
        return economy;
    }

    /**
     * Sets up economy
     *
     * @return economy
     */
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }

        this.economy = rsp.getProvider();

        return true;
    }
}
