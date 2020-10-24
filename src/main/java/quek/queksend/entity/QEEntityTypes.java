package quek.queksend.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.mixin.object.builder.SpawnRestrictionAccessor;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.mob.AmbientEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import quek.queksend.QueksEndMod;
import quek.queksend.entity.passive.FlitterflyEntity;

public class QEEntityTypes {

    public static final EntityType<FlitterflyEntity> flitterfly = FabricEntityTypeBuilder.create(SpawnGroup.AMBIENT, FlitterflyEntity::new)
            .dimensions(new EntityDimensions(0.5F, 0.5F, true))
            .build();

    public static void registerAll() {
        register("flitterfly", flitterfly);

        registerAttributes();
        registerSpawnRestrictions();
    }

    private static void registerAttributes() {
        att(flitterfly, FlitterflyEntity.createFlitterflyAttributes());
    }

    private static void registerSpawnRestrictions() {
        spR(flitterfly, SpawnRestriction.Location.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING, FlitterflyEntity::canFlitterflySpawn);
    }

    private static <T extends Entity> void register(String id, EntityType<T> entityType) {
        Registry.register(Registry.ENTITY_TYPE, new Identifier(QueksEndMod.MODID, id), entityType);
    }

    private static <T extends LivingEntity> void att(EntityType<T> entityType, DefaultAttributeContainer.Builder builder) {
        FabricDefaultAttributeRegistry.register(entityType, builder);
    }

    private static <T extends MobEntity> void spR(EntityType<T> entityType, SpawnRestriction.Location location, Heightmap.Type heightmap, SpawnRestriction.SpawnPredicate<T> spawnPredicate) {
        SpawnRestrictionAccessor.callRegister(entityType, location, heightmap, spawnPredicate);
    }

}
