// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

package quek.queksend.client.render.entity.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.util.math.MathHelper;
import quek.queksend.entity.passive.FlitterflyEntity;

public class FlitterflyEntityModel extends CompositeEntityModel<FlitterflyEntity> {
    private final ModelPart body;
    private final ModelPart rightWing;
    private final ModelPart leftWing;
    private final ModelPart rightAntenna;
    private final ModelPart leftAntenna;

    public FlitterflyEntityModel() {
        textureWidth = 32;
        textureHeight = 32;
        body = new ModelPart(this);
        body.setPivot(0.0F, 20.5F, 0.0F);
        setRotationAngle(body, 0.5236F, 0.0F, 0.0F);
        body.setTextureOffset(16, 0).addCuboid(-1.0F, -3.5F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);

        rightWing = new ModelPart(this);
        rightWing.setPivot(-1.0F, 0.0F, 0.0F);
        body.addChild(rightWing);
        rightWing.setTextureOffset(0, 0).addCuboid(-8.0F, -6.0F, 0.134F, 8.0F, 11.0F, 0.0F, 0.0F, false);

        leftWing = new ModelPart(this);
        leftWing.setPivot(1.0F, 0.0F, 0.0F);
        body.addChild(leftWing);
        leftWing.setTextureOffset(0, 0).addCuboid(0.0F, -6.0F, 0.134F, 8.0F, 11.0F, 0.0F, 0.0F, true);

        rightAntenna = new ModelPart(this);
        rightAntenna.setPivot(0.0F, -3.5F, -1.0F);
        body.addChild(rightAntenna);
        setRotationAngle(rightAntenna, 0.1309F, 0.0F, -0.3491F);
        rightAntenna.setTextureOffset(24, 0).addCuboid(-1.0F, -4.0F, 0.0F, 1.0F, 4.0F, 0.0F, 0.0F, false);

        leftAntenna = new ModelPart(this);
        leftAntenna.setPivot(0.0F, -3.5F, -1.0F);
        body.addChild(leftAntenna);
        setRotationAngle(leftAntenna, 0.1309F, 0.0F, 0.3491F);
        leftAntenna.setTextureOffset(24, 0).addCuboid(0.0F, -4.0F, 0.0F, 1.0F, 4.0F, 0.0F, 0.0F, false);
    }

	@Override
	public Iterable<ModelPart> getParts() {
		return ImmutableList.of(this.body);
	}

	@Override
    public void setAngles(FlitterflyEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.rightWing.yaw = MathHelper.cos(ageInTicks) * 3.1415927F * 0.15F;
        this.leftWing.pitch = this.rightWing.pitch;
        this.leftWing.yaw = -this.rightWing.yaw;
        this.leftWing.roll = this.rightWing.roll;
    }

    public void setRotationAngle(ModelPart bone, float x, float y, float z) {
        bone.pitch = x;
        bone.yaw = y;
        bone.roll = z;
    }

}