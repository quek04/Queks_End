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
    public static final Block stripped_end_log = new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_LOG));
    public static final Block end_wood = new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD));
    public static final Block stripped_end_wood = new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_WOOD));
    public static final Block end_planks = new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS));
    public static final Block end_slab = new SlabBlock(FabricBlockSettings.copyOf(Blocks.OAK_SLAB));
    public static final Block end_stairs = new EndStairsBlock(end_planks.getDefaultState(), FabricBlockSettings.copyOf(Blocks.OAK_STAIRS));
    public static final Block end_fence = new FenceBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE));
    public static final Block end_fence_gate = new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE_GATE));
    public static final Block end_pressure_plate = new EndPressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copyOf(Blocks.OAK_PRESSURE_PLATE));
    public static final Block end_button = new EndButtonBlock(FabricBlockSettings.copyOf(Blocks.OAK_BUTTON));
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
        register("stripped_end_log", stripped_end_log);
        register("end_wood", end_wood);
        register("stripped_end_wood", stripped_end_wood);
        register("end_planks", end_planks);
        register("end_slab", end_slab);
        register("end_stairs", end_stairs);
        register("end_fence", end_fence);
        register("end_fence_gate", end_fence_gate);
        register("end_pressure_plate", end_pressure_plate);
        register("end_button", end_button);
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
