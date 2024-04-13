package xyz.sakubami.infinitum.rpg.utils;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

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
                        if ( nbt.checkForItemID( itemStack ) )
                            return nbt.getItemID( itemStack ).equalsIgnoreCase( itemID );
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
        if ( event.getHand() == null )
            return false;
        if ( !event.getHand().equals( EquipmentSlot.HAND ) )
            return false;
        if ( event.getAction().equals( Action.RIGHT_CLICK_AIR ) || event.getAction().equals( Action.RIGHT_CLICK_BLOCK ) )
            if (event.getClickedBlock() != null )
                if ( event.getClickedBlock().getType().equals( material ) )
                    if ( event.getItem() != null )
                        if ( nbt.checkForItemID( itemStack ) )
                            return nbt.getItemID( itemStack ).equalsIgnoreCase( itemID );
        return false;
    }

    public boolean rightClickCustomEntity( PlayerInteractEntityEvent event )
    {
        if ( event.getHand().equals( EquipmentSlot.HAND ) )
            return ( nbt.isCustomEntity( ( LivingEntity ) event.getRightClicked() ) );
        return false;
    }
}
