package xyz.sakubami.infinitum.utils.builder.item.nbt;

import org.bukkit.inventory.ItemStack;
import xyz.sakubami.infinitum.world.functionality.items.components.ItemCategory;
import xyz.sakubami.infinitum.world.functionality.items.components.ItemType;
import xyz.sakubami.infinitum.world.functionality.items.enchanting.CustomEnchantment;

import java.util.ArrayList;

public class ItemNBTUtils {

    ItemNBTApi NBT = new ItemNBTApi();

    public boolean isProtected( ItemStack item )
    {
        if ( NBT.getNBTValue( item, "PROTECTED" ) != null )
            return Boolean.parseBoolean( NBT.getNBTValue( item, "PROTECTED" ) );
        return false;
    }

    public ArrayList<CustomEnchantment> getEnchantments( ItemStack item )
    {
        ArrayList<CustomEnchantment> list = new ArrayList<>();
        if ( NBT.getNBTValue( item, "ENCHANTED" ) != null ) {
            for ( String value : NBT.getNBTTags( item ).values() )
                list.add( CustomEnchantment.getByID( value ) );
        }
        return list;
    }

    public boolean hasID( ItemStack item ) { return NBT.getNBTValue( item, "ID") != null; }

    public String getID( ItemStack item ) { return NBT.getNBTValue( item, "ID" ); }

    public ItemCategory getCategory ( ItemStack itemStack ) { return ItemCategory.valueOf( NBT.getNBTValue( itemStack, "CATEGORY" ) ); }

    public ItemType getType (ItemStack itemStack ) { return ItemType.valueOf( NBT.getNBTValue( itemStack, "TYPE" ) ); }
}
