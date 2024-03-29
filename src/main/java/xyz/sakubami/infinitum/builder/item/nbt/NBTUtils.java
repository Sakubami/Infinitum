package xyz.sakubami.infinitum.builder.item.nbt;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class NBTUtils {

    NBTApi NBT = new NBTApi();

    public boolean isProtected( ItemStack item )
    {
        if ( NBT.getNBTValue( item, "protected" ) != null )
            return Boolean.parseBoolean( NBT.getNBTValue( item, "protected" ) );
        return false;
    }

    public boolean hasID( ItemStack item )
    {
        return NBT.getNBTValue( item, "id" ) != null;
    }

    public String getID( ItemStack item )
    {
        return NBT.getNBTValue( item, "id" );
    }

    public String getAbility( ItemStack item )
    {
        return NBT.getNBTValue( item, "ability" );
    }

    public String getRarity( ItemStack item )
    {
        return NBT.getNBTValue( item, "rarity" );
    }
}
