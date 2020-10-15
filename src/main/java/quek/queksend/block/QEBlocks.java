package quek.queksend.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import quek.queksend.QueksEndMod;
import quek.queksend.block.sapling.EndSaplingGenerator;

public class QEBlocks {

    //basic
    public static final Block infested_end_stone = new EndInfestedBlock(Blocks.END_STONE, FabricBlockSettings.copyOf(Blocks.INFESTED_STONE));
    public static final Block end_grass_block = new EndGrassBlock(FabricBlockSettings.copyOf(Blocks.END_STONE));
    public static final Block paramulite_ore = new EndOreBlock(FabricBlockSettings.copyOf(Blocks.END_STONE).lightLevel(5).breakByTool(FabricToolTags.PICKAXES, 5));
    public static final Block paramulite_block = new Block(FabricBlockSettings.copyOf(Blocks.DIAMOND_BLOCK).lightLevel(10).breakByTool(FabricToolTags.PICKAXES, 5));
    public static final Block argulum_ore = new EndOreBlock(FabricBlockSettings.copyOf(Blocks.END_STONE).breakByTool(FabricToolTags.PICKAXES, 4));
    public static final Block argulum_block = new Block(FabricBlockSettings.copyOf(Blocks.NETHERITE_BLOCK).breakByTool(FabricToolTags.PICKAXES, 4));
    public static final Block end_log = new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG));
    public static final Block end_planks = new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS));
    public static final Block end_leaves = new EndLeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES));

    //plants
    public static final Block end_sapling = new EndSaplingBlock(new EndSaplingGenerator(), FabricBlockSettings.copyOf(Blocks.OAK_SAPLING));
    public static final Block end_grass = new EndFernBlock(FabricBlockSettings.copyOf(Blocks.GRASS));
    public static final Block tall_end_grass = new TallEndPlantBlock(FabricBlockSettings.copyOf(Blocks.TALL_GRASS));
    public static final Block obversa_plant = new ObversaPlantBlock(FabricBlockSettings.copyOf(Blocks.SWEET_BERRY_BUSH));

    public static void registerAll() {
        register("infested_end_stone", infested_end_stone);
        register("end_grass_block", end_grass_block);
        register("paramulite_ore", paramulite_ore);
        register("paramulite_block", paramulite_block);
        register("argulum_ore", argulum_ore);
        register("argulum_block", argulum_block);
        register("end_log", end_log);
        register("end_planks", end_planks);
        register("end_leaves", end_leaves);
        register("end_sapling", end_sapling);
        register("end_grass", end_grass);
        register("tall_end_grass", tall_end_grass);
        registerNI("obversa_plant", obversa_plant);
    }

    private static void register(String id, Block block) {
        Registry.register(Registry.ITEM, new Identifier(QueksEndMod.MODID, id), new BlockItem(block, new FabricItemSettings().group(QueksEndMod.group)));
        Registry.register(Registry.BLOCK, new Identifier(QueksEndMod.MODID, id), block);
    }

    private static void registerNI(String id, Block block) {
        Registry.register(Registry.BLOCK, new Identifier(QueksEndMod.MODID, id), block);
    }
}
