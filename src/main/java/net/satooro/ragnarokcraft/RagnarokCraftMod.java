package net.satooro.ragnarokcraft;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.satooro.ragnarokcraft.block.ModBlocks;
import net.satooro.ragnarokcraft.block.entity.ModBlockEntities;
import net.satooro.ragnarokcraft.block.entity.renderer.PedestalBlockEntityRenderer;
import net.satooro.ragnarokcraft.component.ModDataComponents;
import net.satooro.ragnarokcraft.item.ModCreativeModelTabs;
import net.satooro.ragnarokcraft.item.ModItems;
import net.satooro.ragnarokcraft.screen.ModMenuTypes;
import net.satooro.ragnarokcraft.screen.custom.AssemblerMenu;
import net.satooro.ragnarokcraft.screen.custom.AssemblerScreen;
import net.satooro.ragnarokcraft.screen.custom.PedestalScreen;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(RagnarokCraftMod.MOD_ID)
public class RagnarokCraftMod {
    public static final String MOD_ID = "ragnarokcraft";
    private static final Logger LOGGER = LogUtils.getLogger();

    public RagnarokCraftMod(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        ModCreativeModelTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);

        ModDataComponents.register(modEventBus);

        ModBlockEntities.register(modEventBus);

        ModMenuTypes.register(modEventBus);

        modEventBus.addListener(this::addCreative);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.RAW_YMIRITA.get());
        }
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.RAGNARIUM_BLOCK.get());
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
        @SubscribeEvent
        public static void registerBER(EntityRenderersEvent.RegisterRenderers event){event.registerBlockEntityRenderer(ModBlockEntities.PEDESTAL_BE.get(), PedestalBlockEntityRenderer::new);}

        @SubscribeEvent
        public static void registerScreens(RegisterMenuScreensEvent event){
            event.register(ModMenuTypes.PEDESTAL_MENU.get(), PedestalScreen::new);
            event.register(ModMenuTypes.ASSEMBLER_MENU.get(), AssemblerScreen::new);
        }
    }
}
