package net.satooro.ragnarokcraft.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.satooro.ragnarokcraft.block.ModBlocks;
import net.satooro.ragnarokcraft.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.YMIRITA_HELMET.get())
                .pattern("XXX")
                .pattern("X X")
                .define('X', ModItems.YMIRITA_INGOT.get())
                .unlockedBy("has_ymirita", has(ModItems.YMIRITA_INGOT)).save(recipeOutput);
    }
}
