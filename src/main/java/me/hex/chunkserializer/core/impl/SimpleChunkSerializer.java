package me.hex.chunkserializer.core.impl;

import me.hex.chunkserializer.api.ChunkHandler;
import me.hex.chunkserializer.api.ChunkSerializer;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * API's Main class, holding the core and essentials of the library.
 */
public final class SimpleChunkSerializer implements ChunkSerializer {

    private final JavaPlugin plugin;
    private final ChunkHandler chunkHandler;

    public SimpleChunkSerializer(JavaPlugin plugin, ChunkHandler handler) {
        this.plugin = plugin;
        this.chunkHandler = handler;
    }

    /**
     * Gets the Main instance.
     *
     * @return Main instance of User's plugin.
     */

    @Override
    public JavaPlugin getPlugin() {
        return plugin;
    }


    /**
     * you should use this to serialize, and to deserialize.
     *
     * @return ChunkHandler Object.
     */

    @Override
    public ChunkHandler getChunkHandler() {
        return chunkHandler;
    }


}
