package quek.queksend;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import quek.queksend.block.QEBlocks;
import quek.queksend.item.QEItems;
import quek.queksend.world.gen.carver.QECarvers;
import quek.queksend.world.gen.carver.QEConfiguredCarvers;
import quek.queksend.world.gen.feature.QEConfiguredFeatures;
import quek.queksend.world.gen.feature.QEFeatures;
import quek.queksend.world.gen.surfacebuilder.QEConfiguredSurfaceBuilders;

public class QueksEndMod implements ModInitializer {

    public static final String MODID = "queks_end";

    @Override
    public void onInitialize() {
        QEBlocks.registerAll();
        QEItems.registerAll();
        QEFeatures.registerAll();
        QEConfiguredFeatures.registerAll();
        QEConfiguredSurfaceBuilders.registerAll();
        QECarvers.registerAll();
        QEConfiguredCarvers.registerAll();

        QEBehaviors.all();
    }

    public static final ItemGroup group = FabricItemGroupBuilder.build(new Identifier(QueksEndMod.MODID, "group"), () -> new ItemStack(QEItems.paramulite_gem));
}
