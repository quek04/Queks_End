package quek.queksend.mixin;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import quek.queksend.tag.QEBlockTags;

import java.util.Iterator;
import java.util.Random;

@Mixin(ChorusFlowerBlock.class)
public abstract class ChorusFlowerBlockMixin {

    @Shadow @Final public static IntProperty AGE;

    @Shadow @Final private ChorusPlantBlock plantBlock;

    @Shadow
    private static boolean isSurroundedByAir(WorldView world, BlockPos pos, @Nullable Direction exceptDirection) {
        Iterator var3 = Direction.Type.HORIZONTAL.iterator();

        Direction direction;
        do {
            if (!var3.hasNext()) {
                return true;
            }

            direction = (Direction)var3.next();
        } while(direction == exceptDirection || world.isAir(pos.offset(direction)));

        return false;
    }

    @Shadow protected abstract void grow(World world, BlockPos pos, int age);

    @Shadow protected abstract void die(World world, BlockPos pos);

    @Inject(method = "randomTick", at = @At("INVOKE"), cancellable = true)
    private void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        BlockPos blockPos = pos.up();
        if (world.isAir(blockPos) && blockPos.getY() < 256) {
            int i = state.get(AGE);
            if (i < 5) {
                boolean bl = false;
                boolean bl2 = false;
                BlockState blockState = world.getBlockState(pos.down());
                Block block = blockState.getBlock();
                int l;
                if (block.isIn(QEBlockTags.chorus_plantable)) {
                    bl = true;
                } else if (block == this.plantBlock) {
                    l = 1;

                    for(int k = 0; k < 4; ++k) {
                        Block block2 = world.getBlockState(pos.down(l + 1)).getBlock();
                        if (block2 != this.plantBlock) {
                            if (block2.isIn(QEBlockTags.chorus_plantable)) {
                                bl2 = true;
                            }
                            break;
                        }

                        ++l;
                    }

                    if (l < 2 || l <= random.nextInt(bl2 ? 5 : 4)) {
                        bl = true;
                    }
                } else if (blockState.isAir()) {
                    bl = true;
                }

                if (bl && isSurroundedByAir(world, blockPos, null) && world.isAir(pos.up(2))) {
                    world.setBlockState(pos, this.plantBlock.withConnectionProperties(world, pos), 2);
                    this.grow(world, blockPos, i);
                } else if (i < 4) {
                    l = random.nextInt(4);
                    if (bl2) {
                        ++l;
                    }

                    boolean bl3 = false;

                    for(int m = 0; m < l; ++m) {
                        Direction direction = Direction.Type.HORIZONTAL.random(random);
                        BlockPos blockPos2 = pos.offset(direction);
                        if (world.isAir(blockPos2) && world.isAir(blockPos2.down()) && isSurroundedByAir(world, blockPos2, direction.getOpposite())) {
                            this.grow(world, blockPos2, i + 1);
                            bl3 = true;
                        }
                    }

                    if (bl3) {
                        world.setBlockState(pos, this.plantBlock.withConnectionProperties(world, pos), 2);
                    } else {
                        this.die(world, pos);
                    }
                } else {
                    this.die(world, pos);
                }

            }
        }
    }

    @Inject(method = "canPlaceAt", at = @At("INVOKE"), cancellable = true)
    private void canPlaceAt(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        BlockState blockState = world.getBlockState(pos.down());
        if (blockState.getBlock() != this.plantBlock && !blockState.isIn(QEBlockTags.chorus_plantable)) {
            if (!blockState.isAir()) {
                cir.setReturnValue(false);
            } else {
                boolean bl = false;

                for (Direction direction : Direction.Type.HORIZONTAL) {
                    BlockState blockState2 = world.getBlockState(pos.offset(direction));
                    if (blockState2.isOf(this.plantBlock)) {
                        if (bl) {
                            cir.setReturnValue(false);
                        }

                        bl = true;
                    } else if (!blockState2.isAir()) {
                        cir.setReturnValue(false);
                    }
                }

                cir.setReturnValue(bl);
            }
        } else {
            cir.setReturnValue(true);
        }
    }
}
