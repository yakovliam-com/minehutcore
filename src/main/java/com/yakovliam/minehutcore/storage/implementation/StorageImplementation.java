package com.yakovliam.minehutcore.storage.implementation;

import com.yakovliam.minehutcore.MineHutCorePlugin;
import com.yakovliam.minehutcore.user.MHCUser;
import org.spongepowered.configurate.serialize.SerializationException;

import java.util.UUID;

public abstract class StorageImplementation {

    /**
     * Mine hut core plugin
     */
    protected final MineHutCorePlugin plugin;

    /**
     * Storage implementation
     *
     * @param plugin plugin
     */
    protected StorageImplementation(MineHutCorePlugin plugin) {
        this.plugin = plugin;

        init();
    }

    /**
     * Initializes storage
     */
    public abstract void init();

    /**
     * Saves a user
     *
     * @param user user
     */
    public abstract void saveUser(MHCUser user);

    /**
     * Loads a user
     *
     * @param uuid uuid
     * @return user
     */
    public abstract MHCUser loadUser(UUID uuid);
}
