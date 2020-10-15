package quek.queksend.tag;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import quek.queksend.QueksEndMod;

public class QEBlockTags {

    public static final Tag<Block> obversa_plantable = TagRegistry.block(new Identifier(QueksEndMod.MODID, "obversa_plantable"));
    public static final Tag<Block> chorus_plantable = TagRegistry.block(new Identifier(QueksEndMod.MODID, "chorus_plantable"));
    public static final Tag<Block> generic_end_plantable = TagRegistry.block(new Identifier(QueksEndMod.MODID, "generic_end_plantable"));
}
