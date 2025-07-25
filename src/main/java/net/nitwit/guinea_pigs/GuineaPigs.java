package net.nitwit.guinea_pigs;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.nitwit.guinea_pigs.entity.ModEntities;
import net.nitwit.guinea_pigs.entity.custom.GuineaPigEntity;
import net.nitwit.guinea_pigs.item.ModItems;
import net.nitwit.guinea_pigs.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GuineaPigs implements ModInitializer {
	public static final String MOD_ID = "guinea_pigs";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModEntities.registerModEntities();
		ModWorldGeneration.generateModWorldGen();

		FabricDefaultAttributeRegistry.register(ModEntities.GUINEA_PIG, GuineaPigEntity.createAttributes());
	}
}


// REMEMBER Ctrl + H and Shift * 2
