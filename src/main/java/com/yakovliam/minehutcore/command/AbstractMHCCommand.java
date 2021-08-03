package com.yakovliam.minehutcore.command;

import co.aikar.commands.BaseCommand;
import com.yakovliam.minehutcore.MineHutCorePlugin;

public abstract class AbstractMHCCommand extends BaseCommand {

    /**
     * Mine hut core plugin
     */
    protected final MineHutCorePlugin plugin;

    /**
     * MineHut core command
     *
     * @param plugin plugin
     */
    public AbstractMHCCommand(MineHutCorePlugin plugin) {
        this.plugin = plugin;
    }

    protected abstract void registerCompletions();
}
