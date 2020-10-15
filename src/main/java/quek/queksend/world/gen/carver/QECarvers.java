package quek.queksend.world.gen.carver;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.carver.Carver;
import net.minecraft.world.gen.carver.CarverConfig;
import quek.queksend.QueksEndMod;

public class QECarvers {

    public static final Carver<ProbabilityConfig> end_carver = new EndCaveCarver(ProbabilityConfig.CODEC, 256);

    public static void registerAll() {
        register("end_carver", end_carver);
    }

    private static <C extends CarverConfig, F extends Carver<C>> void register(String id, F carver) {
        Registry.register(Registry.CARVER, new Identifier(QueksEndMod.MODID, id), carver);
    }
}
