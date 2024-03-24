package xyz.sakubami.infinitum.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import xyz.sakubami.infinitum.crafting.Crafting;

public class Combust implements Listener {

    @EventHandler
    public void onCombust( EntityDamageEvent e )
    {
        Location location = e.getEntity().getLocation();
        World world = location.getWorld();
        Entity entity = e.getEntity();

        if ( e.getCause().equals( EntityDamageEvent.DamageCause.LAVA ) )
        {
            if ( entity.getType().equals( EntityType.DROPPED_ITEM ) )
            {
                if ( world.getBlockAt( location ).getType().equals( Material.LAVA_CAULDRON ) )
                {
                    String name;
                    ItemStack item = ( ( Item ) e.getEntity() ).getItemStack();
                    if ( item.getItemMeta().hasLore() )
                    {
                        name = item.getItemMeta().getLore().get( item.getItemMeta().getLore().size() -1 ).replace( "§0", "") + "/" + item.getAmount();
                    } else
                    {
                        name = item.getType().name() + "/" + item.getAmount();
                    }

                    world.playSound( location, Sound.ITEM_BUCKET_FILL_LAVA, 1 , 4 );

                    Crafting.get().queryCauldronCraft( location , name );
                }
            }
        }
    }
}