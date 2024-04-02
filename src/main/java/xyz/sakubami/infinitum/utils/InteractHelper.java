package xyz.sakubami.infinitum.utils;

import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import xyz.sakubami.infinitum.builder.item.nbt.NBTUtils;

public class InteractHelper {

    private final NBTUtils nbt = new NBTUtils();

    public boolean rightClick( PlayerInteractEvent event )
    {
        if ( event.getHand() != null )
            if ( event.getHand().equals( EquipmentSlot.HAND ) )
                return event.getAction().equals( Action.RIGHT_CLICK_AIR ) || event.getAction().equals( Action.RIGHT_CLICK_BLOCK );
        return false;
    }

    public boolean rightClickWithItem( PlayerInteractEvent event, ItemStack itemStack, String itemID )
    {
        if ( event.getHand() != null )
            if ( event.getHand().equals( EquipmentSlot.HAND ) )
                if ( event.getAction().equals( Action.RIGHT_CLICK_AIR ) || event.getAction().equals( Action.RIGHT_CLICK_BLOCK ) )
                    if ( event.getItem() != null )
                        if ( nbt.hasID( itemStack ) )
                            return nbt.getID( itemStack ).equalsIgnoreCase( itemID );
        return false;
    }

    public boolean rightClickBlock( PlayerInteractEvent event, Material material )
    {
        if ( event.getHand() != null )
            if ( event.getHand().equals( EquipmentSlot.HAND ) )
                if ( event.getAction().equals( Action.RIGHT_CLICK_AIR ) || event.getAction().equals( Action.RIGHT_CLICK_BLOCK ) )
                    if ( event.getClickedBlock() != null )
                        return event.getClickedBlock().getType().equals( material );
        return false;
    }

    public boolean rightClickBlockWithItem( PlayerInteractEvent event, Material material, ItemStack itemStack, String itemID )
    {
        if ( event.getHand() != null )
            if ( event.getHand().equals( EquipmentSlot.HAND ) )
                if ( event.getAction().equals( Action.RIGHT_CLICK_AIR ) || event.getAction().equals( Action.RIGHT_CLICK_BLOCK ) )
                    if (event.getClickedBlock() != null )
                        if ( event.getClickedBlock().getType().equals( material ) )
                            if ( event.getItem() != null )
                                if ( nbt.hasID( itemStack ) )
                                    return nbt.getID( itemStack ).equalsIgnoreCase( itemID );
        return false;
    }
}
