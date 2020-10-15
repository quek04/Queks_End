package quek.queksend.mixin;

import net.minecraft.block.ChorusFlowerBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ChorusPlantFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import quek.queksend.tag.QEBlockTags;

import java.util.Random;

@Mixin(ChorusPlantFeature.class)
public class ChorusPlantFeatureMixin {

    @Inject(method = "generate", at = @At("INVOKE"), cancellable = true)
    private void generate(StructureWorldAccess structureWorldAccess, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, DefaultFeatureConfig defaultFeatureConfig, CallbackInfoReturnable<Boolean> cir) {
        if (structureWorldAccess.isAir(blockPos) && structureWorldAccess.getBlockState(blockPos.down()).isIn(QEBlockTags.chorus_plantable)) {
            ChorusFlowerBlock.generate(structureWorldAccess, blockPos, random, 8);
            cir.setReturnValue(true);
        } else {
            cir.setReturnValue(false);
        }
    }
}
