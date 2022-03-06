package me.hex.chunkserializer;

import com.google.common.base.Preconditions;
import me.hex.chunkserializer.api.ChunkHandler;
import me.hex.chunkserializer.core.StructureFactory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.structure.StructureManager;

/**
 * API's Main class, holding the core and essentials of the library.
 */
public final class ChunkSerializer {

    private final JavaPlugin plugin;
    private final ChunkHandler chunkHandler;

    public ChunkSerializer(JavaPlugin plugin) {
        Preconditions.checkArgument(plugin != null);

        this.plugin = plugin;
        StructureManager manager = plugin.getServer().getStructureManager();
        this.chunkHandler = new ChunkHandler(manager, new StructureFactory(manager));
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
     * @return ChunkHandler Object.
     */

    public ChunkHandler getChunkHolder() {
        return chunkHandler;
    }


}
