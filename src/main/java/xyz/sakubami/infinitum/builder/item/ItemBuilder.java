package xyz.sakubami.infinitum.builder.item;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import xyz.sakubami.infinitum.Infinitum;
import xyz.sakubami.infinitum.builder.item.nbt.NBTApi;
import xyz.sakubami.infinitum.items.CustomItem;
import xyz.sakubami.infinitum.utils.Chat;

import java.util.*;

public class ItemBuilder {

    private ItemStack item;
    private ItemMeta meta;
    private Material material;
    private int amount = 1;
    private MaterialData data;
    private int customModelData;
    private Map<Enchantment, Integer> enchantments = new HashMap<>();
    private String displayName;
    private List<String> lore = new ArrayList<>();
    private List<ItemFlag> flags = new ArrayList<>();
    private String localizedName;
    private ItemMeta NBTMeta;
    private boolean isGlowing = false;

    private final NBTApi nbt = new NBTApi();

    private NamespacedKey key( String key ) {
        return new NamespacedKey( Infinitum.getInstance(), key );
    }

    public ItemBuilder( Material material ) {
        this.item = new ItemStack(material);
        this.meta = item.hasItemMeta() && item.getItemMeta() != null ? item.getItemMeta() : Bukkit.getItemFactory().getItemMeta( item.getType() );
        this.material = material;
    }

    public ItemBuilder(Material material, int amount) {
        this.item = new ItemStack(material);
        this.meta = item.hasItemMeta() && item.getItemMeta() != null ? item.getItemMeta() : Bukkit.getItemFactory().getItemMeta(item.getType());
        this.amount = amount;
        this.material = material;
    }

    public ItemBuilder(Material material, String displayName) {
        this.item = new ItemStack(material);
        this.meta = item.hasItemMeta() && item.getItemMeta() != null ? item.getItemMeta() : Bukkit.getItemFactory().getItemMeta(item.getType());
        this.material = material;
        this.displayName = displayName;
    }

    public ItemBuilder(Material material, int amount, String displayName) {
        this.item = new ItemStack(material, amount);
        this.meta = item.hasItemMeta() && item.getItemMeta() != null ? item.getItemMeta() : Bukkit.getItemFactory().getItemMeta(item.getType());
        this.material = material;
        this.amount = amount;
        this.displayName = displayName;
    }

    public ItemBuilder( ItemStack item ) {
        this.item = item;
        if ( item.hasItemMeta() && item.getItemMeta() != null ) {
            this.meta = item.getItemMeta();
            this.displayName = meta.getDisplayName();
            this.lore = meta.hasLore() && meta.getLore() != null ? meta.getLore() : new ArrayList<>();
            this.flags.addAll( item.getItemMeta().getItemFlags() );
            nbt.extractNBTData( meta );
        } else this.meta = Bukkit.getItemFactory().getItemMeta( item.getType() );
        this.material = item.getType();
        this.amount = item.getAmount();
        this.enchantments = item.getEnchantments();
    }

    public ItemBuilder( CustomItem item ) {
        this.item = new ItemStack( item.getMaterial() );
        this.meta = Bukkit.getItemFactory().getItemMeta( item.getMaterial() );
        this.material = item.getMaterial();
        this.displayName = item.getTier().getColor() + item.getName();

        this.lore = new LoreBuilder( item ).addDescription( item.getLore() != null ).build();

        nbt.addNBTTag( key( "id" ), item.name() );
    }

    public ItemBuilder(FileConfiguration cfg, String path) {
        this( cfg.getItemStack( path ) );
    }

    public static void toConfig(FileConfiguration cfg, String path, ItemBuilder builder) {
        cfg.set(path, builder.build());
    }

    public ItemBuilder addNBTTag(String key, String value) {
        nbt.addNBTTag(key(key), value);
        return this;
    }

    public ItemBuilder addNBTTagList(HashMap<String, String> value) {
        HashMap <NamespacedKey, String> list = new HashMap<>();
        for (String str: value.keySet()) {
            list.put(key(str), value.get(str));
        }
        nbt.addAllNBTTagList(list);
        return this;
    }

    public ItemBuilder setProtected(boolean value) {
        nbt.addNBTTag(key("protected"), String.valueOf(value));
        return this;
    }

    public ItemBuilder tier(String rarity) {
        List<String> rarities = Arrays.asList("DIVINE", "MYTHIC", "LEGENDARY", "EPIC", "RARE", "UNCOMMON", "COMMON");
        if (rarities.contains(rarity.toUpperCase())) {
            nbt.addNBTTag(key("tier"), rarity);
        } else nbt.addNBTTag(key("tier"), "COMMON");
        return this;
    }

    public ItemBuilder amount(int amount) {
        this.amount = amount;
        return this;
    }

    public ItemBuilder amount(int amount, String displayName) {
        this.amount = amount;
        return this;
    }

    public ItemBuilder data(MaterialData data) {
        this.data = data;
        return this;
    }

    public ItemBuilder id(String id) {
        this.localizedName = id;
        return this;
    }

    public ItemBuilder material(Material material) {
        this.material = material;
        return this;
    }

    public ItemBuilder customModelData(int customModelData) {
        this.customModelData = customModelData;
        return this;
    }

    public ItemBuilder meta(ItemMeta meta) {
        this.meta = meta;
        return this;
    }

    public ItemBuilder enchant(Enchantment enchant, int level) {
        enchantments.put(enchant, level);
        return this;
    }

    public ItemBuilder enchant(Map<Enchantment, Integer> enchantments) {
        this.enchantments = enchantments;
        return this;
    }

    public ItemBuilder displayname(String displayname) {
        this.displayName = Chat.translate(displayname);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        this.lore = Chat.translate(lore);
        return this;
    }

    public ItemBuilder addToLore(String lore) {
        this.lore.add(Chat.translate(lore));
        return this;
    }

    public ItemBuilder editLore(int index, String lore) {
        this.lore.set(index, Chat.translate(lore));
        return this;
    }

    public ItemBuilder setGlowing() {
        this.isGlowing = true;
        return this;
    }

    public ItemStack build()
    {
        item.setType( material );
        item.setAmount( amount );
        if ( data != null )
            item.setData( data );
        if ( !enchantments.isEmpty() )
            item.addUnsafeEnchantments( enchantments );
        if ( displayName != null )
            meta.setDisplayName( Chat.translate( displayName) );
        if ( !lore.isEmpty() )
            meta.setLore( Chat.translate( lore ) );
        if ( customModelData != 0 )
            meta.setCustomModelData( customModelData );
        if ( !flags.isEmpty() )
        {
            for (ItemFlag f : flags )
            {
                meta.addItemFlags( f );
            }
        }
        if ( isGlowing )
        {
            meta.addEnchant(Enchantment.CHANNELING, 1, true);
            meta.addItemFlags( ItemFlag.HIDE_ENCHANTS );
            meta.addItemFlags( ItemFlag.HIDE_POTION_EFFECTS );
        }
        meta.setLocalizedName( localizedName );
        meta = nbt.parseAllNBTTags( meta );
        item.setItemMeta( meta );
        return item;
    }
}