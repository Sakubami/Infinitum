package xyz.sakubami.infinitum.rpg.world.entities.control;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import xyz.sakubami.infinitum.Infinitum;
import xyz.sakubami.infinitum.rpg.utils.math.MathUtils;
import xyz.sakubami.infinitum.rpg.world.entities.player.skills.ExperienceType;
import xyz.sakubami.infinitum.rpg.world.functionality.Attribute;

import java.util.HashMap;

public class EntityControl {

    private final EntityMask entityMask;
    private final MathUtils mathUtils = new MathUtils();
    private final EntityConnector connector = EntityConnector.get();
    private final HashMap<EntityMask, Boolean> queue = new HashMap<>();
    private final CustomType customType;
    private final HashMap<Attribute, Integer> attributes;

    public EntityControl( EntityMask entityMask)
    {
        this.entityMask = entityMask;
        this.customType = entityMask.getCustomType();
        this.attributes = entityMask.getMasked();
    }

    public EntityControl( LivingEntity entity )
    {
        this.entityMask = connector.get( entity );
        this.customType = entityMask.getCustomType();
        this.attributes = entityMask.getMasked();
    }

    public EntityControl teleport( Location location )
    {
        entityMask.getEntity().teleport( location );
        return this;
    }

    public EntityControl attribute( Attribute attribute, int v )
    {
        entityMask.getMasked().put( attribute, v );
        queue.put( entityMask, false );
        return this;
    }

    public EntityControl equip( ItemStack itemStack, EquipmentSlot slot )
    {
        LivingEntity entity = entityMask.getEntity();
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

    public EntityControl damage( int raw )
    {
        int finalDef = ( attributes.get( Attribute.DEFENSE ) / 100 ) +1;
        int damagedEHP = ( attributes.get( Attribute.HEALTH ) * finalDef ) - raw ;
        int damagedHP = damagedEHP / finalDef;

        if ( damagedHP <= 0 )
        {
            updateHealthDisplay( 0 );
            entityMask.getEntity().damage( 999999999 );
            if ( customType.equals( CustomType.MOB ) )
                queue.put( entityMask, true );
            else
                queue.put( entityMask, false );
        }
        else
        {
            entityMask.getEntity().damage( 0 );
            updateHealthDisplay( damagedHP );
            entityMask.getMasked().replace( Attribute.HEALTH, damagedHP );
            queue.put( entityMask, false );
        }
        return this;
    }

    public EntityControl kill()
    {
        updateHealthDisplay( 0 );
        entityMask.getEntity().damage( 999999999 );
        if ( customType.equals( CustomType.MOB ) )
            queue.put( entityMask, true );
        else
            queue.put( entityMask, false );
        return this;
    }

    public EntityControl heal( int v )
    {
        int healing = attributes.get( Attribute.HEALTH ) + v;
        int max = attributes.get( Attribute.MAX_HEALTH );

        if ( healing > max )
        {
            int finalMath = ( healing - max );
            updateHealthDisplay( healing - finalMath );
            entityMask.getMasked().replace( Attribute.HEALTH, healing - finalMath );
        } else
        {
            updateHealthDisplay( healing );
            entityMask.getMasked().replace( Attribute.HEALTH, healing );
        }

        queue.put( entityMask, false );
        return this;
    }

    private void updateHealthDisplay( int v )
    {
        ChatColor color = ChatColor.of( "#18ff03" );

        int max = attributes.get( Attribute.MAX_HEALTH );

        if ( mathUtils.percentageOf( v , 50 ) <= ( max / 2 ) )
            color = ChatColor.of( "#ffff03" );

        if ( customType.equals( CustomType.MOB ) )
            entityMask.getEntity().setCustomName( "§c" + entityMask.getName() + color + " " + v + "§f/" +  ChatColor.of( "#18ff03" ) + max + "§7hp" );
        else
        {
            // do title shit and shit and sujnosdahuoasdbuiasdbuiasdui im so high rn im tripping balls
            Infinitum.getInstance().getServer().broadcastMessage("a" );
        }
    }

    public EntityControl levelUp()
    {
        if ( customType.equals( CustomType.PLAYER ) )
        {
            entityMask.setLevel( entityMask.getLevel() +1 );
            queue.put( entityMask, false );
        }
        return this;
    }

    public EntityControl addExperience( ExperienceType type, int exp )
    {
        if ( customType.equals( CustomType.PLAYER ) )
        {
            entityMask.getExperience().replace( type, exp + entityMask.getExperience().get( type ) );
            queue.put( entityMask, false );
        }
        return this;
    }

    public EntityControl setExperience( ExperienceType type, int exp )
    {
        if ( customType.equals( CustomType.PLAYER ) )
        {
            entityMask.getExperience().replace( type, exp );
            queue.put( entityMask, false );
        }
        return this;
    }

    public void queue()
    {
        for ( EntityMask mask : queue.keySet() )
        {
            if ( connector.contains( mask ) )
                connector.update( mask );

            if ( queue.get( mask ) )
                connector.remove( mask );
        }
    }
}
