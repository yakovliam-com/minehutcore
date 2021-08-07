package com.yakovliam.minehutcore.message;

import com.yakovliam.minehutcore.api.message.Message;
import com.yakovliam.minehutcore.util.NumberUtil;

public class Messages {

    /**
     * Deaths/Kill
     */

    // killed
    public Message killedPlayer = Message.builder()
            .addLine("&7You killed &f%target%&7.")
            .build();

    // died
    public Message died = Message.builder()
            .addLine("&7You were killed by &f%killer%&7.")
            .build();

    /**
     * Token
     */

    // exchange
    public Message tokenExchange = Message.builder()
            .addLine("&f%tokens% &7token(s) goes for &f$%amount%")
            .build();

    // token buy not enough
    public Message tokenBuyNotEnough = Message.builder()
            .addLine("&cYou need &f$%need% &cto buy &f%amount% &cand you only have &f%have%&c!")
            .build();

    // token buy not enough
    public Message tokenSellNotEnough = Message.builder()
            .addLine("&cYou need &f$%need% tokens &cto sell, &cand you only have &f%have%&c!")
            .build();

    // token bought
    public Message tokenBought = Message.builder()
            .addLine("&aYou bought &f%tokens% &afor &f$%cost%&a.")
            .build();

    // token sold
    public Message tokenSold = Message.builder()
            .addLine("&aYou sold &f%tokens% &afor &f$%paid%&a.")
            .build();

}
