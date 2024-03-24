package xyz.sakubami.infinitum.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import xyz.sakubami.infinitum.Infinitum;
import xyz.sakubami.infinitum.crafting.Crafting;
import xyz.sakubami.infinitum.crafting.stations.Primer;
import xyz.sakubami.infinitum.items.CustomItem;
import xyz.sakubami.infinitum.utils.InteractHelper;
import xyz.sakubami.infinitum.utils.worldedit.SchematicType;
import xyz.sakubami.infinitum.utils.worldedit.WorldEditHelper;

import javax.xml.validation.Schema;

public class Interact implements Listener {

    Crafting crafting = Crafting.get();

    @EventHandler
    public void onInteract( PlayerInteractEvent e )
    {
        Player player = e.getPlayer();
        if ( e.getAction().equals( Action.RIGHT_CLICK_BLOCK ) )
        {
            if ( InteractHelper.rightClick( e ) )
            {
                if ( e.getClickedBlock().getType().equals( Material.LAVA_CAULDRON ) )
                {
                    if (e.getItem() != null )
                    {
                        if ( e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase( "§6Rune der Macht" ) )
                        {
                            Location loc = e.getClickedBlock().getLocation();
                            Crafting.get().initiateCauldronCrafting( loc, Primer.RUNE, player, true );
                        }

                        if ( e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase( "§bInfinita Scientia" ) )
                        {
                            Location loc = e.getClickedBlock().getLocation();
                            Crafting.get().initiateCauldronCrafting( loc, Primer.BOOK, player, false );
                        }
                    }
                }
            }

            if ( e.getClickedBlock().getType().equals( Material.CARTOGRAPHY_TABLE ) )
            {
                if (e.getItem() != null )
                {
                    if ( e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase( "§5Fragment der Realität" ) )
                    {

                        e.setCancelled( true );

                        player.playSound( player.getLocation(), Sound.BLOCK_BELL_RESONATE, 1000, 3);
                        player.playSound( player.getLocation(), Sound.BLOCK_BELL_RESONATE, 1000, 2);

                        final Location location = WorldEditHelper.fixTableLocation( e.getClickedBlock().getLocation(), player.getFacing() );

                        Bukkit.getScheduler().scheduleSyncDelayedTask( Infinitum.getInstance(), () ->
                                WorldEditHelper.Paste( location , SchematicType.TABLE.getPath(), player ), 19 );
                    }
                }
            }
        }
    }
}
