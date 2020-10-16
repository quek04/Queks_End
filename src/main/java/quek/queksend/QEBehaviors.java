package quek.queksend;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.ComposterBlock;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemConvertible;
import quek.queksend.block.QEBlocks;
import quek.queksend.item.QEItems;

import java.util.Map;

public class QEBehaviors {

    public static void all() {
        logStrips();
        composterFuels();
    }

    private static void logStrips() {
        Map<Block, Block> qeStrips = Maps.newHashMap(AxeItem.STRIPPED_BLOCKS);

        qeStrips.put(QEBlocks.end_log, QEBlocks.stripped_end_log);
        qeStrips.put(QEBlocks.end_wood, QEBlocks.stripped_end_wood);

        AxeItem.STRIPPED_BLOCKS = ImmutableMap.copyOf(qeStrips);
    }

    private static void composterFuels() {
        c(0.3F, QEItems.obversa_fruit);
        c(0.3F, QEBlocks.end_sapling);
        c(0.3F, QEBlocks.end_grass);
        c(0.3F, QEBlocks.tall_end_grass);
    }

    private static void c(float levelIncreaseChance, ItemConvertible item) {
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(item.asItem(), levelIncreaseChance);
    }
}
