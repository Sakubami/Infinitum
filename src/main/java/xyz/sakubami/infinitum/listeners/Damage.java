package xyz.sakubami.infinitum.listeners;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import xyz.sakubami.infinitum.Infinitum;
import xyz.sakubami.infinitum.world.functionality.Attribute;
import xyz.sakubami.infinitum.utils.InteractHelper;
import xyz.sakubami.infinitum.utils.builder.mob.nbt.MobNBT;
import xyz.sakubami.infinitum.world.entities.control.EntityControl;

public class Damage implements Listener {

    InteractHelper helper = new InteractHelper();
    MobNBT nbt = new MobNBT();

    @EventHandler
    public void onInteract( PlayerInteractEntityEvent e )
    {
        LivingEntity entity = helper.rightClickCustomEntity( e );
        new EntityControl( entity )
                .attribute( Attribute.MAX_HEALTH, 100 )
                .heal( 45 )
                .equip( new ItemStack( Material.DIAMOND_CHESTPLATE ), EquipmentSlot.CHEST )
                .queue();
    }

    @EventHandler
    public void onDamage( EntityDamageByEntityEvent e )
    {
        LivingEntity entity = ( LivingEntity ) e.getEntity();
        if ( nbt.hasNBT( entity ) )
        {
            e.setDamage( 0 );
            Infinitum.getInstance().getServer().broadcastMessage( "this mob has some nbt data");
            Infinitum.getInstance().getServer().broadcastMessage( "" + nbt.getNBTTags( entity ) );
            new EntityControl( entity )
                    .damage( 10 )
                    .queue();
        } else
        {
            Infinitum.getInstance().getServer().broadcastMessage( "this mob dont has nbt data");
        }
    }
}
