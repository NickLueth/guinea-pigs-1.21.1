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
        EntityModelLayerRegistry.registerModelLayer(GuineaPigModel.GUINEA_PIG, GuineaPigModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.GUINEA_PIG, GuineaPigRenderer::new);
    }
}
