package quek.queksend.world.gen.trunk;

import com.mojang.serialization.Codec;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;
import quek.queksend.QueksEndMod;

public class QETrunkPlacerTypes {

    public static final TrunkPlacerType<?> end_trunk_placer = register("end_trunk_placer", EndTrunkPlacer.CODEC);

    private static <P extends TrunkPlacer> TrunkPlacerType<P> register(String id, Codec<P> codec) {
        return Registry.register(Registry.TRUNK_PLACER_TYPE, new Identifier(QueksEndMod.MODID, id), new TrunkPlacerType(codec));
    }
}
