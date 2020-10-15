package quek.queksend.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class EndLeavesBlock extends LeavesBlock {

    public EndLeavesBlock(Settings settings) {
        super(settings);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        for(int i = 0; i < 3; ++i) {
            int j = random.nextInt(2) * 2 - 1;
            int k = random.nextInt(2) * 2 - 1;
            double d = (double)pos.getX() + 0.5D + 0.25D * (double)j;
            double e = (float)pos.getY() + random.nextFloat();
            double f = (double)pos.getZ() + 0.5D + 0.25D * (double)k;
            double g = random.nextFloat() * (float)j;
            double h = ((double)random.nextFloat() - 0.5D) * 0.125D;
            double l = random.nextFloat() * (float)k;
            world.addParticle(ParticleTypes.PORTAL, d, e, f, g, h, l);
        }
    }
}
