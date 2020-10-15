package quek.queksend.world.gen.surfacebuilder;

import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import quek.queksend.QueksEndMod;
import quek.queksend.block.QEBlocks;

public class QEConfiguredSurfaceBuilders {

    public static final ConfiguredSurfaceBuilder<TernarySurfaceConfig> end_grass = SurfaceBuilder.NETHER_FOREST.withConfig(
            new TernarySurfaceConfig(QEBlocks.end_grass_block.getDefaultState(), Blocks.END_STONE.getDefaultState(), Blocks.END_STONE.getDefaultState()));
    public static final ConfiguredSurfaceBuilder<TernarySurfaceConfig> end_grass_sparse = SurfaceBuilder.NETHER_FOREST.withConfig(
            new TernarySurfaceConfig(Blocks.END_STONE.getDefaultState(), Blocks.END_STONE.getDefaultState(), QEBlocks.end_grass_block.getDefaultState()));

    public static void registerAll() {
        register("end_grass", end_grass);
        register("end_grass_sparse", end_grass_sparse);
    }

    private static <SC extends SurfaceConfig> void register(String id, ConfiguredSurfaceBuilder<SC> configuredSurfaceBuilder) {
        BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, new Identifier(QueksEndMod.MODID, id), configuredSurfaceBuilder);
    }
}
