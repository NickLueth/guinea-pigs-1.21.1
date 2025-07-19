package net.nitwit.guinea_pigs;

import net.fabricmc.api.ModInitializer;

import net.nitwit.guinea_pigs.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GuineaPigs implements ModInitializer {
	public static final String MOD_ID = "guinea_pigs";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
	}
}