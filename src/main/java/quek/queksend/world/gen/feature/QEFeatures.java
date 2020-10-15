package quek.queksend.world.gen.feature;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import quek.queksend.QueksEndMod;

public class QEFeatures {

    public static final Feature<TreeFeatureConfig> end_tree = new EndTreeFeature(TreeFeatureConfig.CODEC);

    public static void registerAll() {
        register("end_tree", end_tree);
    }

    private static <C extends FeatureConfig, F extends Feature<C>> void register(String id, F feature) {
        Registry.register(Registry.FEATURE, new Identifier(QueksEndMod.MODID, id), feature);
    }
}
