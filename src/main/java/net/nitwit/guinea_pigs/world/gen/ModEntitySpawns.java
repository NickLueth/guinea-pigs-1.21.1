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
    public static void addSpawns() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(
                BiomeKeys.BIRCH_FOREST,
                BiomeKeys.FOREST,
                BiomeKeys.FLOWER_FOREST,
                BiomeKeys.DARK_FOREST,
                BiomeKeys.CHERRY_GROVE,
                BiomeKeys.TAIGA),
                SpawnGroup.CREATURE, ModEntities.GUINEA_PIG, 35, 2, 4);

        SpawnRestriction.register(ModEntities.GUINEA_PIG, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, TameableEntity::isValidNaturalSpawn);
    }
}
