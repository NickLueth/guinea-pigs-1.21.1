package net.nitwit.guinea_pigs.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;
import net.nitwit.guinea_pigs.entity.ModEntities;

public class ModEntitySpawns {
    // Call this method during mod initialization to add entity spawns to biomes
    public static void addSpawns() {
        // Add Guinea Pig spawns to specified forest-like biomes with spawn weight and group sizes
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(
                        BiomeKeys.BIRCH_FOREST,
                        BiomeKeys.FOREST,
                        BiomeKeys.FLOWER_FOREST,
                        BiomeKeys.DARK_FOREST,
                        BiomeKeys.CHERRY_GROVE,
                        BiomeKeys.TAIGA),
                SpawnGroup.CREATURE,  // Spawn group (passive creatures)
                ModEntities.GUINEA_PIG,  // The entity to spawn
                35,  // Spawn weight (higher = more common)
                2,   // Minimum group size
                4    // Maximum group size
        );

        // Register spawning restrictions for Guinea Pigs:
        // They spawn on the ground on blocks with heightmap MOTION_BLOCKING_NO_LEAVES,
        // and use the natural spawn condition of tameable entities.
        SpawnRestriction.register(
                ModEntities.GUINEA_PIG,
                SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                TameableEntity::isValidNaturalSpawn
        );
    }
}
