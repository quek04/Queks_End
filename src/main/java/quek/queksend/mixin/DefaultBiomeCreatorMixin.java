package quek.queksend.mixin;

import net.minecraft.entity.SpawnGroup;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.ConfiguredStructureFeatures;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import quek.queksend.entity.QEEntityTypes;
import quek.queksend.world.gen.carver.QEConfiguredCarvers;
import quek.queksend.world.gen.feature.QEConfiguredFeatures;
import quek.queksend.world.gen.surfacebuilder.QEConfiguredSurfaceBuilders;

import java.awt.*;

@Mixin(DefaultBiomeCreator.class)
public abstract class DefaultBiomeCreatorMixin {

    @Unique
    private static Biome composeEndSpawnSettings(GenerationSettings.Builder builder, int grass) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addEndMobs(spawnSettings);
        return (new Biome.Builder())
                .precipitation(Biome.Precipitation.NONE)
                .category(Biome.Category.THEEND)
                .depth(0.1F)
                .scale(0.2F)
                .temperature(0.5F)
                .downfall(0.5F)
                .effects((new BiomeEffects.Builder())
                        .grassColor(grass)
                        .waterColor(4159204)
                        .waterFogColor(329011)
                        .fogColor(10518688)
                        .skyColor(0)
                        .moodSound(BiomeMoodSound.CAVE)
                        .build())
                .spawnSettings(spawnSettings
                        .spawn(SpawnGroup.AMBIENT, new SpawnSettings.SpawnEntry(QEEntityTypes.flitterfly, 5, 1, 4))
                        .build())
                .generationSettings(builder
                        .carver(GenerationStep.Carver.AIR, QEConfiguredCarvers.end_cave)
                        .feature(GenerationStep.Feature.UNDERGROUND_ORES, QEConfiguredFeatures.ore_infested_end_stone)
                        .feature(GenerationStep.Feature.VEGETAL_DECORATION, QEConfiguredFeatures.patch_obversa_fruit)
                        .feature(GenerationStep.Feature.VEGETAL_DECORATION, QEConfiguredFeatures.patch_end_grass)
                        .build())
                .build();
    }

    @Inject(method = "createTheEnd", at = @At("INVOKE"), cancellable = true)
    private static void createTheEndInject(CallbackInfoReturnable<Biome> cir) {
        GenerationSettings.Builder builder = (new GenerationSettings.Builder())
                .surfaceBuilder(QEConfiguredSurfaceBuilders.end_grass_sparse)
                .feature(GenerationStep.Feature.SURFACE_STRUCTURES, ConfiguredFeatures.END_SPIKE)
                ;

        cir.setReturnValue(composeEndSpawnSettings(builder, new Color(79, 65, 80).getRGB()));
    }

    @Inject(method = "createEndBarrens", at = @At("INVOKE"), cancellable = true)
    private static void createEndBarrensInject(CallbackInfoReturnable<Biome> cir) {
        GenerationSettings.Builder builder = (new GenerationSettings.Builder())
                .surfaceBuilder(QEConfiguredSurfaceBuilders.end_grass_sparse)
                .feature(GenerationStep.Feature.UNDERGROUND_ORES, QEConfiguredFeatures.ore_paramulite)
                .feature(GenerationStep.Feature.UNDERGROUND_ORES, QEConfiguredFeatures.ore_argulum)
                ;

        cir.setReturnValue(composeEndSpawnSettings(builder, new Color(79, 65, 80).getRGB()));
    }

    @Inject(method = "createSmallEndIslands", at = @At("INVOKE"), cancellable = true)
    private static void createSmallEndIslandsInject(CallbackInfoReturnable<Biome> cir) {
        GenerationSettings.Builder builder = (new GenerationSettings.Builder())
                .surfaceBuilder(QEConfiguredSurfaceBuilders.end_grass_sparse)
                .feature(GenerationStep.Feature.UNDERGROUND_ORES, QEConfiguredFeatures.ore_paramulite)
                .feature(GenerationStep.Feature.UNDERGROUND_ORES, QEConfiguredFeatures.ore_argulum)
                .feature(GenerationStep.Feature.RAW_GENERATION, ConfiguredFeatures.END_ISLAND_DECORATED)
                ;

        cir.setReturnValue(composeEndSpawnSettings(builder, new Color(79, 65, 80).getRGB()));
    }

    @Inject(method = "createEndMidlands", at = @At("INVOKE"), cancellable = true)
    private static void createEndMidlandsInject(CallbackInfoReturnable<Biome> cir) {
        GenerationSettings.Builder builder = (new GenerationSettings.Builder())
                .surfaceBuilder(QEConfiguredSurfaceBuilders.end_grass)
                .feature(GenerationStep.Feature.UNDERGROUND_ORES, QEConfiguredFeatures.ore_paramulite)
                .feature(GenerationStep.Feature.UNDERGROUND_ORES, QEConfiguredFeatures.ore_argulum)
                .structureFeature(ConfiguredStructureFeatures.END_CITY)
                ;

        cir.setReturnValue(composeEndSpawnSettings(builder, new Color(155, 97, 160).getRGB()));
    }

    @Inject(method = "createEndHighlands", at = @At("INVOKE"), cancellable = true)
    private static void createEndHighlandsInject(CallbackInfoReturnable<Biome> cir) {
        GenerationSettings.Builder builder = (new GenerationSettings.Builder())
                .surfaceBuilder(QEConfiguredSurfaceBuilders.end_grass)
                .feature(GenerationStep.Feature.UNDERGROUND_ORES, QEConfiguredFeatures.ore_paramulite)
                .feature(GenerationStep.Feature.UNDERGROUND_ORES, QEConfiguredFeatures.ore_argulum)
                .feature(GenerationStep.Feature.VEGETAL_DECORATION, QEConfiguredFeatures.patch_tall_end_grass)
                .feature(GenerationStep.Feature.SURFACE_STRUCTURES, ConfiguredFeatures.END_GATEWAY)
                .feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.CHORUS_PLANT)
                .feature(GenerationStep.Feature.VEGETAL_DECORATION, QEConfiguredFeatures.trees_end)
                .structureFeature(ConfiguredStructureFeatures.END_CITY)
                ;

        cir.setReturnValue(composeEndSpawnSettings(builder, new Color(201, 103, 208).getRGB()));
    }
}
