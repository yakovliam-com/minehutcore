package com.yakovliam.minehutcore.message;

import com.yakovliam.minehutcore.api.message.Message;

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

}
