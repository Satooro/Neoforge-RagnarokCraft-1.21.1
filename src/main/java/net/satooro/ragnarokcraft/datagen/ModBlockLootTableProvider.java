package net.satooro.ragnarokcraft.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.satooro.ragnarokcraft.block.ModBlocks;
import net.satooro.ragnarokcraft.item.ModItems;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.MUSPELIUM_BLOCK.get());
        dropSelf(ModBlocks.RAGNARIUM_BLOCK.get());
        dropSelf(ModBlocks.THORNIUM_BLOCK.get());
        dropSelf(ModBlocks.YMIRITA_BLOCK.get());
        dropSelf(ModBlocks.MIMIR_HEART_BLOCK.get());

        dropSelf(ModBlocks.PEDESTAL.get());
        dropSelf(ModBlocks.ASSEMBLER.get());
        dropSelf(ModBlocks.MINER.get());

        add(ModBlocks.RAGNARIUM_ORE.get(), block -> createOreDrop(ModBlocks.RAGNARIUM_ORE.get(), ModItems.RAW_RAGNARIUM.get()));
        add(ModBlocks.YMIRITA_ORE.get(), block -> createOreDrop(ModBlocks.YMIRITA_ORE.get(), ModItems.RAW_YMIRITA.get()));
        add(ModBlocks.THORNIUM_ORE.get(), block -> createOreDrop(ModBlocks.THORNIUM_ORE.get(), ModItems.RAW_THORNIUM.get()));
        add(ModBlocks.MIMIR_HEART_ORE.get(), block -> createOreDrop(ModBlocks.MIMIR_HEART_ORE.get(), ModItems.RAW_MIMIR_HEART.get()));
        add(ModBlocks.MUSPELIUM_ORE.get(), block -> createOreDrop(ModBlocks.MUSPELIUM_ORE.get(), ModItems.RAW_MUSPELIUM.get()));
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
