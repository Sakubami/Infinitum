package xyz.sakubami.infinitum.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import xyz.sakubami.infinitum.Infinitum;
import xyz.sakubami.infinitum.world.functionality.Attribute;
import xyz.sakubami.infinitum.world.functionality.items.components.ItemCategory;
import xyz.sakubami.infinitum.world.functionality.items.components.ItemType;
import xyz.sakubami.infinitum.world.functionality.items.enchanting.CustomEnchantment;

import java.util.HashMap;
import java.util.Map;

public class NBTUtils {

    private final HashMap<NamespacedKey, String> list = new HashMap<>();

    private NamespacedKey key( String key ) { return new NamespacedKey( Infinitum.getInstance(), key ); }

    public void extractItemNBTData( ItemMeta meta )
    {
        for ( NamespacedKey key: meta.getPersistentDataContainer().getKeys() )
        {
            list.put( key, meta.getPersistentDataContainer().get( key, PersistentDataType.STRING ) );
        }
    }

    public void extractMobNBTData( LivingEntity entity )
    {
        for ( NamespacedKey key: entity.getPersistentDataContainer().getKeys() )
        {
            list.put( key, entity.getPersistentDataContainer().get( key, PersistentDataType.STRING ) );
        }
    }

    public void addAllNBTTagList(HashMap<NamespacedKey, String> list) {
        this.list.putAll(list);
    }

    public void addNBTTag( String key, String value ) {
        this.list.put( key( key ), value);
    }

    public void addNBTTag( String key, int value ) {
        this.list.put( key( key ), String.valueOf( value ) );
    }

    public void addNBTTag( NamespacedKey key, String value ) { this.list.put( key, value); }

    public void addNBTTag( NamespacedKey key, int value ) { this.list.put( key, String.valueOf( value ) ); }

    public ItemMeta parseAllItemNBTTags( ItemMeta meta ) {
        for ( NamespacedKey key : this.list.keySet() ) {
            meta.getPersistentDataContainer().set( key, PersistentDataType.STRING, this.list.get(key));
        }
        return meta;
    }

    public LivingEntity parseAllEntityNBTTags( LivingEntity entity ) {
        for ( NamespacedKey key : this.list.keySet() ) {
            entity.getPersistentDataContainer().set( key, PersistentDataType.STRING, this.list.get(key));
        }
        return entity;
    }

    public String getItemNBTString( ItemStack item, String key )
    {
        if ( item.hasItemMeta() )
            return item.getItemMeta().getPersistentDataContainer().get( key( key ), PersistentDataType.STRING );
        return null;
    }

    public String getItemNBTString( ItemStack item, NamespacedKey key )
    {
        if ( item.hasItemMeta() )
            return null;
        return item.getItemMeta().getPersistentDataContainer().get( key, PersistentDataType.STRING );
    }

    public int getItemNBTInt( ItemStack item, NamespacedKey key )
    {
        if ( !item.hasItemMeta() )
            return 0;
        return Integer.parseInt( item.getItemMeta().getPersistentDataContainer().get( key, PersistentDataType.STRING ) );
    }

    public int getItemNBTInt( ItemStack item, String key )
    {
        if ( !item.hasItemMeta() )
            return 0;
        return Integer.parseInt( item.getItemMeta().getPersistentDataContainer().get( key( key ), PersistentDataType.STRING ) );
    }

    public String getEntityNBTString( LivingEntity entity, String key )
    {
        if ( entity.getPersistentDataContainer().isEmpty() )
            return null;
        return entity.getPersistentDataContainer().get( key( key ), PersistentDataType.STRING );
    }

    public String getEntityNBTString( LivingEntity entity, NamespacedKey key )
    {
        if ( entity.getPersistentDataContainer().isEmpty() )
            return null;
        return entity.getPersistentDataContainer().get( key, PersistentDataType.STRING );
    }

