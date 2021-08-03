package com.yakovliam.minehutcore.command;

import co.aikar.commands.BukkitCommandManager;
import co.aikar.commands.MessageType;
import com.yakovliam.minehutcore.MineHutCorePlugin;
import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.Collections;

public class CommandManager extends BukkitCommandManager {

    public CommandManager(MineHutCorePlugin plugin) {
        super(plugin);

        enableUnstableAPI("help");
        enableUnstableAPI("brigadier");

        setFormat(MessageType.INFO, ChatColor.WHITE);
        setFormat(MessageType.HELP, ChatColor.GRAY);
        setFormat(MessageType.ERROR, ChatColor.RED);
        setFormat(MessageType.SYNTAX, ChatColor.GRAY);

        // TODO add more commands here, registering them
        Collections.singletonList(
                new MHCCommand(plugin)
        ).forEach(c -> {
            c.registerCompletions();
            this.registerCommand(c);
        });
    }
}