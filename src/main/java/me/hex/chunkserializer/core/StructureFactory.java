package me.hex.chunkserializer.core;

import me.hex.chunkserializer.core.interfaces.Factory;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.structure.Structure;
import org.bukkit.structure.StructureManager;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation of Factory, a Structure Factory that produces Structures.
 */
public class StructureFactory implements Factory<Chunk, NamespacedKey, Structure> {

    private final StructureManager manager;

    public StructureFactory(StructureManager manager) {
        this.manager = manager;
    }

    /**
     * IMPORTANT NOTE: DO NOT USE THIS METHOD, IT'S FOR THE API INTERNALS.
     * Creates a structure around the Chunk.
     * Note that this method uses 1.17.1 Structures API.
     *
     * @param chunk Chunk to create a structure around.
     * @param includeEntities whether to include entities or not
     * @param keyToStruct key used for structure.
     * @return Structure created..
     */
    @Override
    public Structure create(Chunk chunk, NamespacedKey keyToStruct, boolean includeEntities) {

        final World world = chunk.getWorld();
        final int minX = chunk.getX() << 4;
        final int minZ = chunk.getZ() << 4;
        final int minY = world.getMinHeight();
        final int maxX = minX | 15;
        final int maxY = world.getMaxHeight();
        final int maxZ = minZ | 15;

        final Location minLocation = new Location(world, minX, minY, minZ);
        final Location maxLocation = new Location(world, maxX, maxY, maxZ);

        final Structure struct = manager.createStructure();

        struct.fill(minLocation, maxLocation, includeEntities);

        manager.registerStructure(keyToStruct, struct);

        CompletableFuture.supplyAsync(() -> {
            try {
                manager.saveStructure(keyToStruct, struct);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        });

        return struct;
    }

    /**
     * Destroys a Structure
     *
     * @param key Key of structure to destroy.
     * @return true if successful, false otherwise.
     */
    @Override
    public CompletableFuture<Boolean> destroy(NamespacedKey key) {

        manager.unregisterStructure(key);

        return CompletableFuture.supplyAsync(() -> {
            try {
                manager.deleteStructure(key);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }).toCompletableFuture();
    }
}
