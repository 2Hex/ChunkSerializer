package me.hex.chunkserializer.api;

import com.google.common.base.Preconditions;
import me.hex.chunkserializer.core.StructureFactory;
import me.hex.chunkserializer.core.interfaces.Serializer;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.structure.Structure;
import org.bukkit.structure.StructureManager;

import java.util.concurrent.CompletableFuture;

public class ChunkHandler implements Serializer<Chunk, NamespacedKey, ChunkHolder> {

    private final StructureManager manager;
    private final StructureFactory factory;

    public ChunkHandler(StructureManager manager, StructureFactory factory) {
        this.manager = manager;
        this.factory = factory;
    }

    /**
     * Serializes a Chunk to Name-spaced Key specified.
     * Note that this method uses 1.17.1 Structures API.
     * @param toSerialize Chunk to serialize.
     * @param serialKey     Key to use for later deserializing.
     * @param includeEntities Whether to include entities or not.
     * @return Name-spaced Key used to serialize.
     */
    @Override
    public NamespacedKey serialize(Chunk toSerialize, NamespacedKey serialKey, boolean includeEntities) {

        Preconditions.checkArgument(toSerialize != null && serialKey != null);

        factory.create(toSerialize, serialKey, includeEntities);

        return serialKey;
    }

    /**
     * Serializes a Chunk to Name-spaced Key specified.
     * Note that this method uses 1.17.1 Structures API.
     *
     * @param serialKey     Key to use for later deserializing.
     * @param locationInTheChunk location from the chunk.
     * @param includeEntities Whether to include entities or not.
     * @return Name-spaced Key used to serialize.
     */
    public NamespacedKey serialize(Location locationInTheChunk, NamespacedKey serialKey, boolean includeEntities) {
        return serialize(locationInTheChunk.getChunk(), serialKey, includeEntities);
    }

    /**
     * Serializes a Chunk to Name-spaced Key specified.
     * Note that this method uses 1.17.1 Structures API.
     *
     * @param locationInTheChunk location from the chunk.
     * @param serialKey     Key to use for later deserializing.
     * @param includeEntities Whether to include entities or not.
     * @return Name-spaced Key used to serialize.
     */
    public NamespacedKey serialize(Block locationInTheChunk, NamespacedKey serialKey, boolean includeEntities) {
        return serialize(locationInTheChunk.getChunk(), serialKey, includeEntities);
    }

    /**
     * Deserializes a Chunk from its corresponding Name-spaced Key specified.
     * Note that this method uses 1.17.1 Structures API.
     *
     * @param toDeserialize Key corresponding to the Chunk serialized.
     * @return deserialized ChunkHolder from Name-spaced Key.
     */
    @Override
    public ChunkHolder deserialize(NamespacedKey toDeserialize) {

        Preconditions.checkArgument(toDeserialize != null);

        Structure structure = manager.getStructure(toDeserialize);

        Preconditions.checkArgument(structure != null);

        return new ChunkHolder(structure);
    }

    /**
     * Destroys a serialization
     *
     * @param toDestroy Key representing the serialization to destroy
     * @return true if successful false otherwise.
     */
    @Override
    public CompletableFuture<Boolean> destroy(NamespacedKey toDestroy) {
        return factory.destroy(toDestroy);
    }
}
