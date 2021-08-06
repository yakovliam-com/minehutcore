package com.yakovliam.minehutcore.storage.implementation.json;

import com.yakovliam.minehutcore.MineHutCorePlugin;
import com.yakovliam.minehutcore.user.MHCUser;
import org.spongepowered.configurate.BasicConfigurationNode;
import org.spongepowered.configurate.gson.GsonConfigurationLoader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonConfigurationProvider {

    /**
     * Mine hut core plugin
     */
    protected final MineHutCorePlugin plugin;


    /**
     * Loader
     */
    private final GsonConfigurationLoader loader;

    /**
     * Root node
     */
    private BasicConfigurationNode root;

    /**
     * Path
     */
    private final String path;

    /**
     * Json configuration provider
     *
     * @param plugin plugin
     * @param path   path
     */
    public JsonConfigurationProvider(MineHutCorePlugin plugin, String path) {
        this.plugin = plugin;
        this.path = path;
        this.loader = GsonConfigurationLoader.builder()
                .defaultOptions(opts -> opts.serializers(build -> build.register(MHCUser.class, new UserSerializer())))
                .path(resolve())
                .build();
    }


    /**
     * Resolves the path of the json storage file
     *
     * @return path
     */
    private Path resolve() {
        Path configFile = plugin.getDataFolder().toPath().resolve(path);

        // if the config doesn't exist, create it based on the template in the resources dir
        if (!Files.exists(configFile)) {
            try {
                Files.createDirectories(configFile.getParent());
            } catch (IOException ignored) {
            }

            try (InputStream is = getClass().getClassLoader().getResourceAsStream(path)) {
                Files.copy(is, configFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return configFile;
    }

    /**
     * Loads the json configuration
     */
    public void load() {
        try {
            this.root = loader.load();
        } catch (IOException e) {
            System.err.println("An error occurred while loading the json storage implementation: " + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
        }
    }

    public GsonConfigurationLoader getLoader() {
        return loader;
    }

    public BasicConfigurationNode root() {
        return root;
    }
}
