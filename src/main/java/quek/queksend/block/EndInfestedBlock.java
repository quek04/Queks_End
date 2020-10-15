package quek.queksend.block;

import com.google.common.collect.Maps;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.EndermiteEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.Map;

public class EndInfestedBlock extends Block {

    private final Block regularBlock;
    private static final Map<Block, Block> REGULAR_TO_INFESTED = Maps.newIdentityHashMap();

    public EndInfestedBlock(Block regularBlock, AbstractBlock.Settings settings) {
        super(settings);
        this.regularBlock = regularBlock;
        REGULAR_TO_INFESTED.put(regularBlock, this);
    }

    public Block getRegularBlock() {
        return this.regularBlock;
    }

    public static boolean isInfestable(BlockState block) {
        return REGULAR_TO_INFESTED.containsKey(block.getBlock());
    }

    public static BlockState fromRegularBlock(Block regularBlock) {
        return REGULAR_TO_INFESTED.get(regularBlock).getDefaultState();
    }

    private void spawnEndermite(ServerWorld serverWorld, BlockPos pos) {
        EndermiteEntity endermiteEntity = EntityType.ENDERMITE.create(serverWorld);
        endermiteEntity.refreshPositionAndAngles((double)pos.getX() + 0.5D, pos.getY(), (double)pos.getZ() + 0.5D, 0.0F, 0.0F);
        serverWorld.spawnEntity(endermiteEntity);
        endermiteEntity.playSpawnEffects();
    }

    @Override
    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) {
        super.onStacksDropped(state, world, pos, stack);
        if (world.getGameRules().getBoolean(GameRules.DO_TILE_DROPS) && EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) {
            this.spawnEndermite(world, pos);
        }
    }

    @Override
    public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
        if (world instanceof ServerWorld) {
            this.spawnEndermite((ServerWorld)world, pos);
        }
    }
}
