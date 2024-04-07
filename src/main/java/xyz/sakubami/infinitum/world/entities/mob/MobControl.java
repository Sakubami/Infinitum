package xyz.sakubami.infinitum.world.entities.mob;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import xyz.sakubami.infinitum.functionality.Attribute;
import xyz.sakubami.infinitum.utils.builder.mob.nbt.MobNBTApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class MobControl {

    private final MobNBTApi NBT = new MobNBTApi();
    private MobMask mob;
    private ArrayList<MobMask> crowd = new ArrayList<>();
    private final MobConnector connector = MobConnector.get();
    private final HashMap<UUID, MobMask> cache = connector.getCache();
    private final HashMap<MobMask, Boolean> queue = new HashMap<>();

    public MobControl( MobMask mob )
    {
        this.mob = mob;
    }

    public MobControl( ArrayList<MobMask> crowd )
    {
        this.crowd = crowd;
    }

    public MobControl spawnSingle( World world, Location location )
    {
        queue.put( mob, true );
        return this;
    }

    public MobControl spawnCrowdAtLocations( World world, double spread )
    {
        if ( !crowd.isEmpty() )
            for ( MobMask mob : crowd ) {
                queue.put( mob, true );
            }
        return this;
    }

    public MobControl teleport( Location location )
    {
        // do stuff
        return this;
    }

    public MobControl attribute( Attribute attribute, int amount )
    {
        NBT.addNBTTag( attribute.name(), String.valueOf( amount ) );
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
        entity.getEquipment().clear();
        return this;
    }

    public MobControl damage( int amount )
    {
        int math = mob.getMask().get( Attribute.HEALTH ) - amount;
        if ( math <= 0 )
        {
            mob.getEntity().damage( 999999999 );
            queue.put( mob, false );
        }
        else
            mob.getMask().replace( Attribute.HEALTH, math );
            queue.put( mob, true );
        return this;
    }

    public MobControl heal( int amount )
    {
        int math = mob.getMask().get( Attribute.HEALTH ) + amount;
        int max = mob.getMask().get( Attribute.MAX_HEALTH );
        mob.getMask().replace( Attribute.HEALTH, Math.min( math, max ) );
        queue.put( mob, true );
        return this;
    }

    public MobControl update()
    {
        for ( MobMask mask : queue.keySet() ) {
            if ( cache.containsValue( mask ) )
                connector.updateMob( mask );
            else if ( queue.get( mask ) )
                connector.addMob( mask );
            else if ( cache.containsValue( mask ) )
                connector.removeMob( mask );
        }
        return this;
    }
}
