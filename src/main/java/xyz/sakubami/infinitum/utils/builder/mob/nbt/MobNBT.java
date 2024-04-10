package xyz.sakubami.infinitum.utils.builder.mob.nbt;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataType;
import xyz.sakubami.infinitum.Infinitum;
import xyz.sakubami.infinitum.world.functionality.Attribute;

import java.util.HashMap;
import java.util.Map;

public class MobNBT {

    private final HashMap<NamespacedKey, String> list = new HashMap<>();

    private NamespacedKey key( String key ) { return new NamespacedKey( Infinitum.getInstance(), key ); }

    public void addAllNBTTagList( HashMap<NamespacedKey, String> list)
    {
        this.list.putAll( list );
    }

    public void addNBTTag( String key, String value ) {
        this.list.put( key( key ), value);
    }

    public void addNBTTag( String key, int value ) {
        this.list.put( key( key ), String.valueOf( value ) );
    }

    public void addNBTTag( NamespacedKey key, String value ) { this.list.put( key, value); }

    public void addNBTTag( NamespacedKey key, int value ) { this.list.put( key, String.valueOf( value ) ); }

    public LivingEntity parseAllNBTTags( LivingEntity entity ) {
        for ( NamespacedKey key : this.list.keySet() ) {
            entity.getPersistentDataContainer().set(key, PersistentDataType.STRING, this.list.get(key));
        }
        return entity;
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

    public Map< Attribute, Integer > getAttributes( LivingEntity entity )
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

    public boolean hasNBT( LivingEntity entity ) {
        return !entity.getPersistentDataContainer().isEmpty();
    }

    public Map< NamespacedKey, String > getNBTTags( LivingEntity entity )
    {
        Map< NamespacedKey, String> list = new HashMap<>();
        for ( NamespacedKey keySet: entity.getPersistentDataContainer().getKeys() )
        {
            list.put( keySet, entity.getPersistentDataContainer().get( keySet, PersistentDataType.STRING ) );
        }
        return list;
    }
}
