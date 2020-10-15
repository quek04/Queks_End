package quek.queksend.tag;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import quek.queksend.QueksEndMod;

public class QEItemTags {

    public static final Tag<Item> end_logs = TagRegistry.item(new Identifier(QueksEndMod.MODID, "end_logs"));
}
