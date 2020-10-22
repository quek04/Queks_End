package quek.queksend.tag;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import quek.queksend.QueksEndMod;

public class QEEntityTags {

    public static final Tag<EntityType<?>> immune_to_obversa = TagRegistry.entityType(new Identifier(QueksEndMod.MODID, "immune_to_obversa"));
}
