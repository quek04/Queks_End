package quek.queksend.mixin;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import quek.queksend.tag.QEBlockTags;

import java.util.Iterator;

@Mixin(ChorusPlantBlock.class)
public abstract class ChorusPlantBlockMixin {

    @Inject(method = "withConnectionProperties", at = @At("INVOKE"), cancellable = true)
    private void withConnectionPropertiesInject(BlockView world, BlockPos pos, CallbackInfoReturnable<BlockState> cir) {
        Block me = (ChorusPlantBlock)(Object)this;
        Block down = world.getBlockState(pos.down()).getBlock();
        Block up = world.getBlockState(pos.up()).getBlock();
        Block north = world.getBlockState(pos.north()).getBlock();
        Block east = world.getBlockState(pos.east()).getBlock();
        Block south = world.getBlockState(pos.south()).getBlock();
        Block west = world.getBlockState(pos.west()).getBlock();

        cir.setReturnValue(Blocks.CHORUS_PLANT.getDefaultState()
                .with(ConnectingBlock.DOWN, down == Blocks.CHORUS_PLANT || down == Blocks.CHORUS_FLOWER || down.isIn(QEBlockTags.chorus_plantable))
                .with(ConnectingBlock.UP, up == Blocks.CHORUS_PLANT || up == Blocks.CHORUS_FLOWER)
                .with(ConnectingBlock.NORTH, north == Blocks.CHORUS_PLANT || north == Blocks.CHORUS_FLOWER)
                .with(ConnectingBlock.EAST, east == Blocks.CHORUS_PLANT || east == Blocks.CHORUS_FLOWER)
                .with(ConnectingBlock.SOUTH, south == Blocks.CHORUS_PLANT || south == Blocks.CHORUS_FLOWER)
                .with(ConnectingBlock.WEST, west == Blocks.CHORUS_PLANT || west == Blocks.CHORUS_FLOWER)
        );
    }

    @Inject(method = "getStateForNeighborUpdate", at = @At("RETURN"), cancellable = true)
    private void getStateForNeighborUpdateInject(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom, CallbackInfoReturnable<BlockState> cir) {
        if (!state.canPlaceAt(world, pos)) {
            world.getBlockTickScheduler().schedule(pos, (ChorusPlantBlock)(Object)this, 1);
            cir.setReturnValue(state.with(ConnectingBlock.FACING_PROPERTIES.get(direction), false));
        } else {
            boolean bl = newState.getBlock() == Blocks.CHORUS_PLANT || newState.isOf(Blocks.CHORUS_FLOWER) || direction == Direction.DOWN && newState.isIn(QEBlockTags.chorus_plantable);
            cir.setReturnValue(state.with(ConnectingBlock.FACING_PROPERTIES.get(direction), bl));
        }
    }

    /**
     *
     * @reason lol
     * @author quek
     */
    @Overwrite
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos.down());
        boolean bl = !world.getBlockState(pos.up()).isAir() && !blockState.isAir();
        Iterator var6 = Direction.Type.HORIZONTAL.iterator();

        Block block2;
        do {
            BlockPos blockPos;
            Block block;
            do {
                if (!var6.hasNext()) {
                    Block block3 = blockState.getBlock();
                    return block3 == Blocks.CHORUS_PLANT || block3.isIn(QEBlockTags.chorus_plantable);
                }

                Direction direction = (Direction)var6.next();
                blockPos = pos.offset(direction);
                block = world.getBlockState(blockPos).getBlock();
            } while(block != Blocks.CHORUS_PLANT);

            if (bl) {
                return false;
            }

            block2 = world.getBlockState(blockPos.down()).getBlock();
        } while(block2 != Blocks.CHORUS_PLANT && !block2.isIn(QEBlockTags.chorus_plantable));

        return true;
    }
}
