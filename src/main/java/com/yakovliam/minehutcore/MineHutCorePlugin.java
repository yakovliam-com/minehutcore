package com.yakovliam.minehutcore;

import com.yakovliam.minehutcore.api.AbstractMineHutCorePlugin;
import com.yakovliam.minehutcore.api.message.Message;
import com.yakovliam.minehutcore.command.CommandManager;
import com.yakovliam.minehutcore.config.MHCConfig;
import com.yakovliam.minehutcore.listener.PlayerListener;

public class MineHutCorePlugin extends AbstractMineHutCorePlugin {

    /**
     * MHC Config
     */
    private MHCConfig mhcConfig;

    @Override
    public void onEnable() {
        Message.initAudience(this);

        this.mhcConfig = new MHCConfig(this, provideConfigAdapter("config.yml"));

        this.getServer().getPluginManager().registerEvents(new PlayerListener(this), this);

        // register commands
        new CommandManager(this);
    }

    /**
     * Returns the mhc config
     *
     * @return mhc config
     */
    public MHCConfig getMhcConfig() {
        return mhcConfig;
    }
}
