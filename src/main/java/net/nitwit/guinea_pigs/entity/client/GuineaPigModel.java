package net.nitwit.guinea_pigs.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.nitwit.guinea_pigs.GuineaPigs;
import net.nitwit.guinea_pigs.entity.custom.GuineaPigEntity;

public class GuineaPigModel<T extends GuineaPigEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer GUINEA_PIG = new EntityModelLayer(Identifier.of(GuineaPigs.MOD_ID, "guinea_pig"), "main");

    private final ModelPart guineaPig;
    private final ModelPart head;

    public GuineaPigModel(ModelPart root) {
        this.guineaPig = root.getChild("GuineaPig");
        this.head = this.guineaPig.getChild("Head");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData GuineaPig = modelPartData.addChild("GuineaPig", ModelPartBuilder.create(), ModelTransform.pivot(-0.5F, 24.0F, 6.0F));

        ModelPartData Body = GuineaPig.addChild("Body", ModelPartBuilder.create(), ModelTransform.of(0.5F, -1.0F, 0.0F, -0.0436F, 0.0F, 0.0F));

        ModelPartData body_r1 = Body.addChild("body_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -4.0F, -1.0F, 5.0F, 5.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(1.5F, -0.8F, -7.0F, -0.0436F, 0.0F, 0.0F));

        ModelPartData Head = GuineaPig.addChild("Head", ModelPartBuilder.create().uv(0, 13).cuboid(-2.5F, -2.0F, -2.5F, 5.0F, 4.0F, 3.0F, new Dilation(0.0F))
                .uv(16, 13).cuboid(-2.0F, -1.0F, -4.5F, 4.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.5F, -4.0F, -8.0F));

        ModelPartData r_ear_r1 = Head.addChild("r_ear_r1", ModelPartBuilder.create().uv(0, 3).cuboid(-2.0F, 0.0F, -0.5F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.5F, -2.0F, -2.0F, 1.3963F, 1.0036F, 0.5236F));

        ModelPartData l_ear_r1 = Head.addChild("l_ear_r1", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, 0.0F, -0.5F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.5F, -2.0F, -2.0F, 1.3963F, -1.0036F, -0.5236F));

        ModelPartData FrontLegs = GuineaPig.addChild("FrontLegs", ModelPartBuilder.create(), ModelTransform.pivot(1.0F, -1.0F, -8.0F));

        ModelPartData FrontLeft = FrontLegs.addChild("FrontLeft", ModelPartBuilder.create().uv(18, 6).cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 0.0F, 0.0F));

        ModelPartData FrontRight = FrontLegs.addChild("FrontRight", ModelPartBuilder.create().uv(13, 13).cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, 0.0F, 0.0F));

        ModelPartData BackLegs = GuineaPig.addChild("BackLegs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -1.0F, 0.0F));

        ModelPartData BackLeft = BackLegs.addChild("BackLeft", ModelPartBuilder.create().uv(18, 3).cuboid(-1.0F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 0.0F, 0.0F));

        ModelPartData BackRight = BackLegs.addChild("BackRight", ModelPartBuilder.create().uv(18, 0).cuboid(0.0F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public void setAngles(GuineaPigEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.animateMovement(GuineaPigAnimations.ANIM_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.updateAnimation(entity.idleAnimationState, GuineaPigAnimations.ANIM_IDLE, ageInTicks, 1F);
        this.updateAnimation(entity.sittingAnimationState, GuineaPigAnimations.ANIM_SITTING, ageInTicks, 1F);
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

        this.head.yaw = headYaw * 0.017453292F;
        this.head.pitch = headPitch * 0.017453292F;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        guineaPig.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return guineaPig;
    }
}