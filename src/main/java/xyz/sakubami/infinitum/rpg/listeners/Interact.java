package xyz.sakubami.infinitum.rpg.listeners;

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
import xyz.sakubami.infinitum.rpg.utils.InteractHelper;
import xyz.sakubami.infinitum.rpg.utils.builder.item.ItemBuilder;
import xyz.sakubami.infinitum.rpg.utils.builder.mob.CustomEntityBuilder;
import xyz.sakubami.infinitum.rpg.utils.worldedit.InfinitumSchematic;
import xyz.sakubami.infinitum.rpg.utils.worldedit.WorldEditHelper;
import xyz.sakubami.infinitum.rpg.world.entities.control.EntityManipulator;
import xyz.sakubami.infinitum.rpg.world.entities.loot.CustomLootTable;
import xyz.sakubami.infinitum.rpg.world.functionality.crafting.Crafting;
import xyz.sakubami.infinitum.rpg.world.functionality.crafting.stations.Primer;
import xyz.sakubami.infinitum.rpg.world.functionality.items.components.CustomItemTemplate;
import xyz.sakubami.infinitum.rpg.world.functionality.items.control.ItemManipulator;
import xyz.sakubami.infinitum.rpg.world.functionality.items.enchanting.EnchantmentLibrary;

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
            for ( int i = 0; i < 10; i++ )
                new EntityManipulator( new CustomEntityBuilder( EntityType.ZOMBIE, player.getWorld(), player.getLocation() )
                            .health( 250000 )
                            .maxHealth( 250000 )
                            .name( "§6Golden Ghoul")
                            .assignLootTable( CustomLootTable.ENDER_DRAGON )
                            .build() )
                        .equip( new ItemStack( Material.GOLDEN_CHESTPLATE ), EquipmentSlot.CHEST )
                        .equip( new ItemStack( Material.GOLDEN_LEGGINGS ), EquipmentSlot.LEGS )
                        .equip( new ItemStack( Material.GOLDEN_BOOTS ), EquipmentSlot.FEET )
                        .equip( new ItemStack( Material.GOLDEN_SWORD ), EquipmentSlot.HAND )
                        .queue();
            // Infinitum.getInstance().getVanillaCoolDowns().addCooldown( Item.byId( 0 ), 60 );


        }

        if ( helper.rightClickBlock( e, Material.GOLD_BLOCK ) )
        {
            player.getInventory().setItemInMainHand( new ItemBuilder( CustomItemTemplate.HYPERION )
                    .build()
            );
        }

        if ( helper.rightClickBlock( e, Material.NETHERITE_BLOCK ) )
        {
            new ItemManipulator( e.getItem(), e.getPlayer() )
                    .addEnchant( EnchantmentLibrary.ATTRIBUTE_STRENGTH, 10 )
                    .addEnchant( EnchantmentLibrary.DAMAGE_ALL, 10 )
                    .addEnchant( EnchantmentLibrary.ATTRIBUTE_CRITICAL_DAMAGE, 10 )
                    .addEnchant( EnchantmentLibrary.DAMAGE_UNDEAD, 10 )
                    .addEnchant( EnchantmentLibrary.DAMAGE_ARACHNID, 10 )
                    .queue();
            Infinitum.getInstance().getServer().broadcastMessage( " clicked lol " );
        }

        if ( helper.rightClickWithItem( e, e.getItem(), "INFINITA_SCIENTIA" ) )
        {
            IBook iBook = InteractiveBooks.getBook( "start" );
            iBook.open( player );
        }
    }
}
