package com.yakovliam.minehutcore.util;

import com.yakovliam.minehutcore.MineHutCorePlugin;
import com.yakovliam.minehutcore.config.MHCConfigKeys;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TokenUtil {

    /**
     * Is an item a token
     *
     * @param itemStack item stack
     * @return is a token
     */
    public static boolean isToken(ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }

        if (!itemStack.getType().equals(Material.AMETHYST_SHARD)) {
            return false;
        }

        ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemMeta == null) {
            return false;
        }

        return itemMeta.getEnchantLevel(Enchantment.DURABILITY) == 1;
    }

    /**
     * Gives a player tokens
     *
     * @param player player
     * @param amount amount
     * @param plugin plugin
     */
    public static void givePlayerTokens(MineHutCorePlugin plugin, Player player, Integer amount) {
        // create give command
        String command = MHCConfigKeys.TOKEN_GIVE_COMMAND.get(plugin.getMhcConfig().getAdapter());
        // replace
        command = command.replace("%player_name%", player.getName()).replace("%amount%", Integer.toString(amount));
        // execute
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
    }

    /**
     * Gets the number of tokens in a player's inventory
     *
     * @param player player
     * @return tokens
     */
    public static int getPlayerTokensInInventory(Player player) {
        int amount = 0;
        for (ItemStack content : player.getInventory().getContents()) {
            // if itemstack is a token
            if (isToken(content)) {
                // get amount and add to total
                amount += content.getAmount();
            }
        }

        return amount;
    }

    /**
     * Removes a player's tokens from their inventory
     *
     * @param player         player
     * @param amountToRemove amount to remove
     */
    public static void removePlayerTokens(Player player, Integer amountToRemove) {
        int amountRemoved = 0;

        for (ItemStack content : player.getInventory().getContents()) {
            if (isToken(content)) {
                // get amount
                int size = content.getAmount();

                if (amountRemoved + size > amountToRemove) {
                    int toSetTo = size - (amountToRemove - amountRemoved);

                    content.setAmount(Math.max(toSetTo, 0));
                    break;
                } else {
                    content.setAmount(0);

                    amountRemoved += size;
                }
            }

            if (amountRemoved >= amountToRemove) {
                break;
            }
        }

        player.updateInventory();
    }
}
