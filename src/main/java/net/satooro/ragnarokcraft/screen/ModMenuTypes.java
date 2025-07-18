package net.satooro.ragnarokcraft.screen;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.satooro.ragnarokcraft.RagnarokCraftMod;
import net.satooro.ragnarokcraft.screen.custom.AssemblerMenu;
import net.satooro.ragnarokcraft.screen.custom.MinerMenu;
import net.satooro.ragnarokcraft.screen.custom.PedestalMenu;

public class ModMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, RagnarokCraftMod.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<PedestalMenu>> PEDESTAL_MENU =
            registerMenuType("pedestal_menu", PedestalMenu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<AssemblerMenu>> ASSEMBLER_MENU =
            registerMenuType("assembler_menu", AssemblerMenu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<MinerMenu>> MINER_MENU =
            registerMenuType("miner_menu", MinerMenu::new);



    private static <T extends AbstractContainerMenu>DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory){
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }
    public static void register(IEventBus eventBus){
        MENUS.register(eventBus);
    }

}
