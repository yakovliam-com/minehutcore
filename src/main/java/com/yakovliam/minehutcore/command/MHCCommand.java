package com.yakovliam.minehutcore.command;

import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CatchUnknown;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.HelpCommand;
import co.aikar.commands.annotation.Subcommand;
import com.yakovliam.minehutcore.MineHutCorePlugin;
import com.yakovliam.minehutcore.api.message.Message;
import org.bukkit.command.CommandSender;

@CommandAlias("mhc")
@CommandPermission("minehutcore.command")
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
    public void onReload(CommandSender sender) {
        // reload config
        plugin.getMhcConfig().reload();

        Message.builder()
                .addLine("&7Reloaded")
                .build()
                .message(sender);
    }

    @Default
    @HelpCommand
    public void onDefault(CommandSender sender, CommandHelp help) {
        Message.builder()
                .addLine("&7MHC Help:")
                .build()
                .message(sender);
        help.showHelp();
    }
}
