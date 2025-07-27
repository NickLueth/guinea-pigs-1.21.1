package net.nitwit.guinea_pigs;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.nitwit.guinea_pigs.entity.ModEntities;
import net.nitwit.guinea_pigs.entity.custom.GuineaPigEntity;
import net.nitwit.guinea_pigs.item.ModItems;
import net.nitwit.guinea_pigs.sound.ModSounds;
import net.nitwit.guinea_pigs.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GuineaPigs implements ModInitializer {
	public static final String MOD_ID = "guinea_pigs";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// Register items like droppings and spawn eggs
		ModItems.registerModItems();

		// Register entities such as the guinea pig
		ModEntities.registerModEntities();

		// Setup entity spawns and other world generation features
		ModWorldGeneration.generateModWorldGen();

		// Register custom sounds used by the mod
		ModSounds.registerSounds();

		// Register default attributes for the guinea pig entity
		FabricDefaultAttributeRegistry.register(ModEntities.GUINEA_PIG, GuineaPigEntity.createAttributes());

		LOGGER.info("Guinea Pigs mod initialized!");
	}
}
