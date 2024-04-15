package xyz.sakubami.infinitum.rpg.utils.builder.item;

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
import xyz.sakubami.infinitum.rpg.utils.Chat;
import xyz.sakubami.infinitum.rpg.utils.NBTUtils;
import xyz.sakubami.infinitum.rpg.world.functionality.Attribute;
import xyz.sakubami.infinitum.rpg.world.functionality.items.components.ItemTier;
import xyz.sakubami.infinitum.rpg.world.functionality.items.enchanting.CustomEnchantment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemBuilder {

    private ItemStack item;
    private ItemMeta meta;
    private Material material;
    private int amount = 1;
    private MaterialData data;
    private int customModelData;
    private Map< CustomEnchantment, Integer > enchantments = new HashMap<>();
    private String displayName;
    private List<String> lore = new ArrayList<>();
    private List<ItemFlag> flags = new ArrayList<>();
    private String localizedName;
    private ItemMeta NBTMeta;
    private boolean isGlowing = false;

    private final NBTUtils nbt = new NBTUtils();

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
            nbt.extractItemNBTData( meta );
        } else this.meta = Bukkit.getItemFactory().getItemMeta( item.getType() );
        this.material = item.getType();
        this.amount = item.getAmount();
        this.enchantments = nbt.getItemEnchantments( item );
    }

    public ItemBuilder( ItemTemplates item ) {
        this.item = new ItemStack( item.getMaterial() );
        this.meta = Bukkit.getItemFactory().getItemMeta( item.getMaterial() );
        this.material = item.getMaterial();
        this.displayName = item.getTier().getColor() + item.getName();

        this.lore = new LoreBuilder( item ).addDescription( !item.getLore().equalsIgnoreCase( "null" ) ).build();

        for ( Attribute attribute : item.getAttributes().keySet() ) {
            nbt.addNBTTag( key( "ATTRIBUTE_" + attribute.name() ), item.getAttributes().get( attribute ) );
        }

        Infinitum.getInstance().getServer().broadcastMessage( item.toString() );
        Infinitum.getInstance().getServer().broadcastMessage( item.name() );

        nbt.addNBTTag( key( "ID" ), item.name() );

        /*
        for ( Attribute attribute : item.get) {

        }
         */
    }

    public ItemBuilder( FileConfiguration cfg, String path) {
        this( cfg.getItemStack( path ) );
    }

    public static void toConfig(FileConfiguration cfg, String path, ItemBuilder builder) {
        cfg.set(path, builder.build());
    }

    public ItemBuilder addNBTTag(String key, String value) {
        nbt.addNBTTag(key(key), value);
        return this;
    }

    public ItemBuilder addNBTTagList( HashMap< String, String > value ) {
        HashMap < NamespacedKey, String > list = new HashMap<>();
        for ( String str: value.keySet() ) {
            list.put( key( str ), value.get( str ) );
        }
        nbt.addAllNBTTagList(list);
        return this;
    }

    public ItemBuilder setProtected( boolean value )
    {
        nbt.addNBTTag( key( "PROTECTED" ), String.valueOf( value ) );
        return this;
    }

    public ItemBuilder tier( ItemTier tier )
    {
        nbt.addNBTTag( key( "TIER" ), tier.name() );
        return this;
    }

    public ItemBuilder attribute( Attribute attribute, int value )
    {
        nbt.addNBTTag( key( "ATTRIBUTE_" + attribute.name() ), value );
        return this;
    }

    public ItemBuilder amount( int amount ) {
        this.amount = amount;
        return this;
    }

    public ItemBuilder amount( int amount, String displayName ) {
        this.amount = amount;
        return this;
    }

    public ItemBuilder data( MaterialData data ) {
        this.data = data;
        return this;
    }

    public ItemBuilder id( String id ) {
        this.localizedName = id;
        return this;
    }

    public ItemBuilder material( Material material ) {
        this.material = material;
        return this;
    }

    public ItemBuilder customModelData( int customModelData ) {
        this.customModelData = customModelData;
        return this;
    }

    public ItemBuilder meta( ItemMeta meta ) {
        this.meta = meta;
        return this;
    }

    public ItemBuilder enchant( CustomEnchantment enchantment, int level )
    {
        this.enchantments.put( enchantment, level );
        return this;
    }

    public ItemBuilder enchant( Map<CustomEnchantment, Integer> enchantments ) {
        this.enchantments = enchantments;
        return this;
    }

    public ItemBuilder displayname( String displayname ) {
        this.displayName = Chat.translate( displayname );
        return this;
    }

    public ItemBuilder setLore( List<String> lore ) {
        this.lore = Chat.translate( lore );
        return this;
    }

    public ItemBuilder addToLore( String lore ) {
        this.lore.add( Chat.translate( lore ) );
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
        {
            nbt.addNBTTag( key( "ENCHANTED" ), "true" );
            for ( CustomEnchantment enchantment : enchantments.keySet() )
                nbt.addNBTTag( CustomEnchantment.getID( enchantment ), enchantments.get( enchantment ) );
        }

        if ( displayName != null )
            meta.setDisplayName( Chat.translate( displayName) );
        if ( !lore.isEmpty() )
            meta.setLore( Chat.translate( lore ) );
        if ( customModelData != 0 )
            meta.setCustomModelData( customModelData );
        if ( !flags.isEmpty() )
        {
            for ( ItemFlag f : flags )
            {
                meta.addItemFlags( f );
            }
        }
        if ( isGlowing )
        {
            meta.addEnchant( Enchantment.CHANNELING, 1, true );
            meta.addItemFlags( ItemFlag.HIDE_ENCHANTS );
            meta.addItemFlags( ItemFlag.HIDE_POTION_EFFECTS );
        }
        meta.setLocalizedName( localizedName );
        meta = nbt.parseAllItemNBTTags( meta );
        item.setItemMeta( meta );
        return item;
    }
}