package com.yakovliam.minehutcore.expansion;

import com.yakovliam.minehutcore.MineHutCorePlugin;
import com.yakovliam.minehutcore.user.MHCUser;
import com.yakovliam.minehutcore.util.NumberUtil;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

public class MineHutCoreExpansion extends PlaceholderExpansion {

    /**
     * Mine hut core plugin
     */
    private final MineHutCorePlugin plugin;

    /**
     * MineHutCore expansion
     *
     * @param plugin plugin
     */
    public MineHutCoreExpansion(MineHutCorePlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * The placeholder identifier of this expansion. May not contain {@literal %},
     * {@literal {}} or _
     *
     * @return placeholder identifier that is associated with this expansion
     */
    @Override
    public @NotNull String getIdentifier() {
        return "mhc";
    }

    /**
     * The author of this expansion
     *
     * @return name of the author for this expansion
     */
    @Override
    public @NotNull String getAuthor() {
        return plugin.getDescription().getAuthors().stream()
                .findFirst()
                .orElse("yakovliam");
    }

    /**
     * The version of this expansion
     *
     * @return current version of this expansion
     */
    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {


        if (params.equalsIgnoreCase("kills")) { // kills
            MHCUser user = plugin.getUserCache().getCache().get(player.getUniqueId()).join();

            return Integer.toString(user.getKills());
        }

        if (params.equalsIgnoreCase("deaths")) { // deaths
            MHCUser user = plugin.getUserCache().getCache().get(player.getUniqueId()).join();

            return Integer.toString(user.getDeaths());
        }

        if (params.equalsIgnoreCase("kdr")) { // kdr
            MHCUser user = plugin.getUserCache().getCache().get(player.getUniqueId()).join();

            return NumberUtil.formatToTwoDecimalPlaces(user.calculateKDR());
        }

        return null;
    }
}
