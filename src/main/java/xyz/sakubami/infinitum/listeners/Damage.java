package xyz.sakubami.infinitum.listeners;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import xyz.sakubami.infinitum.Infinitum;
import xyz.sakubami.infinitum.utils.InteractHelper;
import xyz.sakubami.infinitum.utils.NBTUtils;
import xyz.sakubami.infinitum.world.entities.control.EntityConnector;
import xyz.sakubami.infinitum.world.entities.control.EntityControl;
import xyz.sakubami.infinitum.world.functionality.damaging.Attack;

public class Damage implements Listener {

    InteractHelper helper = new InteractHelper();
    NBTUtils nbt = new NBTUtils();

    @EventHandler
    public void onInteract( PlayerInteractEntityEvent e )
    {


        if ( helper.rightClickCustomEntity( e ) )
        {
            LivingEntity entity = ( LivingEntity) e.getRightClicked();

            new EntityControl( entity )
                    .equip( new ItemStack( Material.DIAMOND_CHESTPLATE ), EquipmentSlot.CHEST )
                    .queue();
        }
    }

    @EventHandler
    public void onDamage( EntityDamageByEntityEvent e )
    {
        EntityConnector connector = EntityConnector.get();
        LivingEntity entity = ( LivingEntity ) e.getEntity();

        if ( nbt.checkForEntityNBTData( entity ) || entity instanceof Player )
        {
            // more checks
            e.setDamage( 0 );
            new Attack( 500, connector.get( ( LivingEntity ) e.getDamager() ), connector.get( entity ) )
                    .setAOE( 5, entity.getLocation() )
                    .attack();
            Infinitum.getInstance().getServer().broadcastMessage( "this mob has some nbt data");
            Infinitum.getInstance().getServer().broadcastMessage( "" + nbt.getEntityNBTTags( entity ) );
        } else
        {
            Infinitum.getInstance().getServer().broadcastMessage( "this mob dont has nbt data" );
        }
    }
}
