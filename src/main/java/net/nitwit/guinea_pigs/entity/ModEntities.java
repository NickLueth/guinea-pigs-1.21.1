package net.nitwit.guinea_pigs.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.nitwit.guinea_pigs.GuineaPigs;
import net.nitwit.guinea_pigs.entity.custom.GuineaPigEntity;

public class ModEntities {
    public static final EntityType<GuineaPigEntity> GUINEA_PIG = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(GuineaPigs.MOD_ID, "guinea_pig"),
            EntityType.Builder.create(GuineaPigEntity::new, SpawnGroup.CREATURE)
                    .dimensions(.6f, .4f).build());


    public static void registerModEntities() {
        GuineaPigs.LOGGER.info("Registering Mod Entities for " + GuineaPigs.MOD_ID);
    }
}
