package net.nitwit.guinea_pigs;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.nitwit.guinea_pigs.entity.ModEntities;
import net.nitwit.guinea_pigs.entity.client.GuineaPigModel;
import net.nitwit.guinea_pigs.entity.client.GuineaPigRenderer;

public class GuineaPigsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Register the model layer for the guinea pig entity, providing the model data supplier
        EntityModelLayerRegistry.registerModelLayer(GuineaPigModel.GUINEA_PIG, GuineaPigModel::getTexturedModelData);

        // Register the renderer for the guinea pig entity, linking it to the custom renderer class
        EntityRendererRegistry.register(ModEntities.GUINEA_PIG, GuineaPigRenderer::new);
    }
}
