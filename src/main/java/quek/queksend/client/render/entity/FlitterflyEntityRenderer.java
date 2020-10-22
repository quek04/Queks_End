package quek.queksend.client.render.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import quek.queksend.QueksEndMod;
import quek.queksend.client.render.entity.feature.FlitterflyWingsFeatureRenderer;
import quek.queksend.client.render.entity.model.FlitterflyEntityModel;
import quek.queksend.entity.passive.FlitterflyEntity;

@Environment(EnvType.CLIENT)
public class FlitterflyEntityRenderer extends MobEntityRenderer<FlitterflyEntity, FlitterflyEntityModel<FlitterflyEntity>> {
    public FlitterflyEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new FlitterflyEntityModel(), 0.5F);
        this.addFeature(new FlitterflyWingsFeatureRenderer<>(this));
    }

    @Override
    public Identifier getTexture(FlitterflyEntity entity) {
        return new Identifier(QueksEndMod.MODID, "textures/entity/flitterfly/flitterfly.png");
    }
}
