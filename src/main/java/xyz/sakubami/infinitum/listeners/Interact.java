package xyz.sakubami.infinitum.listeners;

import net.leonardo_dgs.interactivebooks.IBook;
import net.leonardo_dgs.interactivebooks.InteractiveBooks;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import xyz.sakubami.infinitum.Infinitum;
import xyz.sakubami.infinitum.world.functionality.Attribute;
import xyz.sakubami.infinitum.world.functionality.crafting.Crafting;
import xyz.sakubami.infinitum.world.functionality.crafting.stations.Primer;
import xyz.sakubami.infinitum.utils.InteractHelper;
import xyz.sakubami.infinitum.utils.builder.mob.CustomEntityBuilder;
import xyz.sakubami.infinitum.utils.worldedit.InfinitumSchematic;
import xyz.sakubami.infinitum.utils.worldedit.WorldEditHelper;
import xyz.sakubami.infinitum.world.entities.control.EntityControl;

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

        if ( helper.rightClickBlock( e, Material.DIAMOND_BLOCK ) )
        {
            player.sendMessage( "testing" );
            new EntityControl( new CustomEntityBuilder( EntityType.ZOMBIE, player.getWorld() ).build() )
                    .teleport( player.getLocation() )
                    .attribute( Attribute.HEALTH, 500 )
                    .attribute( Attribute.MAX_HEALTH, 500 )
                    .equip( new ItemStack( Material.DIAMOND_HELMET ), EquipmentSlot.HEAD )
                    .queue();
        }

        if ( helper.rightClickWithItem( e, e.getItem(), "INFINITA_SCIENTIA" ) )
        {
            IBook iBook = InteractiveBooks.getBook( "start" );
            iBook.open( player );
        }
    }
}
