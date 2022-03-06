package me.hex.chunkserializer.api;

import me.hex.chunkserializer.core.interfaces.Serializer;
import org.bukkit.Chunk;
import org.bukkit.NamespacedKey;

public interface ChunkHandler extends Serializer<Chunk, NamespacedKey, ChunkHolder> {

}
