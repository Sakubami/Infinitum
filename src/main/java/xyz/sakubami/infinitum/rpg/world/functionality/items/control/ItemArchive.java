package xyz.sakubami.infinitum.rpg.world.functionality.items.control;

import org.bukkit.inventory.ItemStack;
import xyz.sakubami.infinitum.Infinitum;
import xyz.sakubami.infinitum.rpg.utils.NBTUtils;

import java.util.HashMap;
import java.util.UUID;

public class ItemArchive {

    private final HashMap< UUID, ItemMask > cache;
    private final NBTUtils nbt = new NBTUtils();

    public ItemArchive()
    {
        this.cache = new HashMap<>();
    }

    public void add( ItemMask mask )
    {
        cache.put( mask.getUuid(), mask );
    }

    public void remove( ItemMask mask )
    {
        cache.remove( mask.getUuid() );
    }

    public void update( ItemMask mask )
    {
        UUID uuid = mask.getUuid();
        if ( cache.get( uuid ) != null )
            cache.replace( uuid, mask );
    }

    public boolean contains( ItemMask mask )
    {
        return ( cache.get( mask.getUuid() ) != null );
    }

    public ItemMask get( ItemStack itemStack ) { return cache.get( UUID.fromString( nbt.getItemNBTString( itemStack, "UUID" ) ) ); }

    public ItemMask get( UUID uuid )
    {
        return cache.get( uuid );
    }

    public static ItemArchive get() { return Infinitum.getInstance().getItemArchive(); }
}
