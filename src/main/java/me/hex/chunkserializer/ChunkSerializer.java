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

    private final JavaPlugin plugin;
    private final ChunkHolder chunkHolder;

    public ChunkSerializer(JavaPlugin plugin) {
        this.plugin = plugin;
        StructureManager manager = plugin.getServer().getStructureManager();
        this.chunkHolder = new ChunkHolder(manager, new StructureFactory(manager));
    }

    /**
     * Gets the Main instance.
     *
     * @return Main instance of User's plugin.
     */
    public JavaPlugin getPlugin() {
        return plugin;
    }


    /**
     * you should use this to serialize, and to deserialize.
     *
     * @return ChunkHolder Object.
     */

    public ChunkHolder getChunkHolder() {
        return chunkHolder;
    }


}
