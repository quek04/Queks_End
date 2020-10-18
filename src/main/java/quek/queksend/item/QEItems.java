package quek.queksend.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import quek.queksend.QueksEndMod;
import quek.queksend.block.QEBlocks;

public class QEItems {

    //food
    public static final Item obversa_fruit = new AliasedBlockItem(QEBlocks.obversa_plant, new FabricItemSettings()
            .group(QueksEndMod.group)
            .food(new FoodComponent.Builder()
                    .hunger(1)
                    .saturationModifier(0.2F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 200, 0), 0.5F)
                    .alwaysEdible()
                    .build()
            )
    );
    public static final Item chorus_obversa_stew = new ChorusObversaStewItem(new FabricItemSettings()
            .group(QueksEndMod.group)
            .food(new FoodComponent.Builder()
                    .hunger(5)
                    .saturationModifier(0.5F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 400, 1), 1)
                    .alwaysEdible()
                    .build()
            )
            .maxCount(1)
    );

    //resources
    public static final Item paramulite_gem = new Item(new FabricItemSettings().group(QueksEndMod.group));
    public static final Item argulum_scrap = new Item(new FabricItemSettings().group(QueksEndMod.group));
    public static final Item argulum_ingot = new Item(new FabricItemSettings().group(QueksEndMod.group));

    //armors
    public static final Item paramulite_helmet = new ParamuliteArmorItem(QEArmorMaterials.paramulite, EquipmentSlot.HEAD, new FabricItemSettings().group(QueksEndMod.group));
    public static final Item paramulite_chestplate = new ParamuliteArmorItem(QEArmorMaterials.paramulite, EquipmentSlot.CHEST, new FabricItemSettings().group(QueksEndMod.group));
    public static final Item paramulite_leggings = new ParamuliteArmorItem(QEArmorMaterials.paramulite, EquipmentSlot.LEGS, new FabricItemSettings().group(QueksEndMod.group));
    public static final Item paramulite_boots = new ParamuliteArmorItem(QEArmorMaterials.paramulite, EquipmentSlot.FEET, new FabricItemSettings().group(QueksEndMod.group));

    //tools
    public static final Item argulum_sword = new SwordItem(QEToolMaterials.argulum, 3, -2.4F, new FabricItemSettings().group(QueksEndMod.group));
    public static final Item argulum_pickaxe = new QEPickaxeItem(QEToolMaterials.argulum, 1, -2.8F, new FabricItemSettings().group(QueksEndMod.group));
    public static final Item argulum_axe = new QEAxeItem(QEToolMaterials.argulum, 6.0F, -3.1F, new FabricItemSettings().group(QueksEndMod.group));
    public static final Item argulum_shovel = new ShovelItem(QEToolMaterials.argulum, 1.5F, -3.0F, new FabricItemSettings().group(QueksEndMod.group));
    public static final Item argulum_hoe = new QEHoeItem(QEToolMaterials.argulum, -2, -1.0F, new FabricItemSettings().group(QueksEndMod.group));

    public static void registerAll() {
        register("obversa_fruit", obversa_fruit);
        register("chorus_obversa_stew", chorus_obversa_stew);
        register("paramulite_gem", paramulite_gem);
        register("argulum_scrap", argulum_scrap);
        register("argulum_ingot", argulum_ingot);
        register("paramulite_helmet", paramulite_helmet);
        register("paramulite_chestplate", paramulite_chestplate);
        register("paramulite_leggings", paramulite_leggings);
        register("paramulite_boots", paramulite_boots);
        register("argulum_sword", argulum_sword);
        register("argulum_pickaxe", argulum_pickaxe);
        register("argulum_axe", argulum_axe);
        register("argulum_shovel", argulum_shovel);
        register("argulum_hoe", argulum_hoe);
    }

    private static void register(String id, Item item) {
        Registry.register(Registry.ITEM, new Identifier(QueksEndMod.MODID, id), item);
    }
}
