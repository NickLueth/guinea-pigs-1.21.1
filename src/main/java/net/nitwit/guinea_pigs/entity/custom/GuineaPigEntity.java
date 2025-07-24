package net.nitwit.guinea_pigs.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.nitwit.guinea_pigs.entity.ModEntities;
import net.nitwit.guinea_pigs.item.ModItems;
import org.jetbrains.annotations.Nullable;


public class GuineaPigEntity extends AnimalEntity{
    public int poopTime = this.random.nextInt(2000) + 2000;
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    private static final TrackedData<Integer> DATA_ID_TYPE_VARIANT =
            DataTracker.registerData(GuineaPigEntity.class, TrackedDataHandlerRegistry.INTEGER);

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
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, .25f)
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
        if (!this.getWorld().isClient && this.isAlive() && --this.poopTime <= 0) {
            this.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            this.dropItem(ModItems.DROPPINGS);
            this.emitGameEvent(GameEvent.ENTITY_PLACE);
            this.poopTime = this.random.nextInt(2000) + 2000;
        }
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.DANDELION);
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        GuineaPigEntity baby = ModEntities.GUINEA_PIG.create(world);
        GuineaPigVariant variant = Util.getRandom(GuineaPigVariant.values(), this.random);
        assert baby != null;
        baby.setVariant(variant);
        return baby;
    }

    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        return super.damage(source, amount);
    }

    // Variant
    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(DATA_ID_TYPE_VARIANT, 9);
    }

    public GuineaPigVariant getVariant() {
        return GuineaPigVariant.byId(this.getTypeVariant() & 255);
    }

    private int getTypeVariant() {
        return this.dataTracker.get(DATA_ID_TYPE_VARIANT);
    }

    private void setVariant(GuineaPigVariant variant) {
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Variant", this.getTypeVariant());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, nbt.getInt("Variant"));
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason,
                                 @Nullable EntityData entityData) {
        GuineaPigVariant variant = Util.getRandom(GuineaPigVariant.values(), this.random);
        setVariant(variant);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }
}
