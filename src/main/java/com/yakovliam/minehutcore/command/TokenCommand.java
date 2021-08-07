package com.yakovliam.minehutcore.command;

import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import co.aikar.commands.annotation.Single;
import co.aikar.commands.annotation.Subcommand;
import com.yakovliam.minehutcore.MineHutCorePlugin;
import com.yakovliam.minehutcore.api.message.Message;
import com.yakovliam.minehutcore.config.MHCConfigKeys;
import com.yakovliam.minehutcore.conversion.TokenConversionRate;
import com.yakovliam.minehutcore.util.NumberUtil;
import com.yakovliam.minehutcore.util.TokenUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("token")
@CommandPermission("minehutcore.command.token")
public class TokenCommand extends AbstractMHCCommand {

    /**
     * MineHut core command
     *
     * @param plugin plugin
     */
    public TokenCommand(MineHutCorePlugin plugin) {
        super(plugin);
    }

    @Subcommand("exchange")
    @Description("Views information about the exchange")
    public void onExchange(Player player) {
        TokenConversionRate tokenConversionRate = MHCConfigKeys.TOKEN_CONVERSION_RATE.get(plugin.getMhcConfig().getAdapter());

        plugin.getMessages().tokenExchange.message(player,
                "%tokens%", Integer.toString(tokenConversionRate.getTokensPer()),
                "%amount%", NumberUtil.formatToTwoDecimalPlaces(tokenConversionRate.getTokensPer()));
    }

    @Subcommand("buy")
    @Description("Buys token for an exchange amount")
    public void onBuy(Player player, @Single Integer amountOfTokens) {
        // if amount is negative or 0
        if (amountOfTokens <= 0) {
            plugin.getMessages().tokenCantExchange.message(player);
            return;
        }

        TokenConversionRate tokenConversionRate = MHCConfigKeys.TOKEN_CONVERSION_RATE.get(plugin.getMhcConfig().getAdapter());

        // find amount of money needed
        double amountOfMoneyNeeded = (amountOfTokens * tokenConversionRate.getCost()) / (double) tokenConversionRate.getTokensPer();

        double balance = plugin.getEconomy().getBalance(player);

        if (amountOfMoneyNeeded > balance) {
            plugin.getMessages().tokenBuyNotEnough.message(player,
                    "%need%", NumberUtil.formatToTwoDecimalPlaces(amountOfMoneyNeeded),
                    "%amount%", Integer.toString(amountOfTokens)
                    , "%have%", NumberUtil.formatToTwoDecimalPlaces(balance));
            return;
        }

        // take money
        plugin.getEconomy().withdrawPlayer(player, amountOfMoneyNeeded);

        // give tokens
        TokenUtil.givePlayerTokens(plugin, player, amountOfTokens);

        plugin.getMessages().tokenBought.message(player, "%tokens%", Integer.toString(amountOfTokens),
                "%cost%", NumberUtil.formatToTwoDecimalPlaces(amountOfMoneyNeeded));
    }

    @Subcommand("sell")
    @Description("Sells tokens for an exchange amount")
    public void onSell(Player player, @Single Integer amountOfTokens) {
        // if amount is negative or 0
        if (amountOfTokens <= 0) {
            plugin.getMessages().tokenCantExchange.message(player);
            return;
        }

        // get amount of tokens
        int amountInInventory = TokenUtil.getPlayerTokensInInventory(player);

        // does player have enough tokens?
        if (amountInInventory < amountOfTokens) {
            plugin.getMessages().tokenSellNotEnough.message(player,
                    "%need%", Integer.toString(amountOfTokens),
                    "%have%", Integer.toString(amountInInventory));
            return;
        }

        TokenConversionRate tokenConversionRate = MHCConfigKeys.TOKEN_CONVERSION_RATE.get(plugin.getMhcConfig().getAdapter());

        // find amount of money needed
        double amountOfMoneyToGive = (amountOfTokens * tokenConversionRate.getCost()) / (double) tokenConversionRate.getTokensPer();

        // take tokens
        TokenUtil.removePlayerTokens(player, amountOfTokens);

        // deposit
        plugin.getEconomy().depositPlayer(player, amountOfMoneyToGive);

        plugin.getMessages().tokenSold.message(player,
                "%tokens%", Integer.toString(amountOfTokens),
                "%paid%", NumberUtil.formatToTwoDecimalPlaces(amountInInventory));
    }

    @Default
    @HelpCommand
    public void onDefault(CommandSender sender, CommandHelp help) {
        Message.builder()
                .addLine("&7Token Help:")
                .build()
                .message(sender);
        help.showHelp();
    }

    @Override
    protected void registerCompletions() {
    }
}