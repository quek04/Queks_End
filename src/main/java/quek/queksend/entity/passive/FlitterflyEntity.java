package quek.queksend.entity.passive;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.TargetFinder;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class FlitterflyEntity extends PassiveEntity {

    public FlitterflyEntity(EntityType<? extends PassiveEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlightMoveControl(this, 20, true);
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    public static DefaultAttributeContainer.Builder createFlitterflyAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0D).add(EntityAttributes.GENERIC_FLYING_SPEED, 1.0D);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new WanderGoal());
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        BirdNavigation birdNavigation = new BirdNavigation(this, world) {
            public boolean isValidPosition(BlockPos pos) {
                return !this.world.getBlockState(pos.down()).isAir();
            }

            public void tick() {
                super.tick();
            }
        };
        birdNavigation.setCanPathThroughDoors(false);
        birdNavigation.setCanSwim(false);
        birdNavigation.setCanEnterOpenDoors(true);
        return birdNavigation;
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        if (this.world.isClient) {
            for (int i = 0; i < 4; ++i) {
                this.world.addParticle(ParticleTypes.PORTAL, this.getParticleX(0.5D), this.getRandomBodyY() - 0.25D, this.getParticleZ(0.5D), (this.random.nextDouble() - 0.5D) * 2.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 2.0D);
            }
        }
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        this.teleportRandomly();
        return super.damage(source, amount);
    }

    protected void teleportRandomly() {
        if (!this.world.isClient() && this.isAlive()) {
            double x = this.getX() + (this.random.nextDouble() - 0.5D) * 64.0D;
            double y = this.getY() + (double)(this.random.nextInt(64) - 32);
            double z = this.getZ() + (this.random.nextDouble() - 0.5D) * 64.0D;
            this.teleportTo(x, y, z);
        }
    }

    private void teleportTo(double x, double y, double z) {
        BlockPos.Mutable mutable = new BlockPos.Mutable(x, y, z);

        while(mutable.getY() > 0 && !this.world.getBlockState(mutable).getMaterial().blocksMovement()) {
            mutable.move(Direction.DOWN);
        }

        BlockState blockState = this.world.getBlockState(mutable);
        boolean bl = blockState.getMaterial().blocksMovement();
        boolean bl2 = blockState.getFluidState().isIn(FluidTags.WATER);
        if (bl && !bl2) {
            boolean bl3 = this.teleport(x, y, z, true);
            if (bl3 && !this.isSilent()) {
                this.world.playSound(null, this.prevX, this.prevY, this.prevZ, SoundEvents.ENTITY_ENDERMAN_TELEPORT, this.getSoundCategory(), 1.0F, 1.0F);
                this.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
            }

        }
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier) {
        return false;
    }

    @Override
    protected void fall(double heightDifference, boolean onGround, BlockState landedState, BlockPos landedPosition) {
    }

    @Override
    protected boolean hasWings() {
        return true;
    }

    class WanderGoal extends Goal {
        WanderGoal() {
            this.setControls(EnumSet.of(Goal.Control.MOVE));
        }

        public boolean canStart() {
            return FlitterflyEntity.this.navigation.isIdle() && FlitterflyEntity.this.random.nextInt(10) == 0;
        }

        public boolean shouldContinue() {
            return FlitterflyEntity.this.navigation.isFollowingPath();
        }

        public void start() {
            Vec3d vec3d = this.getRandomLocation();
            if (vec3d != null) {
                FlitterflyEntity.this.navigation.startMovingAlong(FlitterflyEntity.this.navigation.findPathTo(new BlockPos(vec3d), 1), 1.0D);
            }

        }

        @Nullable
        private Vec3d getRandomLocation() {
            Vec3d vec3d3 = FlitterflyEntity.this.getRotationVec(0.0F);

            Vec3d vec3d4 = TargetFinder.findAirTarget(FlitterflyEntity.this, 8, 7, vec3d3, 1.5707964F, 2, 1);
            return vec3d4 != null ? vec3d4 : TargetFinder.findGroundTarget(FlitterflyEntity.this, 8, 4, -2, vec3d3, 1.5707963705062866D);
        }
    }
}
