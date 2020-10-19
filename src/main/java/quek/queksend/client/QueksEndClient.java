package quek.queksend.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.EntityType;
import quek.queksend.block.QEBlocks;
import quek.queksend.client.render.entity.FlitterflyEntityRenderer;
import quek.queksend.entity.QEEntityTypes;

import java.awt.*;

public class QueksEndClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        renderRenderLayers();
        registerEntityRenders();

        ColorProviderRegistry.BLOCK.register(((state, world, pos, tintIndex) ->
                    world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : new Color(155, 97, 160).getRGB()),
                QEBlocks.end_grass_block,
                QEBlocks.end_grass,
                QEBlocks.tall_end_grass,
                QEBlocks.end_leaves
        );

        ColorProviderRegistry.ITEM.register(((stack, tintIndex) ->
                        new Color(155, 97, 160).getRGB()),
                QEBlocks.end_grass_block,
                QEBlocks.end_grass,
                QEBlocks.tall_end_grass,
                QEBlocks.end_leaves
        );
    }

    private static void renderRenderLayers() {
        RenderLayer cutout = RenderLayer.getCutout();
        RenderLayer mipped = RenderLayer.getCutoutMipped();
        RenderLayer translucent = RenderLayer.getTranslucent();

        registerRenderLayer(QEBlocks.end_grass_block, mipped);
        registerRenderLayer(QEBlocks.end_grass, cutout);
        registerRenderLayer(QEBlocks.tall_end_grass, cutout);
        registerRenderLayer(QEBlocks.obversa_plant, cutout);
        registerRenderLayer(QEBlocks.end_sapling, cutout);
    }

    private static void registerRenderLayer(Block block, RenderLayer renderLayer) {
        BlockRenderLayerMap.INSTANCE.putBlock(block, renderLayer);
    }

    private static void registerEntityRenders() {
        eR(QEEntityTypes.flitterfly, ((entityRenderDispatcher, context) -> new FlitterflyEntityRenderer(entityRenderDispatcher)));
    }

    private static void eR(EntityType<?> entityType, EntityRendererRegistry.Factory factory) {
        EntityRendererRegistry.INSTANCE.register(entityType, factory);
    }
}
