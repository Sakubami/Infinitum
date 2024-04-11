package xyz.sakubami.infinitum.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import xyz.sakubami.infinitum.Infinitum;
import xyz.sakubami.infinitum.world.functionality.Attribute;
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

    //TODO REPLACE WITH JAVA OBJECT CLASS AND CHECK FOR INSTANCES
    // !!!!!!!!!!!!!!
    // !!!!!!!!!!!!!!
    // !!!!!!!!!!!!!!// !!!!!!!!!!!!!!// !!!!!!!!!!!!!!
    // !!!!!!!!!!!!!!
    // !!!!!!!!!!!!!!// !!!!!!!!!!!!!!// !!!!!!!!!!!!!!// !!!!!!!!!!!!!!// !!!!!!!!!!!!!!// !!!!!!!!!!!!!!//

    public ItemMeta parseAllNBTTags( ItemMeta meta ) {
        for ( NamespacedKey key : this.list.keySet() ) {
            meta.getPersistentDataContainer().set( key, PersistentDataType.STRING, this.list.get(key));
        }
        return meta;
    }

    public LivingEntity parseAllNBTTags( LivingEntity entity ) {
        for ( NamespacedKey key : this.list.keySet() ) {
            entity.getPersistentDataContainer().set( key, PersistentDataType.STRING, this.list.get(key));
        }
        return entity;
    }

    public String getNBTString( ItemStack item, String key )
    {
        if ( item.hasItemMeta() )
            return item.getItemMeta().getPersistentDataContainer().get( key( key ), PersistentDataType.STRING );
        return null;
    }

    public String getNBTString( ItemStack item, NamespacedKey key )
    {
        if ( item.hasItemMeta() )
            return null;
        return item.getItemMeta().getPersistentDataContainer().get( key, PersistentDataType.STRING );
    }

    public int getNBTInt( ItemStack item, NamespacedKey key )
    {
        if ( !item.hasItemMeta() )
            return 0;
        return Integer.parseInt( item.getItemMeta().getPersistentDataContainer().get( key, PersistentDataType.STRING ) );
    }

    public String getNBTString( LivingEntity entity, String key )
    {
        if ( entity.getPersistentDataContainer().isEmpty() )
            return null;
        return entity.getPersistentDataContainer().get( key( key ), PersistentDataType.STRING );
    }

    public String getNBTString( LivingEntity entity, NamespacedKey key )
    {
        if ( entity.getPersistentDataContainer().isEmpty() )
            return null;
        return entity.getPersistentDataContainer().get( key, PersistentDataType.STRING );
    }

    public int getNBTInt( LivingEntity entity, NamespacedKey key )
    {
        if ( entity.getPersistentDataContainer().isEmpty() )
            return 0;
        return Integer.parseInt( entity.getPersistentDataContainer().get( key, PersistentDataType.STRING ) );
    }

    public Map< NamespacedKey, String > getNBTTags( ItemStack item )
    {
        Map< NamespacedKey, String > list = new HashMap<>();
        for ( NamespacedKey keySet: item.getItemMeta().getPersistentDataContainer().getKeys() )
        {
            list.put( keySet, item.getItemMeta().getPersistentDataContainer().get( keySet, PersistentDataType.STRING ) );
        }
        return list;
    }

    public Map< NamespacedKey, String > getNBTTags( LivingEntity entity )
    {
        Map< NamespacedKey, String > list = new HashMap<>();
        for ( NamespacedKey keySet: entity.getPersistentDataContainer().getKeys() )
        {
            list.put( keySet, entity.getPersistentDataContainer().get( keySet, PersistentDataType.STRING ) );
        }
        return list;
    }

    public Map<CustomEnchantment, Integer > getEnchantments( ItemStack item )
    {
        Map< CustomEnchantment, Integer > enchants = new HashMap<>();
        if ( getNBTString( item, "ENCHANTED" ) == null )
            return null;
        for ( NamespacedKey key : getNBTTags( item ).keySet() )
        {
            if ( CustomEnchantment.contains( key ) )
                return null;
            int level = getNBTInt( item, key );
            enchants.put( CustomEnchantment.getByID( key ), level );
        }
        return enchants;
    }

    public Map<Attribute, Integer > getAttributes( ItemStack item )
    {
        Map< Attribute, Integer > attributes = new HashMap<>();
        for ( NamespacedKey key : getNBTTags( item ).keySet() )
        {
            if ( !getNBTString( item, key ).contains( "ATTRIBUTE") )
                return null;
            int level = getNBTInt( item, key );
            attributes.put( Attribute.valueOf( key.getKey().replace( "ATTRIBUTE_", "" ) ), level );
        }
        return attributes;
    }

    public Map<Attribute, Integer > getAttributes( Object entity )
    {
        Map< Attribute, Integer > attributes = new HashMap<>();
        for ( NamespacedKey key : getNBTTags( entity ).keySet() )
        {
            if ( !getNBTString( entity, key ).contains( "ATTRIBUTE") )
                return null;
            int level = getNBTInt( entity, key );
            attributes.put( Attribute.valueOf( key.getKey().replace( "ATTRIBUTE_", "" ) ), level );
        }
        return attributes;
    }
}
