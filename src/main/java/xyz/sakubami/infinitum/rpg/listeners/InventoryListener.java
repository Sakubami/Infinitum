package xyz.sakubami.infinitum.rpg.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import xyz.sakubami.infinitum.rpg.utils.NBTUtils;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryInteract( InventoryClickEvent e )
    {
        NBTUtils nbt = new NBTUtils();

        Player p = ( Player ) e.getWhoClicked();
        ItemStack item = e.getCursor();

        if ( nbt.isItemProtected( item ) )
            e.setCancelled( true );
        // implement GUI working with custom NBT tags out of
    }
}