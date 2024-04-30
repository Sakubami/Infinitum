package xyz.sakubami.infinitum.rpg.world.functionality.damaging;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import xyz.sakubami.infinitum.Infinitum;
import xyz.sakubami.infinitum.rpg.utils.NBTUtils;
import xyz.sakubami.infinitum.rpgcore.utils.builder.mob.CustomEntityBuilderUtils;
import xyz.sakubami.infinitum.rpg.utils.math.EntityMath;
import xyz.sakubami.infinitum.rpgcore.utils.control.EntityConnector;
import xyz.sakubami.infinitum.rpgcore.utils.control.EntityManipulator;
import xyz.sakubami.infinitum.rpgcore.utils.control.EntityMask;
import xyz.sakubami.infinitum.rpg.world.functionality.Attribute;
import xyz.sakubami.infinitum.rpg.world.functionality.items.control.ItemArchive;
import xyz.sakubami.infinitum.rpg.world.functionality.items.control.ItemMask;
import xyz.sakubami.infinitum.rpg.world.functionality.items.enchanting.EnchantmentManager;

import java.util.ArrayList;
import java.util.UUID;


public class Attack {

    private int raw;
    private final EntityMask damager;
    private final ArrayList< EntityMask > receivers = new ArrayList<>();
    private boolean doCritical = true;
    private final EntityMath entityMath = new EntityMath();
    private final EntityConnector connector = EntityConnector.get();
    private final NBTUtils nbt = new NBTUtils();
    private final CustomEntityBuilderUtils builderUtils = CustomEntityBuilderUtils.get();
    private final EnchantmentManager enchantmentManager = new EnchantmentManager();
    private final ItemArchive archive = ItemArchive.get();

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
        int criticalChance = damager.getMasked().get( Attribute.CRITICAL_CHANCE);
        int strength = damager.getMasked().get( Attribute.STRENGTH );
        int criticalDamage = damager.getMasked().get( Attribute.CRITICAL_DAMAGE );
        int additive = 1;
        int multiplicative = 1;

        ItemMask itemMask = archive.get( UUID.fromString( nbt.getItemNBTString( damager.getEntity().getEquipment().getItemInMainHand(), "UUID" ) ) );
        if ( !itemMask.getEnchantments().isEmpty() )
            enchantmentManager.runEnchants( itemMask, damager.getUuid() );

        if ( !itemMask.getItem().getType().equals( Material.AIR ) )
        {
            if ( itemMask.getAttributes().get( Attribute.DAMAGE ) != 0 )
                raw += itemMask.getAttributes().get( Attribute.DAMAGE );
            if ( itemMask.getAttributes().get( Attribute.ADDITIVE ) != 0 )
                additive += itemMask.getAttributes().get( Attribute.ADDITIVE );
            if ( itemMask.getAttributes().get( Attribute.MULTIPLICATIVE ) != 0 )
                multiplicative += itemMask.getAttributes().get( Attribute.MULTIPLICATIVE );
            if ( itemMask.getAttributes().get( Attribute.STRENGTH ) != 0 )
                strength += itemMask.getAttributes().get( Attribute.STRENGTH );
            if ( itemMask.getAttributes().get( Attribute.CRITICAL_DAMAGE ) != 0 )
                criticalDamage += itemMask.getAttributes().get( Attribute.CRITICAL_DAMAGE );
            if ( itemMask.getAttributes().get( Attribute.CRITICAL_CHANCE ) != 0 )
                criticalChance += itemMask.getAttributes().get( Attribute.CRITICAL_CHANCE );
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
            new EntityManipulator( receiver )
                    .damage( finalDamage )
                    .queue();
            builderUtils.createDamageTag( critical, finalDamage, receiver.getEntity().getWorld(), receiver.getEntity().getLocation() );
            Infinitum.getInstance().getServer().broadcastMessage( "damage done = " + finalDamage );
        }
    }
}
