package me.hex.chunkserializer.api;

import org.bukkit.Chunk;
import org.bukkit.Location;

public interface ChunkHolder {

    /**
     * Spawns and gets the chunk.
     * @param location Where to spawn
     * @return the chunk spawned
     */
    Chunk spawnAndGet(Location location);

    /**
     * Spawns the chunk.
     * @param location Where to spawn
     */
    void spawn(Location location);
}
