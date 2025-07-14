package net.satooro.ragnarokcraft.slots;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.satooro.ragnarokcraft.util.ModUtils;

public class ComponentItem extends SlotItemHandler {
    public ComponentItem(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return ModUtils.isItemAnComponent(stack);
    }
}
