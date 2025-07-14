package net.satooro.ragnarokcraft.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.satooro.ragnarokcraft.item.ModItems;
import net.satooro.ragnarokcraft.item.custom.ComponentItem;

public class ModUtils {

    public static boolean isItemAnUpgrade(ItemStack item){
        return item.getItem() == ModItems.SPEED_UPGRADE.get();
    }
    public static boolean isItemAnComponent(ItemStack item){
        return item.getItem() instanceof ComponentItem;
    }
    public static void useComponent(ItemStack component, Level level, BlockPos blockPos){
        if(component.getItem() == ModItems.COMPONENT_TIER7.get()){

        } else if(component.getDamageValue() >= component.getMaxDamage() && component.getItem() != ModItems.COMPONENT_TIER6.get()){
            if(!component.getComponents().toString().contains("eternal")){
                component.shrink(1);
            }
        } else {
            component.setDamageValue(component.getDamageValue() + 1);
        }
    }

    public static int getComponentTier(ItemStack item){
        if(item.getItem() == ModItems.COMPONENT_TIER1.get()) {
            return 1;
        }
        if(item.getItem() == ModItems.COMPONENT_TIER2.get()) {
            return 2;
        }
        if(item.getItem() == ModItems.COMPONENT_TIER3.get()) {
            return 3;
        }
        if(item.getItem() == ModItems.COMPONENT_TIER4.get()) {
            return 4;
        }
        if(item.getItem() == ModItems.COMPONENT_TIER5.get()) {
            return 5;
        }
        if(item.getItem() == ModItems.COMPONENT_TIER6.get()) {
            return 6;
        }
        if(item.getItem() == ModItems.COMPONENT_TIER7.get()) {
            return 7;
        }
        return 0;
    }
}
