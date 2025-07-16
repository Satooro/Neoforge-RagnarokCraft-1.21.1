package net.satooro.ragnarokcraft.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.satooro.ragnarokcraft.item.ModItems;
import net.satooro.ragnarokcraft.screen.custom.MinerMenu;
import net.satooro.ragnarokcraft.util.ModUtils;
import org.jetbrains.annotations.Nullable;

public class MinerBlockEntity extends BlockEntity implements MenuProvider {

    public final ItemStackHandler itemStackHandler = new ItemStackHandler(17){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            return switch (slot){
                case 9 -> (stack.getItem() instanceof PickaxeItem || stack.getItem() instanceof EnchantedBookItem);
                case 10 -> stack.getItem() == ModItems.TESTE_ITEM.get();
                case 11 -> ModUtils.isItemAnComponent(stack);
                case 12 -> false;
                case 13, 14, 15, 16 -> ModUtils.isItemAnUpgrade(stack);
                default -> super.isItemValid(slot, stack);
            };
        }

        @Override
        public int getSlotLimit(int slot) {
            if(slot >= 13 && slot <= 16){
                return 4;
            }
            return super.getSlotLimit(slot);
        };

        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            switch (slot){
                case 9, 10, 11, 12, 13, 14, 15, 16 -> {
                    return ItemStack.EMPTY;
                }
            }
            return super.extractItem(slot, amount, simulate);
        }
    };


    private static final int PICK_OR_BOOK_SLOT = 9;
    private static final int COMPONENT_SLOT = 11;
    private static final int[] OUTPUT_SLOT = {0, 1,2,3,4,5,6,7,8};
    private static final int[] UPGRADE_SLOT = {13,14,15,16};

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 1200;

    public MinerBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.MINER_BE.get(), pos, blockState);
        data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i){
                    case 0 -> MinerBlockEntity.this.progress;
                    case 1 -> MinerBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int i1) {
                switch (i){
                    case 0 -> MinerBlockEntity.this.progress = i1;
                    case 1 -> MinerBlockEntity.this.maxProgress = i1;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
    public void drops(){
        SimpleContainer inv = new SimpleContainer(itemStackHandler.getSlots());
        for(int i = 0; i < itemStackHandler.getSlots(); i++){
            inv.setItem(i, itemStackHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.ragnarokcraft.miner");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new MinerMenu(i, inventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", itemStackHandler.serializeNBT(registries));
        tag.putInt("miner.progress", progress);
        tag.putInt("miner.max_progress", maxProgress);

        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        itemStackHandler.deserializeNBT(registries, tag.getCompound("inventory"));
        progress = tag.getInt("miner.progress");
        maxProgress = tag.getInt("miner.max_progress");
    }

    public void tick(Level level1, BlockPos pPos, BlockState state1) {
    }
}
