package net.satooro.ragnarokcraft.slots;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.satooro.ragnarokcraft.util.ModUtils;

public class UpgradeSlot extends SlotItemHandler {
    public UpgradeSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public int getMaxStackSize() {
        return 4;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return ModUtils.isItemAnUpgrade(stack);
    }
}
