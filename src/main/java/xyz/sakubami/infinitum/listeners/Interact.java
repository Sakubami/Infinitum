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
import xyz.sakubami.infinitum.Infinitum;
import xyz.sakubami.infinitum.crafting.Crafting;
import xyz.sakubami.infinitum.crafting.stations.Primer;
import xyz.sakubami.infinitum.generation.CustomGeneration;
import xyz.sakubami.infinitum.player.level.PlayerConfig;
import xyz.sakubami.infinitum.player.skills.ExperienceType;
import xyz.sakubami.infinitum.player.skills.Leveling;
import xyz.sakubami.infinitum.utils.InteractHelper;
import xyz.sakubami.infinitum.utils.worldedit.InfinitumSchematic;
import xyz.sakubami.infinitum.utils.worldedit.WorldEditHelper;

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
                                WorldEditHelper.Paste( location , InfinitumSchematic.TABLE.getPath(), player ), 19 );
                    }
                }
            }

            if ( e.getClickedBlock().getType().equals( Material.DIAMOND_BLOCK ) )
            {
                if (e.getItem() != null )
                {
                    if ( e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase( "§5Fragment der Realität" ) )
                    {
                        player.sendMessage( "spawned tower ig lolz" );
                        CustomGeneration.generateRandomChunks( player, Material.GRASS_BLOCK, 0 );
                    }
                }
            }

            if ( e.getClickedBlock().getType().equals( Material.GOLD_BLOCK ) )
            {
                if (e.getItem() != null )
                {
                    if ( e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase( "§5Fragment der Realität" ) )
                    {
                        CustomGeneration.generateTowers();
                        player.sendMessage( "spawned tower AJAJJAA" );
                    }
                }
            }

            if ( e.getClickedBlock().getType().equals( Material.NETHERITE_BLOCK ) )
            {
                if (e.getItem() != null )
                {
                    if ( e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase( "§5Fragment der Realität" ) )
                    {
                        CustomGeneration.abort();
                        player.sendMessage( "ABORTED" );
                    }
                }
            }

            if ( e.getClickedBlock().getType().equals( Material.IRON_BLOCK ) )
            {
                if (e.getItem() != null )
                {
                    if ( e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase( "§5Fragment der Realität" ) )
                    {
                        Leveling leveling = new Leveling();
                        PlayerConfig config = PlayerConfig.get();
                        leveling.gainExperience( player.getUniqueId(), ExperienceType.BASE, 823434 );
                        player.sendMessage( "gave 300k xp lolz have fun hehehe " + leveling.calculateLevel( config.getPlayerExperience( player.getUniqueId() ).get( ExperienceType.BASE ) ) );
                    }
                }
            }
        }
    }
}
