package quek.queksend.world.gen.feature;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.CountConfig;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.decorator.CountExtraDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DepthAverageDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.JungleFoliagePlacer;
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer;
import net.minecraft.world.gen.placer.DoublePlantPlacer;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.ForkingTrunkPlacer;
import quek.queksend.QueksEndMod;
import quek.queksend.block.ObversaPlantBlock;
import quek.queksend.block.QEBlocks;
import quek.queksend.world.gen.trunk.EndTrunkPlacer;

public class QEConfiguredFeatures {

    private static final RuleTest end_stone = new BlockMatchRuleTest(Blocks.END_STONE);

    public static final ConfiguredFeature<TreeFeatureConfig, ?> end_tree_small = QEFeatures.end_tree.configure((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(QEBlocks.end_log.getDefaultState()), new SimpleBlockStateProvider(QEBlocks.end_leaves.getDefaultState()), new LargeOakFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(2), 4), new EndTrunkPlacer(5, 4, 4), new TwoLayersFeatureSize(1, 0, 2)).build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> end_tree_big = QEFeatures.end_tree.configure((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(QEBlocks.end_log.getDefaultState()), new SimpleBlockStateProvider(QEBlocks.end_leaves.getDefaultState()), new LargeOakFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(2), 4), new EndTrunkPlacer(20, 8, 8), new TwoLayersFeatureSize(2, 0, 4)).build()));

    public static final ConfiguredFeature<?, ?> patch_end_grass = Feature.RANDOM_PATCH.configure((new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(QEBlocks.end_grass.getDefaultState()), SimpleBlockPlacer.INSTANCE)).tries(32).build()).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE).repeat(5);

    public static final ConfiguredFeature<?, ?> patch_tall_end_grass = Feature.RANDOM_PATCH.configure((new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(QEBlocks.tall_end_grass.getDefaultState()), DoublePlantPlacer.INSTANCE)).tries(32).build()).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE).repeat(5);

    public static final ConfiguredFeature<?, ?> patch_obversa_fruit = Feature.RANDOM_PATCH.configure((new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(QEBlocks.obversa_plant.getDefaultState().with(ObversaPlantBlock.AGE, 3)), SimpleBlockPlacer.INSTANCE)).tries(32).build()).method_30377(256).spreadHorizontally().repeat(5);

    public static final ConfiguredFeature<?, ?> ore_paramulite = Feature.ORE.configure(new OreFeatureConfig(end_stone, QEBlocks.paramulite_ore.getDefaultState(), 4)).method_30377(64).spreadHorizontally();

    public static final ConfiguredFeature<?, ?> ore_argulum = Feature.ORE.configure(new OreFeatureConfig(end_stone, QEBlocks.argulum_ore.getDefaultState(), 2)).method_30377(128).spreadHorizontally();

    public static final ConfiguredFeature<?, ?> ore_infested_end_stone = Feature.ORE.configure(new OreFeatureConfig(end_stone, QEBlocks.argulum_ore.getDefaultState(), 9)).method_30377(256).spreadHorizontally().repeat(7);

    public static final ConfiguredFeature<?, ?> trees_end = Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(ImmutableList.of(end_tree_small.withChance(1), end_tree_big.withChance(.5F)), patch_end_grass)).decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(30, 0.1F, 1)));


    public static void registerAll() {
        register("end_tree_small", end_tree_small);
        register("end_tree_big", end_tree_big);
        register("patch_end_grass", patch_end_grass);
        register("patch_tall_end_grass", patch_tall_end_grass);
        register("patch_obversa_fruit", patch_obversa_fruit);
        register("ore_paramulite", ore_paramulite);
        register("ore_argulum", ore_argulum);
        register("ore_infested_end_stone", ore_infested_end_stone);
        register("trees_end", trees_end);
    }

    private static <FC extends FeatureConfig> void register(String id, ConfiguredFeature<FC, ?> configuredFeature) {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(QueksEndMod.MODID, id), configuredFeature);
    }
}
