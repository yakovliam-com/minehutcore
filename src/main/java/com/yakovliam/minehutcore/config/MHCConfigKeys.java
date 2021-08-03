package com.yakovliam.minehutcore.config;

import com.yakovliam.minehutcore.api.config.generic.KeyedConfiguration;
import com.yakovliam.minehutcore.api.config.generic.key.ConfigKey;
import com.yakovliam.minehutcore.api.config.generic.key.SimpleConfigKey;

import java.util.List;

public final class MHCConfigKeys {

    private static final List<SimpleConfigKey<?>> KEYS = KeyedConfiguration.initialise(MHCConfigKeys.class);

    public static List<? extends ConfigKey<?>> getKeys() {
        return KEYS;
    }
}