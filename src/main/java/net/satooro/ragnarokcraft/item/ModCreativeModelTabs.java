package net.satooro.ragnarokcraft.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.satooro.ragnarokcraft.RagnarokCraftMod;
import net.satooro.ragnarokcraft.block.ModBlocks;
import net.satooro.ragnarokcraft.block.entity.ModBlockEntities;
import net.satooro.ragnarokcraft.block.entity.PedestalBlockEntity;
import net.satooro.ragnarokcraft.block.entity.renderer.PedestalBlockEntityRenderer;

import java.util.function.Supplier;

public class ModCreativeModelTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RagnarokCraftMod.MOD_ID);

    public static final Supplier<CreativeModeTab> RAGNAROK_ORES = CREATIVE_MODE_TAB.register("ragnarokcraft_ores_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModBlocks.RAGNARIUM_ORE))
                    .title(Component.literal("Ragnarok Ores"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.RAW_RAGNARIUM.get());
                        output.accept(ModItems.RAW_YMIRITA.get());
                        output.accept(ModItems.RAW_THORNIUM.get());
                        output.accept(ModItems.RAW_MIMIR_HEART.get());
                        output.accept(ModItems.RAW_MUSPELIUM.get());
//                        output.accept(ModItems.RAW_NIFLACRIL.get());
                        output.accept(ModItems.RAGNARIUM_INGOT.get());
                        output.accept(ModItems.YMIRITA_INGOT.get());
                        output.accept(ModItems.THORNIUM_INGOT.get());
                        output.accept(ModItems.MIMIR_HEART_INGOT.get());
                        output.accept(ModItems.MUSPELIUM_INGOT.get());
                        output.accept(ModBlocks.RAGNARIUM_BLOCK);
                        output.accept(ModBlocks.YMIRITA_BLOCK);
                        output.accept(ModBlocks.THORNIUM_BLOCK);
                        output.accept(ModBlocks.MIMIR_HEART_BLOCK);
                        output.accept(ModBlocks.MUSPELIUM_BLOCK);
//                        output.accept(ModBlocks.NIFLACRIL_BLOCK);
                        output.accept(ModBlocks.RAGNARIUM_ORE);
                        output.accept(ModBlocks.YMIRITA_ORE);
                        output.accept(ModBlocks.THORNIUM_ORE);
                        output.accept(ModBlocks.MIMIR_HEART_ORE);
                        output.accept(ModBlocks.MUSPELIUM_ORE);
//                        output.accept(ModBlocks.NIFLACRIL_ORE);
                    })
                    .build());

    public static final Supplier<CreativeModeTab> RAGNAROK_TAB = CREATIVE_MODE_TAB.register("ragnarokcraft_tools_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.YMIRITA_HELMET.get()))
                    .title(Component.literal("Ragnarok Tools"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.CHISEL);
                        output.accept(ModItems.TESTE_ITEM.get());
                        output.accept(ModItems.YMIRITA_HELMET);
                        output.accept(ModItems.YMIRITA_CHESTPLATE);
                        output.accept(ModItems.YMIRITA_LEGGINGS);
                        output.accept(ModItems.YMIRITA_BOOTS);

                        output.accept(ModItems.RAGNARIUM_PICKAXE);
                        output.accept(ModItems.RAGNARIUM_SWORD);
                    })
                    .build());

    public static final Supplier<CreativeModeTab> RAGNAROK_ITEMS = CREATIVE_MODE_TAB.register("ragnarokcraft_items_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModBlocks.PEDESTAL))
                    .title(Component.literal("Ragnarok Machines"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.PEDESTAL);
                        output.accept(ModBlocks.ASSEMBLER);
                        output.accept(ModItems.SPEED_UPGRADE.get());
                    })
                    .build());

    public static void register (IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
