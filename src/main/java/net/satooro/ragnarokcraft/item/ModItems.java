package net.satooro.ragnarokcraft.item;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.satooro.ragnarokcraft.RagnarokCraftMod;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(RagnarokCraftMod.MOD_ID);

    public static final DeferredHolder<Item, Item> TESTE_ITEM = ITEMS.register("gu_aberto", () -> new Item(new Item.Properties().stacksTo(1)));

    // Raw Ingots
    public static final DeferredItem<Item> RAW_RAGNARIUM = ITEMS.register("raw_ragnarium", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final DeferredItem<Item> RAW_YMIRITA = ITEMS.register("raw_ymirita", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final DeferredItem<Item> RAW_THORNIUM = ITEMS.register("raw_thornium", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final DeferredItem<Item> RAW_MIMIR_HEART = ITEMS.register("raw_mimir_heart", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final DeferredItem<Item> RAW_MUSPELIUM = ITEMS.register("raw_muspelium", () -> new Item(new Item.Properties().stacksTo(64)));
//    public static final DeferredHolder<Item, Item> RAW_NIFLACRIL = ITEMS.register("raw_niflacril", () -> new Item(new Item.Properties().stacksTo(64)));

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


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
