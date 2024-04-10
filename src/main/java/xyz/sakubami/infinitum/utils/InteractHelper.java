package xyz.sakubami.infinitum.utils;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import xyz.sakubami.infinitum.utils.builder.item.nbt.ItemNBTUtils;
import xyz.sakubami.infinitum.utils.builder.mob.nbt.MobNBT;

public class InteractHelper {

    private final ItemNBTUtils itemNBT = new ItemNBTUtils();
    private final MobNBT mobNBT = new MobNBT();

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
                        if ( itemNBT.hasID( itemStack ) )
                            return itemNBT.getID( itemStack ).equalsIgnoreCase( itemID );
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
                        if ( itemNBT.hasID( itemStack ) )
                            return itemNBT.getID( itemStack ).equalsIgnoreCase( itemID );
        return false;
    }

    public LivingEntity rightClickCustomEntity( PlayerInteractEntityEvent event )
    {
        return ( LivingEntity ) event.getRightClicked();
    }
}
