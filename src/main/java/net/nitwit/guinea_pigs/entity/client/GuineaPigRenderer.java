package net.nitwit.guinea_pigs.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.nitwit.guinea_pigs.GuineaPigs;
import net.nitwit.guinea_pigs.entity.custom.GuineaPigEntity;

public class GuineaPigRenderer extends MobEntityRenderer<GuineaPigEntity, GuineaPigModel<GuineaPigEntity>> {
    public GuineaPigRenderer(EntityRendererFactory.Context context) {
        super(context, new GuineaPigModel<>(context.getPart(GuineaPigModel.GUINEA_PIG)), 0.2f);
    }

    @Override
    public Identifier getTexture(GuineaPigEntity entity) {
        return Identifier.of(GuineaPigs.MOD_ID, "textures/entity/acorn_squash.png");
    }

    public void render(GuineaPigEntity livingEntity, float f, float g, MatrixStack matrixStack,
        VertexConsumerProvider vertexConsumerProvider, int i) {
        if (livingEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

}
