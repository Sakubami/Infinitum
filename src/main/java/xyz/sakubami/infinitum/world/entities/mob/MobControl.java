package xyz.sakubami.infinitum.world.entities.mob;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import xyz.sakubami.infinitum.functionality.Attribute;

import java.util.HashMap;

public class MobControl {

    private final MobMask mob;
    private final MobConnector connector = MobConnector.get();
    private final HashMap<MobMask, Boolean> queue = new HashMap<>();

    public MobControl( MobMask mob )
    {
        this.mob = mob;
    }

    public MobControl( LivingEntity entity )
    {
        this.mob = connector.get( entity );
    }

    public MobControl teleport( Location location )
    {
        mob.getEntity().teleport( location );
        return this;
    }

    public MobControl attribute( Attribute attribute, int v )
    {
        mob.getMask().put( attribute, v );
        queue.put( mob, false );
        return this;
    }

    public MobControl equip( ItemStack itemStack, EquipmentSlot slot )
    {
        LivingEntity entity = mob.getEntity();
        if ( slot.equals( EquipmentSlot.HEAD ) )
            entity.getEquipment().setHelmet( itemStack );
        if ( slot.equals( EquipmentSlot.CHEST ) )
            entity.getEquipment().setChestplate( itemStack );
        if ( slot.equals( EquipmentSlot.LEGS ) )
            entity.getEquipment().setLeggings( itemStack );
        if ( slot.equals( EquipmentSlot.FEET ) )
            entity.getEquipment().setBoots( itemStack );
        if ( slot.equals( EquipmentSlot.HAND ) )
            entity.getEquipment().setItemInMainHand( itemStack );
        if ( slot.equals( EquipmentSlot.OFF_HAND ) )
            entity.getEquipment().setItemInOffHand( itemStack );
        return this;
    }

    public MobControl damage( int v )
    {
        int math = mob.getMask().get( Attribute.HEALTH ) - v;
        if ( math <= 0 )
        {
            updateHealthDisplay( 0 );
            mob.getEntity().damage( 999999999 );
            queue.put( mob, true );
        }
        else
        {
            updateHealthDisplay( math );
            mob.getMask().replace( Attribute.HEALTH, math );
            queue.put( mob, false );
        }
        return this;
    }

    public MobControl kill()
    {
        updateHealthDisplay( 0 );
        mob.getEntity().damage( 999999999 );
        queue.put( mob, true );
        return this;
    }

    public MobControl heal( int v )
    {
        int math = mob.getMask().get( Attribute.HEALTH ) + v;
        int max = mob.getMask().get( Attribute.MAX_HEALTH );
        if ( math > max )
        {
            int finalMath = ( math - max ) - math;
            updateHealthDisplay( finalMath );
            mob.getMask().replace( Attribute.HEALTH, finalMath );
        } else
        {
            updateHealthDisplay( math );
            mob.getMask().replace( Attribute.HEALTH, math );
        }
        queue.put( mob, false );
        return this;
    }

    private void updateHealthDisplay( int v )
    {
        int max = mob.getMask().get( Attribute.MAX_HEALTH );
        mob.getEntity().setCustomName( "§c" + WordUtils.capitalizeFully( mob.getEntity().getType().name() ) + " §a" + v + "§f/§a" + max + "§7hp" );
    }

    public void queue()
    {
        for ( MobMask mask : queue.keySet() )
        {
            if ( connector.contains( mask ) )
                connector.update( mask );

            if ( queue.get( mask ) )
                connector.remove( mask );
        }
    }
}
