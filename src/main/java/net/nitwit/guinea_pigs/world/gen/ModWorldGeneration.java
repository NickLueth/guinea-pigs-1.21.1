package net.nitwit.guinea_pigs.world.gen;

public class ModWorldGeneration {
    // Call this method during mod initialization to set up all mod world generation features
    public static void generateModWorldGen() {
        // Add all entity spawn rules for the mod
        ModEntitySpawns.addSpawns();

        // You can add other world gen calls here later (e.g. ores, structures, vegetation)
    }
}