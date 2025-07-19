package net.nitwit.guinea_pigs.item;

// 2 items [poops, spawner egg]

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.nitwit.guinea_pigs.GuineaPigs;

public class ModItems {

    public static final Item DROPPINGS = registerItem("droppings", new Item(new Item.Settings()));
    //public static final Item GUINEA_PIG_SPAWN_EGG = registerItem("guinea_pig_spawn_egg", new SpawnEggItem())

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(GuineaPigs.MOD_ID, name), item);
    }

    public static void registerModItems() {
        GuineaPigs.LOGGER.info("Registering Mod Items for " + GuineaPigs.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(DROPPINGS);
        });
    }
}
