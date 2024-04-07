package xyz.sakubami.infinitum.utils.builder.mob.nbt;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import xyz.sakubami.infinitum.Infinitum;

import java.util.HashMap;

public class MobNBTApi {

    private final HashMap<NamespacedKey, String> list = new HashMap<>();

    private NamespacedKey key( String key ) { return new NamespacedKey( Infinitum.getInstance(), key ); }

    public void addAllNBTTagList( HashMap<NamespacedKey, String> list)
    {
        this.list.putAll( list );
    }

    public void addNBTTag( String key, String value )
    {
        this.list.put( key( key ), value );
    }

    public LivingEntity parseAllNBTTags( LivingEntity entity )
    {
        for ( NamespacedKey key : this.list.keySet() )
        {
            entity.getPersistentDataContainer().set( key, PersistentDataType.STRING, this.list.get( key ) );
        }
        return entity;
    }

    //

    public String getNBTValue( LivingEntity entity, String key ) {
        return entity.getPersistentDataContainer().get( key( key ), PersistentDataType.STRING );
    }

    public boolean hasNBT( LivingEntity entity ) {
        return !entity.getPersistentDataContainer().isEmpty();
    }

    public HashMap<NamespacedKey, String> getNBTTags( LivingEntity entity )
    {
        HashMap<NamespacedKey, String> list = new HashMap<>();
        for ( NamespacedKey keySet: entity.getPersistentDataContainer().getKeys() )
        {
            list.put( keySet, entity.getPersistentDataContainer().get( keySet, PersistentDataType.STRING ) );
        }
        return list;
    }
}
