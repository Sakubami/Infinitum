package xyz.sakubami.infinitum.utils;

import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class InteractHelper {

    public static boolean rightClick(PlayerInteractEvent event )
    {
        if ( event.getHand() != null )
        {
            if ( event.getHand().equals( EquipmentSlot.HAND ) )
            {
                return event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK);

            }
        }
        return false;
    }
}
