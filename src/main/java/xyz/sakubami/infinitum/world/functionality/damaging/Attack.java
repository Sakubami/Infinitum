package xyz.sakubami.infinitum.world.functionality.damaging;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import xyz.sakubami.infinitum.Infinitum;
import xyz.sakubami.infinitum.utils.NBTUtils;
import xyz.sakubami.infinitum.utils.math.EntityMath;
import xyz.sakubami.infinitum.world.entities.control.EntityConnector;
import xyz.sakubami.infinitum.world.entities.control.EntityControl;
import xyz.sakubami.infinitum.world.entities.control.EntityMask;
import xyz.sakubami.infinitum.world.functionality.Attribute;

import java.util.ArrayList;
import java.util.Random;


public class Attack {

    private int raw;
    private EntityMask damager;
    private ArrayList<EntityMask> receivers = new ArrayList<>();

    private final EntityMath entityMath = new EntityMath();
    private final EntityConnector connector = EntityConnector.get();
    private final NBTUtils nbt = new NBTUtils();

    // USAGE who = false == received, true == dealt

    public Attack( int raw, EntityMask damager, EntityMask receiver )
    {
        this.raw = raw;
        this.damager = damager;
        this.receivers.add( receiver );
    }

    public Attack( int raw, EntityMask damager )
    {
        this.raw = raw;
        this.damager = damager;
    }

    public Attack( int raw, EntityDamageByEntityEvent event )
    {
        this.raw = raw;
        this.damager = connector.get( event.getDamager().getUniqueId() );
        this.receivers.add( connector.get( event.getEntity().getUniqueId() ) );
    }

    public Attack killEffect()
    {
        // effect manager
        return this;
    }

    public Attack effect()
    {
        // maybe like swing effects idk
        return this;
    }

    public Attack setAOE( int radius, Location location )
    {
        for ( Entity entity : entityMath.getRadius( location, radius ) )
        {
            if ( entity.getUniqueId().equals( damager.getUuid() ) )
                continue;

            receivers.add( connector.get( entity.getUniqueId() ) );
        }
        return this;
    }

    public void attack()
    {
        int weaponDamage = 0;
        int criticalChance = damager.getMasked().get( Attribute.CRITICAL_CHANCE);
        int weaponStrength = 0;
        int weaponCriticalDamage= 0;
        int strength = damager.getMasked().get( Attribute.STRENGTH );
        int criticalDamage = damager.getMasked().get( Attribute.CRITICAL_DAMAGE );
        int additive = 0;
        int multiplicative = 0;

        ItemStack itemStack = damager.getEntity().getEquipment().getItemInMainHand();

        if ( !itemStack.getType().equals( Material.AIR ) )
        {
            if ( !nbt.getItemNBTTags( itemStack ).isEmpty() )
            {
                raw += weaponDamage = Integer.parseInt( nbt.getItemNBTString( itemStack, "ATTRIBUTE_DAMAGE" ) + 5 );
                additive = Integer.parseInt( nbt.getItemNBTString( itemStack, "ATTRIBUTE_ADDITIVE" ) );
                multiplicative = Integer.parseInt( nbt.getItemNBTString( itemStack, "ATTRIBUTE_MULTIPLICATIVE" ) );
                strength += weaponStrength = Integer.parseInt( nbt.getItemNBTString( itemStack, "ATTRIBUTE_STRENGTH" ) );
                criticalDamage += weaponCriticalDamage = Integer.parseInt( nbt.getItemNBTString( itemStack, "ATTRIBUTE_CRITICAL_DAMAGE" ) );
                criticalChance += criticalChance = Integer.parseInt( nbt.getItemNBTString( itemStack, "ATTRIBUTE_CRITICAL_CHANCE" ) );
            }
        }

        Random random = new Random();
        if ( criticalChance < random.nextInt( 100 ) )
        {
            criticalChance = 0;
            Infinitum.getInstance().getServer().broadcastMessage(" not a crit ");
        } else
            Infinitum.getInstance().getServer().broadcastMessage(" crit lol ");


        int finalDamage = ( raw ) * ( 1+ ( strength / 100 ) ) * ( 1 + criticalDamage / 100 ) * ( 1 + additive ) * ( 1 + multiplicative );

        for ( EntityMask receiver : receivers )
        {
            new EntityControl( receiver )
                    .damage( finalDamage )
                    .queue();
            Infinitum.getInstance().getServer().broadcastMessage( "damage done= " + finalDamage );
        }
    }
}
