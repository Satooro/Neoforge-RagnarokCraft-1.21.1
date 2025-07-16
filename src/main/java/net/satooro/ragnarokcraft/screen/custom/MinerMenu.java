package net.satooro.ragnarokcraft.screen.custom;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.satooro.ragnarokcraft.block.ModBlocks;
import net.satooro.ragnarokcraft.block.entity.MinerBlockEntity;
import net.satooro.ragnarokcraft.screen.ModMenuTypes;
import net.satooro.ragnarokcraft.slots.InputSlot;
import net.satooro.ragnarokcraft.slots.ResultSlot;
import net.satooro.ragnarokcraft.slots.UpgradeSlot;

public class MinerMenu extends AbstractContainerMenu {
    public final MinerBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public MinerMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(2));
    }
    public MinerMenu(int containerId, Inventory inv, BlockEntity entity, ContainerData data){
        super(ModMenuTypes.MINER_MENU.get(), containerId);
        checkContainerSize(inv, 16);
        this.blockEntity = ((MinerBlockEntity) entity);
        this.level = inv.player.level();
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.addSlot(new ResultSlot(this.blockEntity.itemStackHandler, 0, 83, 17));
        this.addSlot(new ResultSlot(this.blockEntity.itemStackHandler, 1, 101, 17));
        this.addSlot(new ResultSlot(this.blockEntity.itemStackHandler, 2, 119, 17));
        this.addSlot(new ResultSlot(this.blockEntity.itemStackHandler, 3, 83, 35));
        this.addSlot(new ResultSlot(this.blockEntity.itemStackHandler, 4, 101, 35));
        this.addSlot(new ResultSlot(this.blockEntity.itemStackHandler, 5, 119, 35));
        this.addSlot(new ResultSlot(this.blockEntity.itemStackHandler, 6, 83, 53));
        this.addSlot(new ResultSlot(this.blockEntity.itemStackHandler, 7, 101, 53));
        this.addSlot(new ResultSlot(this.blockEntity.itemStackHandler, 8, 119, 53));

        // Pickaxe
        this.addSlot(new InputSlot(this.blockEntity.itemStackHandler, 9, 47, 17));
        // Linking - Cu aberto
        this.addSlot(new InputSlot(this.blockEntity.itemStackHandler, 10, 38, 35));
        // Component
        this.addSlot(new InputSlot(this.blockEntity.itemStackHandler, 11, 56, 35));
        // Coal
        this.addSlot(new InputSlot(this.blockEntity.itemStackHandler, 12, 47, 62));

        // Upgrade Slots
        this.addSlot(new UpgradeSlot(this.blockEntity.itemStackHandler, 13, 6, 8));
        this.addSlot(new UpgradeSlot(this.blockEntity.itemStackHandler, 14, 6, 26));
        this.addSlot(new UpgradeSlot(this.blockEntity.itemStackHandler, 15, 6, 44));
        this.addSlot(new UpgradeSlot(this.blockEntity.itemStackHandler, 16, 6, 62));

        addDataSlots(data);
    }

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 16;  // must be the number of slots you have!
    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                                                                             + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, ModBlocks.MINER.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
