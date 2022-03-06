package me.hex.chunkserializer.api;

import com.google.common.base.Preconditions;
import me.hex.chunkserializer.core.impl.SimpleChunkHandler;
import me.hex.chunkserializer.core.impl.SimpleChunkSerializer;
import me.hex.chunkserializer.core.impl.StructureFactory;
import me.hex.chunkserializer.core.interfaces.Serializer;
import org.bukkit.Chunk;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.structure.StructureManager;

public interface ChunkSerializer {

    /**
     * Creates an instance of ChunkSerializer.
     * @param plugin Your main class.
     * @return the instance of ChunkSerializer created.
     */
    static ChunkSerializer create(JavaPlugin plugin) {

        Preconditions.checkArgument(plugin != null);

        StructureManager manager = plugin.getServer().getStructureManager();
        SimpleChunkHandler simpleEntityHandler = new SimpleChunkHandler(manager, new StructureFactory(manager));

        return new SimpleChunkSerializer(plugin, simpleEntityHandler);
    }

    /**
     * @return User's plugin
     */
    JavaPlugin getPlugin();

    /**
     * Use this to de/serialize, destroy a serialization
     * @return The Serializer
     */
    Serializer<Chunk, NamespacedKey, ChunkHolder> getChunkHandler();
}
