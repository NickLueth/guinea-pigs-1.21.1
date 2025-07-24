package net.nitwit.guinea_pigs.entity.client;

import com.google.common.collect.Maps;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.nitwit.guinea_pigs.GuineaPigs;
import net.nitwit.guinea_pigs.entity.custom.GuineaPigEntity;
import net.nitwit.guinea_pigs.entity.custom.GuineaPigVariant;

import java.util.Map;

public class GuineaPigRenderer extends MobEntityRenderer<GuineaPigEntity, GuineaPigModel<GuineaPigEntity>> {
    private static final Map<GuineaPigVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(GuineaPigVariant.class), map -> {
                map.put(GuineaPigVariant.WHITE,
                        Identifier.of(GuineaPigs.MOD_ID, "textures/entity/guinea_pig/white.png"));
                map.put(GuineaPigVariant.TRICOLOR,
                        Identifier.of(GuineaPigs.MOD_ID, "textures/entity/guinea_pig/tricolor.png"));
                map.put(GuineaPigVariant.RED,
                        Identifier.of(GuineaPigs.MOD_ID, "textures/entity/guinea_pig/red.png"));
                map.put(GuineaPigVariant.HIMALAYAN,
                        Identifier.of(GuineaPigs.MOD_ID, "textures/entity/guinea_pig/himalayan.png"));
                map.put(GuineaPigVariant.GRAY,
                        Identifier.of(GuineaPigs.MOD_ID, "textures/entity/guinea_pig/gray.png"));
                map.put(GuineaPigVariant.DUTCH,
                        Identifier.of(GuineaPigs.MOD_ID, "textures/entity/guinea_pig/dutch.png"));
                map.put(GuineaPigVariant.BROWN_DUTCH,
                        Identifier.of(GuineaPigs.MOD_ID, "textures/entity/guinea_pig/brown_dutch.png"));
                map.put(GuineaPigVariant.BLACK_TAN,
                        Identifier.of(GuineaPigs.MOD_ID, "textures/entity/guinea_pig/black_tan.png"));
                map.put(GuineaPigVariant.BLACK,
                        Identifier.of(GuineaPigs.MOD_ID, "textures/entity/guinea_pig/black.png"));
                map.put(GuineaPigVariant.ACORN_SQUASH,
                        Identifier.of(GuineaPigs.MOD_ID, "textures/entity/guinea_pig/acorn_squash.png"));
            });

    public GuineaPigRenderer(EntityRendererFactory.Context context) {
        super(context, new GuineaPigModel<>(context.getPart(GuineaPigModel.GUINEA_PIG)), 0.2f);
    }

    @Override
    public Identifier getTexture(GuineaPigEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
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
