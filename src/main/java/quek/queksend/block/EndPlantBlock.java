package quek.queksend.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import quek.queksend.tag.QEBlockTags;

public class EndPlantBlock extends PlantBlock {

    protected EndPlantBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(QEBlockTags.generic_end_plantable);
    }
}
