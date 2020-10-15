package quek.queksend.world.gen.trunk;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;
import quek.queksend.tag.QEBlockTags;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class EndTrunkPlacer extends TrunkPlacer {

    public static final Codec<EndTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) -> method_28904(instance).apply(instance, EndTrunkPlacer::new));

    public EndTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
    }

    @Override
    protected TrunkPlacerType<?> getType() {
        return QETrunkPlacerTypes.end_trunk_placer;
    }

    @Override
    public List<FoliagePlacer.TreeNode> generate(ModifiableTestableWorld world, Random random, int trunkHeight, BlockPos pos, Set<BlockPos> set, BlockBox blockBox, TreeFeatureConfig treeFeatureConfig) {
        placeEndStone(world, pos.down());
        List<FoliagePlacer.TreeNode> list = Lists.newArrayList();
        Direction direction = Direction.Type.HORIZONTAL.random(random);
        int i = trunkHeight - random.nextInt(10) - 1;
        int j = 3 - random.nextInt(3);
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        int x = pos.getX();
        int z = pos.getZ();
        int y = 0;

        int o;
        for(int n = 0; n < trunkHeight; ++n) {
            o = pos.getY() + n;
            if (n >= i && j > 0) {
                x += direction.getOffsetX();
                z += direction.getOffsetZ();
                --j;
            }

            if (method_27402(world, random, mutable.set(x, o, z), set, blockBox, treeFeatureConfig)) {
                y = o + 1;
            }
        }

        list.add(new FoliagePlacer.TreeNode(new BlockPos(x, y, z), 1, false));
        x = pos.getX();
        z = pos.getZ();
        Direction direction2 = Direction.Type.HORIZONTAL.random(random);
        if (direction2 != direction) {
            o = i - random.nextInt(10) - 1;
            int q = 1 + random.nextInt(10);
            y = 0;

            for(int r = o; r < trunkHeight && q > 0; --q) {
                if (r >= 1) {
                    int s = pos.getY() + r;
                    x += direction2.getOffsetX();
                    z += direction2.getOffsetZ();
                    if (method_27402(world, random, mutable.set(x, s, z), set, blockBox, treeFeatureConfig)) {
                        y = s + 1;
                    }
                }

                ++r;
            }

            if (y > 1) {
                list.add(new FoliagePlacer.TreeNode(new BlockPos(x, y, z), 0, false));
            }
        }

        return list;
    }

    protected static void placeEndStone(ModifiableTestableWorld modifiableTestableWorld, BlockPos blockPos) {
        if (!isPlantableBlock(modifiableTestableWorld, blockPos)) {
            TreeFeature.setBlockStateWithoutUpdatingNeighbors(modifiableTestableWorld, blockPos, Blocks.END_STONE.getDefaultState());
        }

    }

    private static boolean isPlantableBlock(TestableWorld testableWorld, BlockPos blockPos) {
        return testableWorld.testBlockState(blockPos, (blockState) -> blockState.isIn(QEBlockTags.generic_end_plantable));
    }
}
