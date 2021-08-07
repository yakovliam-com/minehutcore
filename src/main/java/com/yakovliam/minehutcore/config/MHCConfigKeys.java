package com.yakovliam.minehutcore.config;

import com.yakovliam.minehutcore.api.config.generic.KeyedConfiguration;
import com.yakovliam.minehutcore.api.config.generic.key.ConfigKey;
import com.yakovliam.minehutcore.api.config.generic.key.SimpleConfigKey;
import com.yakovliam.minehutcore.conversion.TokenConversionRate;

import java.util.List;

import static com.yakovliam.minehutcore.api.config.generic.key.ConfigKeyFactory.key;

public final class MHCConfigKeys {

    /**
     * Token conversion rate
     */
    public static final ConfigKey<TokenConversionRate> TOKEN_CONVERSION_RATE = key(c -> {
        String rate = c.getString("token.conversion-rate", "");

        if (!rate.contains(":")) {
            return null;
        }

        // try to split by :
        String[] parts = rate.split(":");

        int tokenNum = Integer.parseInt(parts[0]);
        double amount = Double.parseDouble(parts[1]);

        return new TokenConversionRate(tokenNum, amount);
    });

    /**
     * Token give command
     */
    public static final ConfigKey<String> TOKEN_GIVE_COMMAND = key(c -> c.getString("token.give-command", null));

    private static final List<SimpleConfigKey<?>> KEYS = KeyedConfiguration.initialise(MHCConfigKeys.class);

    public static List<? extends ConfigKey<?>> getKeys() {
        return KEYS;
    }
}