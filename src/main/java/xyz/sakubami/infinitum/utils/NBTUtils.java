package xyz.sakubami.infinitum.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import xyz.sakubami.infinitum.world.functionality.Attribute;
import xyz.sakubami.infinitum.world.functionality.items.enchanting.CustomEnchantment;

import java.util.HashMap;
import java.util.Map;

public class NBTUtils {

    public Map<CustomEnchantment, Integer > getEnchantments( ItemStack item )
    {
        Map< CustomEnchantment, Integer > enchants = new HashMap<>();
        if ( NBT.getNBTString( item, "ENCHANTED" ) == null )
            return null;
        for ( NamespacedKey key : NBT.getNBTTags( item ).keySet() )
        {
            if ( CustomEnchantment.contains( key ) )
                return null;
            int level = NBT.getNBTInt( item, key );
            enchants.put( CustomEnchantment.getByID( key ), level );
        }
        return enchants;
    }

    public Map<CustomEnchantment, Integer > getEnchantments( ItemStack item )
    {
        Map< CustomEnchantment, Integer > enchants = new HashMap<>();
        if ( NBT.getNBTString( item, "ENCHANTED" ) == null )
            return null;
        for ( NamespacedKey key : NBT.getNBTTags( item ).keySet() )
        {
            if ( CustomEnchantment.contains( key ) )
                return null;
            int level = NBT.getNBTInt( item, key );
            enchants.put( CustomEnchantment.getByID( key ), level );
        }
        return enchants;
    }

    public Map<Attribute, Integer > getAttributes(ItemStack item )
    {
        Map< Attribute, Integer > attributes = new HashMap<>();
        for ( NamespacedKey key : NBT.getNBTTags( item ).keySet() )
        {
            if ( !NBT.getNBTString( item, key ).contains( "ATTRIBUTE") )
                return null;
            int level = NBT.getNBTInt( item, key );
            attributes.put( Attribute.valueOf( key.getKey().replace( "ATTRIBUTE_", "" ) ), level );
        }
        return attributes;
    }
}
