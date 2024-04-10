package xyz.sakubami.infinitum.utils.builder.item.nbt;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import xyz.sakubami.infinitum.world.functionality.Attribute;
import xyz.sakubami.infinitum.world.functionality.items.components.ItemCategory;
import xyz.sakubami.infinitum.world.functionality.items.components.ItemType;
import xyz.sakubami.infinitum.world.functionality.items.enchanting.CustomEnchantment;

import java.util.HashMap;
import java.util.Map;

public class ItemNBTUtils {

    ItemNBT NBT = new ItemNBT();

    public boolean isProtected( ItemStack item )
    {
        if ( NBT.getNBTString( item, "PROTECTED" ) != null )
            return Boolean.parseBoolean( NBT.getNBTString( item, "PROTECTED" ) );
        return false;
    }

    public Map< CustomEnchantment, Integer > getEnchantments( ItemStack item )
    {
        Map< CustomEnchantment, Integer > enchants = new HashMap<>();
        if ( NBT.getNBTString( item, "ENCHANTED" ) == null )
            return null;
        for ( NamespacedKey key : NBT.getNBTTags( item ).keySet() )
        {
            if ( !CustomEnchantment.contains( key ) )
                continue;
            enchants.put( CustomEnchantment.getByID( key ), NBT.getNBTInt( item, key ) );
        }
        return enchants;
    }

    public Map< Attribute, Integer > getAttributes( ItemStack item )
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

    public boolean hasID( ItemStack item ) { return NBT.getNBTString( item, "ID") != null; }

    public String getID( ItemStack item ) { return NBT.getNBTString( item, "ID" ); }

    public ItemCategory getCategory ( ItemStack itemStack ) { return ItemCategory.valueOf( NBT.getNBTString( itemStack, "CATEGORY" ) ); }

    public ItemType getType ( ItemStack itemStack ) { return ItemType.valueOf( NBT.getNBTString( itemStack, "TYPE" ) ); }

    public int getEnchantmentValue ( ItemStack itemStack, CustomEnchantment enchantment ) { return NBT.getNBTInt( itemStack, CustomEnchantment.getID( enchantment ) ); }
}
