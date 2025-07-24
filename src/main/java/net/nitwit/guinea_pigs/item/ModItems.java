package net.nitwit.guinea_pigs.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.nitwit.guinea_pigs.GuineaPigs;
import net.nitwit.guinea_pigs.entity.ModEntities;
import net.nitwit.guinea_pigs.item.custom.DroppingsItem;

public class ModItems {
    public static final Item DROPPINGS = registerItem("droppings", new DroppingsItem(new Item.Settings()));
    public static final Item EMPTY_CRATE = registerItem("empty_crate", new Item(new Item.Settings()));
    public static final Item FULL_CRATE = registerItem("full_crate", new Item(new Item.Settings()));
    public static final Item GUINEA_PIG_SPAWN_EGG = registerItem("guinea_pig_spawn_egg",
            new SpawnEggItem(ModEntities.GUINEA_PIG, 0xeeeee4, 0xeab676, new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(GuineaPigs.MOD_ID, name), item);
    }

    public static void registerModItems() {
        GuineaPigs.LOGGER.info("Registering Mod Items for " + GuineaPigs.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(DROPPINGS);
            entries.add(EMPTY_CRATE);
            entries.add(FULL_CRATE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> {
            entries.add(GUINEA_PIG_SPAWN_EGG);
        });
    }
}
