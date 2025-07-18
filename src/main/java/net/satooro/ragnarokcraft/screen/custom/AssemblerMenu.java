package net.satooro.ragnarokcraft.screen.custom;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.satooro.ragnarokcraft.block.ModBlocks;
import net.satooro.ragnarokcraft.block.entity.AssemblerBlockEntity;
import net.satooro.ragnarokcraft.screen.ModMenuTypes;
import net.satooro.ragnarokcraft.slots.InputSlot;
import net.satooro.ragnarokcraft.slots.ResultSlot;
import net.satooro.ragnarokcraft.slots.UpgradeSlot;

public class AssemblerMenu extends AbstractContainerMenu {
    public final AssemblerBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public AssemblerMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(2));
    }

    public AssemblerMenu(int containerId, Inventory inv, BlockEntity entity, ContainerData data){
        super(ModMenuTypes.ASSEMBLER_MENU.get(), containerId);
        checkContainerSize(inv, 14);
        this.blockEntity = ((AssemblerBlockEntity) entity);
        this.level = inv.player.level();
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        // INPUTS
        this.addSlot(new InputSlot(this.blockEntity.itemHandler, 0, 49, 11));
        this.addSlot(new InputSlot(this.blockEntity.itemHandler, 1, 70, 11));
        this.addSlot(new InputSlot(this.blockEntity.itemHandler, 2, 91, 11));
        this.addSlot(new InputSlot(this.blockEntity.itemHandler, 3, 112, 11));
        this.addSlot(new InputSlot(this.blockEntity.itemHandler, 4, 49, 61));
        this.addSlot(new InputSlot(this.blockEntity.itemHandler, 5, 70, 61));
        this.addSlot(new InputSlot(this.blockEntity.itemHandler, 6, 91, 61));
        this.addSlot(new InputSlot(this.blockEntity.itemHandler, 7, 112, 61));

        // OUTPUT
        this.addSlot(new ResultSlot(this.blockEntity.itemHandler, 8, 127, 36));

        // COMPONENT
        this.addSlot(new SlotItemHandler(this.blockEntity.itemHandler, 9, 34, 36));

        // UPGRADES
        this.addSlot(new UpgradeSlot(this.blockEntity.itemHandler, 10, 7, 8));
        this.addSlot(new UpgradeSlot(this.blockEntity.itemHandler, 11, 7, 26));
        this.addSlot(new UpgradeSlot(this.blockEntity.itemHandler, 12, 7, 44));
        this.addSlot(new UpgradeSlot(this.blockEntity.itemHandler, 13, 7, 62));

        addDataSlots(data);
    }
    public boolean isCrafting(){
        return data.get(0) > 0;
    }
    public int getScaledProgress(){
        int progress = this.data.get(0);
        int maxProgrss = this.data.get(1);
        int progressArrowSize = 16;

        return maxProgrss != 0 && progress != 0 ? progress * progressArrowSize / maxProgrss : 0;
    }

    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 14;  // must be the number of slots you have!
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
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, ModBlocks.ASSEMBLER.get());
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
