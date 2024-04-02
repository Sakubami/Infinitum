package xyz.sakubami.infinitum.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import xyz.sakubami.infinitum.Infinitum;
import xyz.sakubami.infinitum.crafting.Crafting;
import xyz.sakubami.infinitum.crafting.stations.Primer;
import xyz.sakubami.infinitum.utils.InteractHelper;
import xyz.sakubami.infinitum.utils.worldedit.InfinitumSchematic;
import xyz.sakubami.infinitum.utils.worldedit.WorldEditHelper;

public class Interact implements Listener {

    Crafting crafting = Crafting.get();

    @EventHandler
    public void onInteract( PlayerInteractEvent e )
    {
        InteractHelper helper = new InteractHelper();
        Player player = e.getPlayer();

        if ( helper.rightClickBlockWithItem( e, Material.LAVA_CAULDRON, e.getItem(), "RUNE_OF_POWER" ) )
        {
            Location loc = e.getClickedBlock().getLocation();
            Crafting.get().initiateCauldronCrafting( loc, Primer.RUNE, player, true );
        }

        if ( helper.rightClickBlockWithItem( e, Material.LAVA_CAULDRON, e.getItem(), "INFINITA_SCIENTIA" ) )
        {
            Location loc = e.getClickedBlock().getLocation();
            Crafting.get().initiateCauldronCrafting( loc, Primer.BOOK, player, false );
        }

        if ( helper.rightClickBlockWithItem( e, Material.CARTOGRAPHY_TABLE, e.getItem(), "FRAGMENT_OF_REALITY" ) )
        {
            e.setCancelled( true );

            player.playSound( player.getLocation(), Sound.BLOCK_BELL_RESONATE, 1000, 3);
            player.playSound( player.getLocation(), Sound.BLOCK_BELL_RESONATE, 1000, 2);

            final Location location = WorldEditHelper.fixTableLocation( e.getClickedBlock().getLocation(), player.getFacing() );

            Bukkit.getScheduler().scheduleSyncDelayedTask( Infinitum.getInstance(), () ->
                    WorldEditHelper.Paste( location , InfinitumSchematic.TABLE.getPath(), player ), 19 );
        }

        if ( helper.rightClickBlockWithItem( e, Material.DIAMOND_BLOCK, e.getItem(), "FRAGMENT_OF_REALITY" ) )
        {
            player.sendMessage( "testing" );
        }
    }
}
