package me.hex.chunkserializer.api;

import com.google.common.base.Preconditions;
import me.hex.chunkserializer.core.StructureFactory;
import me.hex.chunkserializer.core.interfaces.Serializer;
import org.bukkit.Chunk;
import org.bukkit.NamespacedKey;
import org.bukkit.structure.Structure;
import org.bukkit.structure.StructureManager;

import java.util.concurrent.CompletableFuture;

public class ChunkHolder implements Serializer<Chunk, NamespacedKey, ChunkResult> {

    private final StructureManager manager;
    private final StructureFactory factory;

    public ChunkHolder(StructureManager manager, StructureFactory factory) {
        this.manager = manager;
        this.factory = factory;
    }

    /**
     * Serializes an Chunk to Name-spaced Key specified.
     * Note that this method uses 1.17.1 Structures API.
     *
     * @param toSerialize     Key to use for later deserializing.
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
     * Deserializes an Chunk from its corresponding Name-spaced Key specified.
     * Note that this method uses 1.17.1 Structures API.
     *
     * @param toDeserialize Key corresponding to the Chunk serialized.
     * @return deserialized ChunkResult from Name-spaced Key.
     */
    @Override
    public ChunkResult deserialize(NamespacedKey toDeserialize) {

        Preconditions.checkArgument(toDeserialize != null);

        Structure structure = manager.getStructure(toDeserialize);

        Preconditions.checkArgument(structure != null);

        return new ChunkResult(structure);
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
