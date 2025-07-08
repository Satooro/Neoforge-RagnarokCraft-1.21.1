package net.satooro.ragnarokcraft.item;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.satooro.ragnarokcraft.util.ModTags;

public class ModToolTiers {
    public static final Tier RAGNARIUM = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_RAGNARIUM_TOOL,
            1400, 5f, 3f, 28, () -> Ingredient.of(ModItems.RAGNARIUM_INGOT));

}
