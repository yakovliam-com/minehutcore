package com.yakovliam.minehutcore.user;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.yakovliam.minehutcore.MineHutCorePlugin;
import com.yakovliam.minehutcore.api.cache.AsyncCache;

import java.util.UUID;

public class UserCache extends AsyncCache<UUID, MHCUser> {

    /**
     * Cache
     *
     * @param plugin plugin
     */
    public UserCache(MineHutCorePlugin plugin) {
        super(Caffeine.newBuilder()
                .buildAsync(u -> plugin.getStorage().loadUser(u)));
    }
}
