package net.satooro.ragnarokcraft.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.satooro.ragnarokcraft.RagnarokCraftMod;
import net.satooro.ragnarokcraft.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, RagnarokCraftMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.RAGNARIUM_BLOCK);
        blockWithItem(ModBlocks.YMIRITA_BLOCK);
//        blockWithItem(ModBlocks.THORNIUM_BLOCK);
        blockWithItem(ModBlocks.MIMIR_HEART_BLOCK);
        blockWithItem(ModBlocks.MUSPELIUM_BLOCK);

        blockWithItem(ModBlocks.RAGNARIUM_ORE);
        blockWithItem(ModBlocks.YMIRITA_ORE);
        blockWithItem(ModBlocks.THORNIUM_ORE);
        blockWithItem(ModBlocks.MIMIR_HEART_ORE);
        blockWithItem(ModBlocks.MUSPELIUM_ORE);

    }

    private void blockWithItem(DeferredBlock<?> deferredBlock){
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
    private void blockItem(DeferredBlock<?> deferredBlock){
        simpleBlockWithItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("ragnarokcraft:block/" + deferredBlock.getId().getPath()));
    }
    private void blockItem(DeferredBlock<?> deferredBlock, String appendix){
        simpleBlockWithItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("ragnarokcraft:block/" + deferredBlock.getId().getPath() + appendix));
    }
}
