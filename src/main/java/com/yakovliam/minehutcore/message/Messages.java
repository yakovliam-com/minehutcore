package com.yakovliam.minehutcore.message;

import com.yakovliam.minehutcore.MineHutCorePlugin;
import com.yakovliam.minehutcore.api.config.generic.adapter.ConfigurationAdapter;
import com.yakovliam.minehutcore.api.message.Message;

public class Messages {

    /**
     * MHC
     */

    public final Message mhcHelp;

    public final Message mhcReloaded;

    /**
     * Deaths/Kill
     */

    // killed
    public final Message killedPlayer;

    // died
    public final Message died;

    /**
     * Token
     */

    // exchange
    public final Message tokenExchange;

    // token buy not enough
    public final Message tokenBuyNotEnough;

    // token buy not enough
    public final Message tokenSellNotEnough;

    // token bought
    public final Message tokenBuyBought;

    // token sold
    public final Message tokenSellSold;

    // token cant exchange
    public final Message tokenCantExchange;

    /**
     * Messages
     *
     * @param plugin plugin
     */
    public Messages(MineHutCorePlugin plugin) {
        ConfigurationAdapter adapter = plugin.getLangConfig().getAdapter();

        mhcReloaded = Message.fromConfigurationSection("mhc.reloaded", adapter);
        mhcHelp = Message.fromConfigurationSection("mhc.help", adapter);
        killedPlayer = Message.fromConfigurationSection("killed-player", adapter);
        died = Message.fromConfigurationSection("died", adapter);
        tokenExchange = Message.fromConfigurationSection("token.exchange", adapter);
        tokenBuyNotEnough = Message.fromConfigurationSection("token.buy.not-enough", adapter);
        tokenBuyBought = Message.fromConfigurationSection("token.buy.bought", adapter);
        tokenSellNotEnough = Message.fromConfigurationSection("token.sell.not-enough", adapter);
        tokenSellSold = Message.fromConfigurationSection("token.sell.sold", adapter);
        tokenCantExchange = Message.fromConfigurationSection("token.cant-exchange", adapter);
    }
}
