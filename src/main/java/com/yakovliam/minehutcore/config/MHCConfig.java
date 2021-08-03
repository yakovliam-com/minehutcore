package com.yakovliam.minehutcore.config;

import com.yakovliam.minehutcore.MineHutCorePlugin;
import com.yakovliam.minehutcore.api.config.generic.KeyedConfiguration;
import com.yakovliam.minehutcore.api.config.generic.adapter.ConfigurationAdapter;

public class MHCConfig extends KeyedConfiguration {

    private final MineHutCorePlugin plugin;

    private final ConfigurationAdapter adapter;

    public MHCConfig(MineHutCorePlugin plugin, ConfigurationAdapter adapter) {
        super(adapter, MHCConfigKeys.getKeys());
        this.plugin = plugin;
        this.adapter = adapter;

        init();
    }

    @Override
    protected void load(boolean initial) {
        super.load(initial);
    }

    @Override
    public void reload() {
        super.reload();
    }

    public MineHutCorePlugin getPlugin() {
        return this.plugin;
    }

    public ConfigurationAdapter getAdapter() {
        return adapter;
    }
}