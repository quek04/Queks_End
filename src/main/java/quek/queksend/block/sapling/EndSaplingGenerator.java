package quek.queksend.block.sapling;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import org.jetbrains.annotations.Nullable;
import quek.queksend.world.gen.feature.QEConfiguredFeatures;

import java.util.Random;

public class EndSaplingGenerator extends SaplingGenerator {
    @Override
    protected @Nullable ConfiguredFeature<TreeFeatureConfig, ?> createTreeFeature(Random random, boolean bl) {
        if(random.nextInt(10) == 0) {
            return QEConfiguredFeatures.end_tree_big;
        }
        else return QEConfiguredFeatures.end_tree_small;
    }
}
