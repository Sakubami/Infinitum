package xyz.sakubami.infinitum.functionality.crafting;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import xyz.sakubami.infinitum.Infinitum;
import xyz.sakubami.infinitum.functionality.crafting.stations.Primer;
import xyz.sakubami.infinitum.functionality.items.ItemTemplates;
import xyz.sakubami.infinitum.utils.builder.item.ItemBuilder;
import xyz.sakubami.infinitum.utils.Loc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Crafting {

    private static class CraftingQueue {

        private final Primer primer;
        private final Location location;
        private List<String> items;

        public CraftingQueue( Location location, Primer primer, String...items ) {
            this.primer = primer;
            this.location = location;
            this.items = new ArrayList<>( Arrays.asList( items ) );
        }
        public Location getLocation() { return location; }
        public Primer getPrimer() { return primer; }
        public List<String> getItems() { return items; }

        public void addItem( String item ) { this.items.add( item ); }
        public void setItems( List<String> items ) { this.items = items; }
    }

    private final ArrayList<CraftingQueue> queue;

    public Crafting()
    {
        queue = new ArrayList<>();
    }

    public void queryCauldronCraft( Location location, String item )
    {
        if ( queue.stream().anyMatch( queued -> queued.getLocation().equals( Loc.normalize( location ) ) ) )
        {
            CraftingQueue queuedItems = queue.stream().filter( queued -> queued.location.equals( Loc.normalize( location ) ) ).findFirst().get();
            if ( queuedItems.getItems().size() >= 3 )
            {
                World world = location.getWorld();
                world.spawnEntity( location, EntityType.LIGHTNING );
                world.playSound( location, Sound.ENTITY_PARROT_IMITATE_WITHER, 20 , 1 );
                world.playSound( location, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 20 , 1 );
                world.getBlockAt( location ).setType( Material.CAULDRON );

                queue.removeIf( queued -> queued.getLocation().equals( Loc.normalize( location ) ) );
                world.playSound( location, Sound.BLOCK_BELL_RESONATE, 20, 1);
                world.playSound( location, Sound.BLOCK_BELL_RESONATE, 20, 1);
                world.playSound( location, Sound.BLOCK_BELL_RESONATE, 20, 1);

                queuedItems.addItem( item );

                ItemTemplates itemTemplates = RecipeConfig.get().craftRecipe( queuedItems.items, queuedItems.primer );

                Infinitum.getInstance().getServer().broadcastMessage( "a: " + itemTemplates);

                Bukkit.getScheduler().scheduleSyncDelayedTask( Infinitum.getInstance(), () ->
                        spawnCraftingResult( itemTemplates != null ? itemTemplates : ItemTemplates.NULL, location ), 41 );
                }

            queuedItems.addItem( item );
        }
    }

    public void initiateCauldronCrafting( Location location, Primer primer, Player player, boolean consume )
    {
        if ( queue.stream().noneMatch( queued -> queued.location.equals( location ) ) )
        {
            queue.add( new CraftingQueue( location , primer ) );

            player.playSound( player.getLocation(), Sound.BLOCK_BELL_RESONATE, 1000, 3);
            player.playSound( player.getLocation(), Sound.BLOCK_BELL_RESONATE, 1000, 2);

            Location particleLoc = new Location( location.getWorld(), location.getX() + 0.5, location.getY() + 1, location.getZ() + 0.5 );
            player.getWorld().spawnParticle( Particle.EXPLOSION_NORMAL, particleLoc, 100);

            if ( consume )
            {
                player.getItemInHand().setAmount( player.getItemInHand().getAmount() -1 );
            }
        } else
        {
            Infinitum.getInstance().getServer().broadcastMessage( "§7Sieht aus als wenn der Kessel bereits reagiert..." );
        }
    }

    public void spawnCraftingResult(ItemTemplates result, Location location )
    {
        ItemStack itemStack = new ItemBuilder( result )
                .setGlowing()
                .build();

        Item item = location.getWorld().dropItem( Loc.centralize( location, 1.2 ), itemStack );
        item.setVelocity( new Vector( 0,0,0 ) );
        item.setGravity( false );
        item.setGlowing( true );
    }

    public static Crafting get() { return Infinitum.getInstance().getCrafting(); }
}
