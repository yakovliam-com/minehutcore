package com.yakovliam.minehutcore.storage.implementation.json;

import com.yakovliam.minehutcore.user.MHCUser;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

import java.lang.reflect.Type;
import java.util.UUID;

public class UserSerializer implements TypeSerializer<MHCUser> {

    /**
     * Deserialize an object (of the correct type) from the given configuration
     * node.
     *
     * @param type the type of return value required
     * @param node the node containing serialized data
     * @return an object
     * @throws SerializationException if the presented data is invalid
     * @since 4.0.0
     */
    @Override
    public MHCUser deserialize(Type type, ConfigurationNode node) throws SerializationException {
        UUID uuid = UUID.fromString(node.node("uuid").getString());
        int kills = node.node("kills").getInt();
        int deaths = node.node("deaths").getInt();

        return new MHCUser(uuid, kills, deaths);
    }

    /**
     * Serialize an object to the given configuration node.
     *
     * @param type the type of the input object
     * @param obj  the object to be serialized
     * @param node the node to write to
     * @throws SerializationException if the object cannot be serialized
     * @since 4.0.0
     */
    @Override
    public void serialize(Type type, @Nullable MHCUser obj, ConfigurationNode node) throws SerializationException {
        node.node("uuid").set(obj.getUuid());
        node.node("kills").set(obj.getKills());
        node.node("deaths").set(obj.getDeaths());
    }
}
