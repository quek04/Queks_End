package quek.queksend.client.render.entity.feature;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import quek.queksend.QueksEndMod;
import quek.queksend.client.render.entity.model.FlitterflyEntityModel;

@Environment(EnvType.CLIENT)
public class FlitterflyWingsFeatureRenderer<T extends LivingEntity> extends EyesFeatureRenderer<T, FlitterflyEntityModel<T>> {

    public FlitterflyWingsFeatureRenderer(FeatureRendererContext<T, FlitterflyEntityModel<T>> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public RenderLayer getEyesTexture() {
        return RenderLayer.getEyes(new Identifier(QueksEndMod.MODID, "textures/entity/flitterfly/flitterfly_wings.png"));
    }
}
