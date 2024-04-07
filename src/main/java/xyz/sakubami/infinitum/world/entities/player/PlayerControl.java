package xyz.sakubami.infinitum.world.entities.player;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import xyz.sakubami.infinitum.functionality.Attribute;
import xyz.sakubami.infinitum.world.entities.player.skills.ExperienceType;

import java.util.ArrayList;

public class PlayerControl {

    private final PlayerMask mask;
    private final PlayerConnector connector = PlayerConnector.get();
    private final ArrayList<PlayerMask> queue = new ArrayList<>();

    public PlayerControl( PlayerMask mask)
    {
        this.mask = mask;
    }

    public PlayerControl( Player mask)
    {
        this.mask = connector.get( mask.getUniqueId() );
    }

    public PlayerControl teleport( Location location )
    {
        mask.getPlayer().teleport( location );
        return this;
    }

    public PlayerControl attribute( Attribute attribute, int value )
    {
        mask.getAttributes().put( attribute, value );
        queue.add( mask );
        return this;
    }

    public PlayerControl equip( ItemStack itemStack, EquipmentSlot slot )
    {
        LivingEntity entity = mask.getPlayer();
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

    public PlayerControl damage( int amount )
    {
        int math = mask.getAttributes().get( Attribute.HEALTH ) - amount;
        if ( math <= 0 )
        {
            mask.getPlayer().damage( 999999999 );
            queue.add( mask );
        }
        else
            mask.getAttributes().replace( Attribute.HEALTH, math );
        queue.add( mask );
        return this;
    }

    public PlayerControl kill()
    {
        mask.getPlayer().damage( 999999999 );
        queue.add( mask );
        return this;
    }

    public PlayerControl heal( int amount )
    {
        int math = mask.getAttributes().get( Attribute.HEALTH ) + amount;
        int max = mask.getAttributes().get( Attribute.MAX_HEALTH );
        mask.getAttributes().replace( Attribute.HEALTH, Math.min( math, max ) );
        queue.add( mask );
        return this;
    }

    public PlayerControl levelUp()
    {
        mask.setLevel( mask.getLevel() +1 );
        queue.add( mask );
        return this;
    }

    public PlayerControl addExperience( ExperienceType type, int exp )
    {
        mask.getExperience().replace( type, exp + mask.getExperience().get( type ) );
        queue.add( mask );
        return this;
    }

    public PlayerControl setExperience( ExperienceType type, int exp )
    {
        mask.getExperience().replace( type, exp );
        queue.add( mask );
        return this;
    }

    public void queue()
    {
        for ( PlayerMask mask : queue )
        {
            connector.update( mask );
        }
    }
}
