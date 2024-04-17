package xyz.sakubami.infinitum.rpg.world.functionality.damaging;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import xyz.sakubami.infinitum.Infinitum;
import xyz.sakubami.infinitum.external.chat.ChatAdapter;
import xyz.sakubami.infinitum.rpg.utils.NBTUtils;
import xyz.sakubami.infinitum.rpg.utils.builder.mob.CustomEntityBuilderUtils;
import xyz.sakubami.infinitum.rpg.utils.math.EntityMath;
import xyz.sakubami.infinitum.rpg.world.entities.control.EntityConnector;
import xyz.sakubami.infinitum.rpg.world.entities.control.EntityControl;
import xyz.sakubami.infinitum.rpg.world.entities.control.EntityMask;
import xyz.sakubami.infinitum.rpg.world.functionality.Attribute;

import java.util.ArrayList;


public class Attack {

    private int raw;
    private final EntityMask damager;
    private final ArrayList< EntityMask > receivers = new ArrayList<>();
    private boolean doCritical = true;
    private final EntityMath entityMath = new EntityMath();
    private final EntityConnector connector = EntityConnector.get();
    private final NBTUtils nbt = new NBTUtils();
    private final CustomEntityBuilderUtils builderUtils = CustomEntityBuilderUtils.get();

    // USAGE who = false == received, true == dealt

    public Attack( int raw, EntityMask damager, EntityMask receiver )
    {
        this.raw = raw;
        this.damager = damager;
        this.receivers.add( receiver );
    }

    public Attack( int raw, LivingEntity entity )
    {
        this.raw = raw;
        this.damager = connector.get( entity.getUniqueId() );
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

    public Attack noCritical()
    {
        this.doCritical = false;
        return this;
    }

    public Attack setAOE( int radius, Location location )
    {
        Infinitum.getInstance().getServer().broadcastMessage( ChatAdapter.convert(  "aoe debug" ) );

        for ( Entity entity : entityMath.getRadius( location, radius ) )
        {
            if ( ! ( entity instanceof LivingEntity ) )
                continue;

            if ( entity.getUniqueId().equals( damager.getUuid() ) )
                continue;

            if ( receivers.contains( connector.get( entity.getUniqueId() ) ) )
                continue;

            if ( connector.get( entity.getUniqueId() ) == null )
                continue;

            if ( entity.getType().equals( EntityType.ARMOR_STAND ) )
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
        int additive = 1;
        int multiplicative = 1;

        ItemStack itemStack = damager.getEntity().getEquipment().getItemInMainHand();

        if ( !itemStack.getType().equals( Material.AIR ) )
        {
            if ( !nbt.getItemNBTTags( itemStack ).isEmpty() )
            {
                if ( nbt.getItemNBTString( itemStack, "ATTRIBUTE_DAMAGE" ) != null )
                    raw += weaponDamage = nbt.getItemNBTInt( itemStack, "ATTRIBUTE_DAMAGE" );
                if ( nbt.getItemNBTString( itemStack, "ATTRIBUTE_ADDITIVE" ) != null )
                    additive += nbt.getItemNBTInt( itemStack, "ATTRIBUTE_ADDITIVE" );
                if ( nbt.getItemNBTString( itemStack, "ATTRIBUTE_MULTIPLICATIVE" ) != null )
                    multiplicative += nbt.getItemNBTInt( itemStack, "ATTRIBUTE_MULTIPLICATIVE" );
                if ( nbt.getItemNBTString( itemStack, "ATTRIBUTE_STRENGTH" ) != null )
                    strength += weaponStrength = nbt.getItemNBTInt( itemStack, "ATTRIBUTE_STRENGTH" );
                if ( nbt.getItemNBTString( itemStack, "ATTRIBUTE_CRITICAL_DAMAGE" ) != null )
                    criticalDamage += weaponCriticalDamage = nbt.getItemNBTInt( itemStack, "ATTRIBUTE_CRITICAL_DAMAGE" );
                if ( nbt.getItemNBTString( itemStack, "ATTRIBUTE_CRITICAL_CHANCE" ) != null )
                    criticalChance += criticalChance = nbt.getItemNBTInt( itemStack, "ATTRIBUTE_CRITICAL_CHANCE" );
            }
        }

        boolean critical = true;

        if ( criticalChance < Infinitum.getInstance().getRandomGenerator().nextInt( 100 ) || !doCritical )
        {
            critical = false;
            criticalDamage = 0;
        }

        int finalDamage = ( int ) Math.round( ( raw + 5 ) * ( 1 + ( ( double ) strength / 100 ) ) * ( 1 + ( ( double ) criticalDamage / 100 ) ) * additive * multiplicative );

        for ( EntityMask receiver : receivers )
        {
            new EntityControl( receiver )
                    .damage( finalDamage )
                    .queue();
            builderUtils.createDamageTag( critical, finalDamage, receiver.getEntity().getWorld(), receiver.getEntity().getLocation() );
            Infinitum.getInstance().getServer().broadcastMessage( "damage done= " + finalDamage );
        }
    }
}
