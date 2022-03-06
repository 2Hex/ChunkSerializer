package me.hex.chunkserializer.core.impl;

import me.hex.chunkserializer.api.ChunkHolder;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.structure.Structure;

import java.util.Objects;
import java.util.Random;

public class SimpleChunkHolder implements ChunkHolder {

    private final Structure structure;

    public SimpleChunkHolder(Structure structure) {
        this.structure = structure;
    }

    /**
     * Spawns the Chunk, and returns it.
     *
     * @param location Location to spawn Chunk at.
     * @return Chunk spawned.
     */
    @Override
    public Chunk spawnAndGet(Location location) {
        spawn(location);
        return Objects.requireNonNull(location.getWorld()).getChunkAt(location);
    }

    /**
     * Spawns the Chunk at the given Location.
     *
     * @param loc Location to spawn Chunk at.
     */
    @Override
    public void spawn(Location loc) {
        structure.place(loc, true, StructureRotation.NONE, Mirror.NONE,
                0, 0f, new Random());
    }

}
