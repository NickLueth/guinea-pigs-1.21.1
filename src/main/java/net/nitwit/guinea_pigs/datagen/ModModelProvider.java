package net.nitwit.guinea_pigs.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;
import net.minecraft.util.Identifier;
import net.nitwit.guinea_pigs.item.ModItems;

import java.util.Optional;

// This class provides item and block model data for data generation.
public class ModModelProvider extends FabricModelProvider {

    // Constructor to initialize with a FabricDataOutput
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    // Method to generate block state models; left empty since no block models are defined
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {}

    // Method to generate item models
    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        // Register a custom model for the guinea pig spawn egg using the spawn egg template
        itemModelGenerator.register(ModItems.GUINEA_PIG_SPAWN_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));

        // Register the 'droppings' item with a default generated item model
        itemModelGenerator.register(ModItems.DROPPINGS, Models.GENERATED);
    }
}
