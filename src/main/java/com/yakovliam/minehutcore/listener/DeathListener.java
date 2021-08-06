package com.yakovliam.minehutcore.listener;

import com.yakovliam.minehutcore.MineHutCorePlugin;
import com.yakovliam.minehutcore.user.MHCUser;
import com.yakovliam.minehutcore.user.UserCache;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    /**
     * MineHut core plugin
     */
    private final MineHutCorePlugin plugin;

    /**
     * Death listener
     *
     * @param plugin plugin
     */
    public DeathListener(MineHutCorePlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event) {
        Player deadPlayer = event.getEntity();
        Player killerPlayer = event.getEntity().getKiller();

        if (killerPlayer == null) {
            return;
        }

        UserCache userCache = plugin.getUserCache();

        // get users for both the dead and the killer
        MHCUser deadUser = userCache.getCache().get(deadPlayer.getUniqueId()).join();
        MHCUser killerUser = userCache.getCache().get(killerPlayer.getUniqueId()).join();

        // increment deaths for dead user
        deadUser.setDeaths(deadUser.getDeaths() + 1);
        // increment kills for killer user
        killerUser.setKills(killerUser.getKills() + 1);

        // message players
        plugin.getMessages().killedPlayer.message(killerPlayer, "%target%", deadPlayer.getDisplayName());
        plugin.getMessages().died.message(deadPlayer, "%killer%", killerPlayer.getDisplayName());
    }
}
