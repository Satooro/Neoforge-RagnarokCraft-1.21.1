package net.satooro.ragnarokcraft.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.satooro.ragnarokcraft.RagnarokCraftMod;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_RAGNARIUM_TOOL = createTag("needs_ragnarium_tool");
        public static final TagKey<Block> INCORRECT_FOR_RAGNARIUM_TOOL = createTag("incorrect_for_ragnarium_tool");


        public static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(RagnarokCraftMod.MOD_ID, name));

        }
    }

    public static class Items {
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");

        public static TagKey<Item> createTag(String name){
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(RagnarokCraftMod.MOD_ID, name));
        }
    }
}
