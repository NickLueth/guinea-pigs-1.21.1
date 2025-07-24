package net.nitwit.guinea_pigs.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.nitwit.guinea_pigs.entity.ModEntities;
import org.jetbrains.annotations.Nullable;


public class GuineaPigEntity extends AnimalEntity{
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;


    public GuineaPigEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.5));
        this.goalSelector.add(2, new FleeEntityGoal<>(this, CreeperEntity.class, 6.0F, 1.0D, 1.5D));

        this.goalSelector.add(3, new AnimalMateGoal(this, 1.25));
        this.goalSelector.add(4, new TemptGoal(this, 1.25, Ingredient.ofItems(Items.DANDELION), false));
        this.goalSelector.add(4, new TemptGoal(this, 1.25, Ingredient.ofItems(Items.WHEAT), false));

        this.goalSelector.add(5, new FollowParentGoal(this, 1.25));

        this.goalSelector.add(6, new WanderAroundGoal(this, 1));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 4.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));

        this.goalSelector.add(9, new EatGrassGoal(this));
        // this.goalSelector.add(10, new FollowOwnerGoal(this, 1.0, 10.0F, 2.0F));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 5)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, .3f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 20);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 40;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick(){
        super.tick();
        if (this.getWorld().isClient()) {
            this.setupAnimationStates();
        }
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.DANDELION);
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.GUINEA_PIG.create(world);
    }

    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        return super.damage(source, amount);
    }

}
