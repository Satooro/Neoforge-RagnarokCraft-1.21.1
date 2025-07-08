package net.satooro.ragnarokcraft.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;
import net.satooro.ragnarokcraft.RagnarokCraftMod;
import net.satooro.ragnarokcraft.item.ModItems;

public class ModelItemModelProvider extends ItemModelProvider {
    public ModelItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, RagnarokCraftMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.CHISEL.get());
        basicItem(ModItems.TESTE_ITEM.get());

        basicItem(ModItems.RAGNARIUM_INGOT.get());
        basicItem(ModItems.YMIRITA_INGOT.get());
        basicItem(ModItems.THORNIUM_INGOT.get());
        basicItem(ModItems.MIMIR_HEART_INGOT.get());
        basicItem(ModItems.MUSPELIUM_INGOT.get());


        basicItem(ModItems.RAW_RAGNARIUM.get());
        basicItem(ModItems.RAW_YMIRITA.get());
        basicItem(ModItems.RAW_THORNIUM.get());
        basicItem(ModItems.RAW_MIMIR_HEART.get());
        basicItem(ModItems.RAW_MUSPELIUM.get());

        basicItem(ModItems.RAGNARIUM_INGOT.get());
        basicItem(ModItems.YMIRITA_INGOT.get());
        basicItem(ModItems.THORNIUM_INGOT.get());
        basicItem(ModItems.MIMIR_HEART_INGOT.get());
        basicItem(ModItems.MUSPELIUM_INGOT.get());

        basicItem(ModItems.YMIRITA_HELMET.get());
        basicItem(ModItems.YMIRITA_CHESTPLATE.get());
        basicItem(ModItems.YMIRITA_LEGGINGS.get());
        basicItem(ModItems.YMIRITA_BOOTS.get());

        handleItem(ModItems.RAGNARIUM_SWORD);
        handleItem(ModItems.RAGNARIUM_PICKAXE);

    }
    private ItemModelBuilder handleItem(DeferredItem<?> item){
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(RagnarokCraftMod.MOD_ID, "item/" + item.getId().getPath()));
    }
}
