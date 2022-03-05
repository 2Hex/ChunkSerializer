package me.hex.chunkserializer;

import me.hex.chunkserializer.api.ChunkHolder;
import me.hex.chunkserializer.core.StructureFactory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.structure.StructureManager;

import java.util.Random;

/**
 * API's Main class, holding the core and essentials of the library.
 */
public final class ChunkSerializer {

    private JavaPlugin plugin;
    private ChunkHolder chunkHolder;
    private StructureFactory factory;
    private Random random;
    private StructureManager manager;

    /**
     * Gets the Main instance.
     *
     * @return Main instance of User's plugin if set, otherwise null
     */
    public JavaPlugin getPlugin() {
        return plugin;
    }

    /**
     * Initializes the API, With the User's Main Instance.
     * REQUIRED TO USE THE API.
     *
     * @param plugin User's Main Instance.
     */
    public void setPlugin(JavaPlugin plugin) {
        this.plugin = plugin;
        initialize();
    }

    /**
     * Checks if the main class instance was set.
     *
     * @return true if set, otherwise false.
     */
    public boolean isPluginSet() {
        return plugin != null;
    }

    /**
     * IMPORTANT NOTE: THIS SHOULD NOT BE USED OUTSIDE OF API INTERNALS. THIS IS NOT FOR YOU.
     *
     * @return StructureManager Object
     */
    public StructureManager getManager() {
        return manager;
    }

    /**
     * you should use this to serialize, and to deserialize.
     *
     * @return ChunkHolder Object.
     */
    public ChunkHolder getChunkHolder() {
        return chunkHolder;
    }

    /**
     * Initializes variables.
     * IMPORTANT NOTE: THIS SHOULD NOT BE USED OUTSIDE OF API INTERNALS. THIS IS NOT FOR YOU.
     */
    private void initialize() {
        manager = plugin.getServer().getStructureManager();
        factory = new StructureFactory(manager);
        chunkHolder = new ChunkHolder(manager, factory);
        random = new Random();
    }

    /**
     * IMPORTANT NOTE: THIS SHOULD NOT BE USED OUTSIDE OF API INTERNALS. THIS IS NOT FOR YOU.
     *
     * @return StructureFactory Instance
     */
    public StructureFactory getFactory() {
        return factory;
    }

    /**
     * Gets the random object used for Structure API methods.
     *
     * @return Random Object.
     */
    public Random getRandom() {
        return random;
    }

}
