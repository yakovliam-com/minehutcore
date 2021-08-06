package com.yakovliam.minehutcore.storage;

import com.yakovliam.minehutcore.MineHutCorePlugin;
import com.yakovliam.minehutcore.storage.implementation.StorageImplementation;
import com.yakovliam.minehutcore.storage.implementation.json.JsonStorageImplementation;
import com.yakovliam.minehutcore.user.MHCUser;
import org.spongepowered.configurate.serialize.SerializationException;

import java.util.UUID;

public class Storage {

    /**
     * Storage implementation
     */
    private final StorageImplementation implementation;

    /**
     * Mine hut core plugin
     */
    private final MineHutCorePlugin plugin;

    /**
     * Storage
     *
     * @param plugin plugin
     */
    public Storage(MineHutCorePlugin plugin) {
        this.plugin = plugin;

        // initialize implementation
        this.implementation = new JsonStorageImplementation(plugin);
    }

    /**
     * Saves a user
     *
     * @param user user
     */
    public void saveUser(MHCUser user) {
        this.implementation.saveUser(user);
    }

    /**
     * Loads a user
     *
     * @param uuid uuid
     * @return user
     */
    public MHCUser loadUser(UUID uuid) {
        return this.implementation.loadUser(uuid);
    }
}
