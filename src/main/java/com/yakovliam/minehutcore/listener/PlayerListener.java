package com.yakovliam.minehutcore.listener;

import com.yakovliam.minehutcore.MineHutCorePlugin;
import com.yakovliam.minehutcore.api.config.generic.adapter.ConfigurationAdapter;
import com.yakovliam.minehutcore.api.message.Message;
import com.yakovliam.minehutcore.config.MHCConfigKeys;
import com.yakovliam.minehutcore.replacer.AmpersandReplacer;
import com.yakovliam.minehutcore.replacer.SectionReplacer;
import com.yakovliam.minehutcore.user.MHCUser;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Collections;
import java.util.List;

public class PlayerListener implements Listener {

    /**
     * Section replacer
     */
    private static final SectionReplacer SECTION_REPLACER = new SectionReplacer();

    /**
     * Ampersand replacer
     */
    private static final AmpersandReplacer AMPERSAND_REPLACER = new AmpersandReplacer();

    /**
     * MineHut core plugin
     */
    private final MineHutCorePlugin plugin;

    /**
     * Player listener
     *
     * @param plugin plugin
     */
    public PlayerListener(MineHutCorePlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);

        // load into cache
        plugin.getUserCache().getCache().get(event.getPlayer().getUniqueId());

        // add player to statistics
        plugin.getTopDeathsStatistic().update(Collections.singleton(event.getPlayer().getUniqueId()), true);
        plugin.getTopKillsStatistic().update(Collections.singleton(event.getPlayer().getUniqueId()), true);
        plugin.getTopBalancesStatistic().update(Collections.singleton(event.getPlayer().getUniqueId()), true);

        getJoinMessage(event.getPlayer()).broadcast();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);

        // get user
        MHCUser user = plugin.getUserCache().getCache().get(event.getPlayer().getUniqueId()).join();
        // save user
        plugin.getStorage().saveUser(user);
        // remove from cache
        plugin.getUserCache().getCache().synchronous().invalidate(event.getPlayer().getUniqueId());

        getLeaveMessage(event.getPlayer()).broadcast();
    }

    /**
     * Returns the join message for the applicable player
     *
     * @param player player
     * @return join message
     */
    private Message getJoinMessage(Player player) {
        ConfigurationAdapter adapter = plugin.getMhcConfig().getAdapter();

        Message.Builder builder = Message.builder();

        List<String> lines = adapter.getStringList("join-message", Collections.emptyList());
        lines.forEach(line -> {
            builder.addLine(SECTION_REPLACER.apply(PlaceholderAPI.setPlaceholders(player, AMPERSAND_REPLACER.apply(line, player)), player));
        });

        // build and return
        return builder.build();
    }

    /**
     * Returns the leave message for the applicable player
     *
     * @param player player
     * @return leave message
     */
    private Message getLeaveMessage(Player player) {
        ConfigurationAdapter adapter = plugin.getMhcConfig().getAdapter();

        Message.Builder builder = Message.builder();

        List<String> lines = adapter.getStringList("leave-message", Collections.emptyList());
        lines.forEach(line -> {
            builder.addLine(SECTION_REPLACER.apply(PlaceholderAPI.setPlaceholders(player, AMPERSAND_REPLACER.apply(line, player)), player));
        });

        // build and return
        return builder.build();
    }
}
