package xyz.sakubami.infinitum.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import xyz.sakubami.infinitum.utils.builder.item.nbt.ItemNBTUtils;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryInteract( InventoryClickEvent e )
    {
        ItemNBTUtils NBT = new ItemNBTUtils();

        Player p = ( Player ) e.getWhoClicked();
        ItemStack item = e.getCursor();


        if ( NBT.isProtected( item ) )
            e.setCancelled( true );
        // implement GUI working with custom NBT tags out of
    }
}
