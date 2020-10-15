package quek.queksend.world.gen.carver;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.carver.CarverConfig;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import quek.queksend.QueksEndMod;

public class QEConfiguredCarvers {

    public static final ConfiguredCarver<ProbabilityConfig> end_cave = QECarvers.end_carver.method_28614(new ProbabilityConfig(0.2F));

    public static void registerAll() {
        register("end_cave", end_cave);
    }

    private static <WC extends CarverConfig> void register(String id, ConfiguredCarver<WC> configuredCarver) {
        BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_CARVER, new Identifier(QueksEndMod.MODID, id), configuredCarver);
    }
}