    public int getEntityNBTInt( LivingEntity entity, NamespacedKey key )
    {
        if ( entity.getPersistentDataContainer().isEmpty() )
            return 0;
        return Integer.parseInt( entity.getPersistentDataContainer().get( key, PersistentDataType.STRING ) );
    }

    public int getEntityNBTInt( LivingEntity entity, String key )
    {
        if ( entity.getPersistentDataContainer().isEmpty() )
            return 0;
        return Integer.parseInt( entity.getPersistentDataContainer().get( key( key ), PersistentDataType.STRING ) );
    }

    public Map< NamespacedKey, String > getItemNBTTags( ItemStack item )
    {
        Map< NamespacedKey, String > list = new HashMap<>();
        for ( NamespacedKey keySet: item.getItemMeta().getPersistentDataContainer().getKeys() )
        {
            list.put( keySet, item.getItemMeta().getPersistentDataContainer().get( keySet, PersistentDataType.STRING ) );
        }
        return list;
    }

    public Map< NamespacedKey, String > getEntityNBTTags( LivingEntity entity )
    {
        Map< NamespacedKey, String > list = new HashMap<>();
        for ( NamespacedKey keySet: entity.getPersistentDataContainer().getKeys() )
        {
            list.put( keySet, entity.getPersistentDataContainer().get( keySet, PersistentDataType.STRING ) );
        }
        return list;
    }

    public Map<CustomEnchantment, Integer > getItemEnchantments( ItemStack item )
    {
        Map< CustomEnchantment, Integer > enchants = new HashMap<>();
        if ( getItemNBTString( item, "ENCHANTED" ) == null )
            return null;
        for ( NamespacedKey key : getItemNBTTags( item ).keySet() )
        {
            if ( CustomEnchantment.contains( key ) )
                return null;
            int level = getItemNBTInt( item, key );
            enchants.put( CustomEnchantment.getByID( key ), level );
        }
        return enchants;
    }

    public Map< Attribute, Integer > getItemAttributes( ItemStack item )
    {
        Map< Attribute, Integer > attributes = new HashMap<>();
        for ( NamespacedKey key : getItemNBTTags( item ).keySet() )
        {
            if ( !getItemNBTString( item, key ).contains( "ATTRIBUTE") )
                return null;
            int level = getItemNBTInt( item, key );
            attributes.put( Attribute.valueOf( key.getKey().replace( "ATTRIBUTE_", "" ) ), level );
        }
        return attributes;
    }

    public Map< Attribute, Integer > getEntityAttributes( LivingEntity entity )
    {
        Map< Attribute, Integer > attributes = new HashMap<>();
        for ( NamespacedKey key : getEntityNBTTags( entity ).keySet() )
        {
            if ( !getEntityNBTString( entity, key ).contains( "ATTRIBUTE") )
                return null;
            int level = getEntityNBTInt( entity, key );
            attributes.put( Attribute.valueOf( key.getKey().replace( "ATTRIBUTE_", "" ) ), level );
        }
        return attributes;
    }

    public boolean checkForEntityNBTData( LivingEntity entity ) { return !entity.getPersistentDataContainer().isEmpty(); }

    public boolean checkForItemID( ItemStack item ) { return getItemNBTString( item, "ID") != null; }

    public boolean isCustomEntity( LivingEntity entity ) { return getEntityNBTString( entity, "CUSTOM") != null; }

    public String getItemID( ItemStack item ) { return getItemNBTString( item, "ID" ); }

    public ItemCategory getItemCategory ( ItemStack itemStack ) { return ItemCategory.valueOf( getItemNBTString( itemStack, "CATEGORY" ) ); }

    public ItemType getItemType ( ItemStack itemStack ) { return ItemType.valueOf( getItemNBTString( itemStack, "TYPE" ) ); }

    public int getItemEnchantmentValue ( ItemStack itemStack, CustomEnchantment enchantment ) { return getItemNBTInt( itemStack, CustomEnchantment.getID( enchantment ) ); }

    public boolean isItemProtected( ItemStack item ) { return Boolean.parseBoolean( getItemNBTString( item, "PROTECTED" ) ); }
}
