package net.satooro.ragnarokcraft.block.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.satooro.ragnarokcraft.RagnarokCraftMod;
import net.satooro.ragnarokcraft.block.ModBlocks;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, RagnarokCraftMod.MOD_ID);

    public static final Supplier<BlockEntityType<PedestalBlockEntity>> PEDESTAL_BE =
            BLOCK_ENTITIES.register("pedestal_be", () -> BlockEntityType.Builder.of(
                    PedestalBlockEntity::new, ModBlocks.PEDESTAL.get()
            ).build(null));

    public static final Supplier<BlockEntityType<AssemblerBlockEntity>> ASSEMBLER_BE = BLOCK_ENTITIES.register("assembler_be", () -> BlockEntityType.Builder.of(
            AssemblerBlockEntity::new, ModBlocks.ASSEMBLER.get()
    ).build(null));

    public static final Supplier<BlockEntityType<MinerBlockEntity>> MINER_BE = BLOCK_ENTITIES.register("miner_be", () -> BlockEntityType.Builder.of(
            MinerBlockEntity::new, ModBlocks.MINER.get()
    ).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
