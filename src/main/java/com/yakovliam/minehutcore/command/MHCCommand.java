package com.yakovliam.minehutcore.command;

import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.HelpCommand;
import co.aikar.commands.annotation.Subcommand;
import com.yakovliam.minehutcore.MineHutCorePlugin;
import org.bukkit.command.CommandSender;

@CommandAlias("mhc")
@CommandPermission("minehutcore.command.mhc")
public class MHCCommand extends AbstractMHCCommand {

    /**
     * MineHut core command
     *
     * @param plugin plugin
     */
    public MHCCommand(MineHutCorePlugin plugin) {
        super(plugin);
    }

    @Override
    protected void registerCompletions() {
    }

    @Subcommand("reload")
    @CommandPermission("minehutcore.command.mhc.reload")
    public void onReload(CommandSender sender) {
        // reload config
        plugin.getMhcConfig().reload();
        plugin.getLangConfig().reload();
        plugin.loadMessages();

        plugin.getMessages().mhcReloaded.message(sender);
    }

    @Default
    @HelpCommand
    public void onDefault(CommandSender sender, CommandHelp help) {
        plugin.getMessages().mhcHelp.message(sender);
        help.showHelp();
    }
}
