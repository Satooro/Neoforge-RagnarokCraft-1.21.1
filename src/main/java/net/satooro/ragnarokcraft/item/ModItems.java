package net.satooro.ragnarokcraft.item;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.SwordItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.satooro.ragnarokcraft.RagnarokCraftMod;
import net.satooro.ragnarokcraft.item.custom.ChiselItem;
import net.satooro.ragnarokcraft.item.custom.ComponentItem;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(RagnarokCraftMod.MOD_ID);

    public static final DeferredHolder<Item, Item> TESTE_ITEM = ITEMS.register("gu_aberto", () -> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties().durability(32)));

    // Raw Ingots
    public static final DeferredItem<Item> RAW_RAGNARIUM = ITEMS.register("raw_ragnarium", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final DeferredItem<Item> RAW_YMIRITA = ITEMS.register("raw_ymirita", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final DeferredItem<Item> RAW_THORNIUM = ITEMS.register("raw_thornium", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final DeferredItem<Item> RAW_MIMIR_HEART = ITEMS.register("raw_mimir_heart", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final DeferredItem<Item> RAW_MUSPELIUM = ITEMS.register("raw_muspelium", () -> new Item(new Item.Properties().stacksTo(64)));
//    public static final DeferredHolder<Item, Item> RAW_NIFLACRIL = ITEMS.register("raw_niflacril", () -> new Item(new Item.Properties().stacksTo(64)));

    // Upgrades
    public static final DeferredItem<Item> SPEED_UPGRADE = ITEMS.register("speed_upgrade", () -> new Item(new Item.Properties().stacksTo(64)));

    // Components
    public static final DeferredItem<Item> COMPONENT_TIER1 = ITEMS.register("component_tier1", () -> new ComponentItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> COMPONENT_TIER2 = ITEMS.register("component_tier2", () -> new ComponentItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> COMPONENT_TIER3 = ITEMS.register("component_tier3", () -> new ComponentItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> COMPONENT_TIER4 = ITEMS.register("component_tier4", () -> new ComponentItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> COMPONENT_TIER5 = ITEMS.register("component_tier5", () -> new ComponentItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> COMPONENT_TIER6 = ITEMS.register("component_tier6", () -> new ComponentItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> COMPONENT_TIER7 = ITEMS.register("component_tier7", () -> new ComponentItem(new Item.Properties().stacksTo(1)));

    // Ingots
    public static final DeferredItem<Item> RAGNARIUM_INGOT = ITEMS.register("ragnarium_ingot", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final DeferredItem<Item> YMIRITA_INGOT = ITEMS.register("ymirita_ingot", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final DeferredItem<Item> THORNIUM_INGOT = ITEMS.register("thornium_ingot", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final DeferredItem<Item> MIMIR_HEART_INGOT = ITEMS.register("mimir_heart_ingot", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final DeferredItem<Item> MUSPELIUM_INGOT = ITEMS.register("muspelium_ingot", () -> new Item(new Item.Properties().stacksTo(64)));

    public static final DeferredItem<ArmorItem> YMIRITA_HELMET = ITEMS.register("ymirita_helmet", () -> new ArmorItem(ModArmorMaterials.YMIRITA_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(19))));
    public static final DeferredItem<ArmorItem> YMIRITA_CHESTPLATE = ITEMS.register("ymirita_chestplate", () -> new ArmorItem(ModArmorMaterials.YMIRITA_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(19))));
    public static final DeferredItem<ArmorItem> YMIRITA_LEGGINGS = ITEMS.register("ymirita_leggings", () -> new ArmorItem(ModArmorMaterials.YMIRITA_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(19))));
    public static final DeferredItem<ArmorItem> YMIRITA_BOOTS = ITEMS.register("ymirita_boots", () -> new ArmorItem(ModArmorMaterials.YMIRITA_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(19))));

    public static final DeferredItem<SwordItem> RAGNARIUM_SWORD = ITEMS.register("ragnarium_sword",
            () -> new SwordItem(ModToolTiers.RAGNARIUM, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.RAGNARIUM, 4, -2.4f))));
    public static final DeferredItem<PickaxeItem> RAGNARIUM_PICKAXE = ITEMS.register("ragnarium_pickaxe",
            () -> new PickaxeItem(ModToolTiers.RAGNARIUM, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.RAGNARIUM, 1, -2.4f))));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
