package com.yakovliam.minehutcore.storage.implementation.json;

import com.yakovliam.minehutcore.MineHutCorePlugin;
import com.yakovliam.minehutcore.storage.implementation.StorageImplementation;
import com.yakovliam.minehutcore.user.MHCUser;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

import java.util.List;
import java.util.UUID;

public class JsonStorageImplementation extends StorageImplementation {

    /**
     * Json configuration provider
     */
    private JsonConfigurationProvider usersConfigurationProvider;

    /**
     * Storage implementation
     *
     * @param plugin plugin
     */
    public JsonStorageImplementation(MineHutCorePlugin plugin) {
        super(plugin);
    }

    /**
     * Initializes storage
     */
    @Override
    public void init() {
        usersConfigurationProvider = new JsonConfigurationProvider(plugin, "users.json");
        // load
        usersConfigurationProvider.load();
    }

    /**
     * Saves a user
     *
     * @param user user
     */
    @Override
    public void saveUser(MHCUser user) {
        // get node
        ConfigurationNode usersNode = usersConfigurationProvider.root().node("users");

        // get list
        List<MHCUser> users = null;
        try {
            users = usersNode.getList(MHCUser.class);
        } catch (SerializationException e) {
            e.printStackTrace();
            return;
        }

        // remove if exists
        users.removeIf(u -> u.getUuid().equals(user.getUuid()));
        // add user
        users.add(user);

        // save to node
        try {
            usersNode.setList(MHCUser.class, users);
        } catch (SerializationException e) {
            e.printStackTrace();
            return;
        }

        // save
        save();
    }

    /**
     * Save
     */
    private void save() {
        // save configuration
        try {
            usersConfigurationProvider.getLoader().save(usersConfigurationProvider.root());
        } catch (ConfigurateException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a user
     *
     * @param uuid uuid
     * @return user
     */
    @Override
    public MHCUser loadUser(UUID uuid) {
        // get node
        ConfigurationNode usersNode = usersConfigurationProvider.root().node("users");

        // get list
        MHCUser user = null;
        try {
            user = usersNode.getList(MHCUser.class).stream()
                    .filter(u -> u.getUuid().equals(uuid))
                    .findFirst()
                    .orElse(null);
        } catch (SerializationException e) {
            e.printStackTrace();
            return null;
        }

        // if null, create
        if (user == null) {
            user = new MHCUser(uuid, 0, 0);

            saveUser(user);
        }

        return user;
    }
}
