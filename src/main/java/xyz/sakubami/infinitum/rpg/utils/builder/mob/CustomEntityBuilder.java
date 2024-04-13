package xyz.sakubami.infinitum.rpg.utils.builder.mob;

import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import xyz.sakubami.infinitum.rpg.utils.NBTUtils;
import xyz.sakubami.infinitum.rpg.world.entities.control.CustomType;
import xyz.sakubami.infinitum.rpg.world.entities.control.EntityConnector;
import xyz.sakubami.infinitum.rpg.world.entities.control.EntityMask;
import xyz.sakubami.infinitum.rpg.world.functionality.Attribute;

import java.util.HashMap;

public class CustomEntityBuilder {

    private final EntityConnector connector = EntityConnector.get();
    private final NBTUtils nbt = new NBTUtils();
    private final EntityType type;
    private final HashMap< Attribute, Integer > attributes = new HashMap<>();
    private final World world;
    private final Location location;
    private LivingEntity entity;
    private String nameReplacement = "DEFAULT";
    private int health = 100000;
    private int max_health = 100000;
    private String name = "§c" + WordUtils.capitalizeFully( "DEFAULT" ) + ChatColor.of( "#18ff03" ) + " " + 100 + "§f/" +  ChatColor.of( "#18ff03" ) + 100 + "§7hp";

    // spawn at default location but teleport to desired location afterwards

    private void addDefaultAttributes()
    {
        attributes.put( Attribute.HEALTH, 100 );
        attributes.put( Attribute.MAX_HEALTH, 100 );
        attributes.put( Attribute.DEFENSE, 0 );
        attributes.put( Attribute.STRENGTH, 0 );
    }

    public CustomEntityBuilder( EntityType type, World world )
    {
        this.world = world;
        this.type = type;
        this.location = new Location( world, 0, 200, 0 );
        this.nbt.addNBTTag( "CUSTOM", "true" );
        this.entity = ( LivingEntity ) world.spawnEntity( location, type );
        addDefaultAttributes();
    }

    public CustomEntityBuilder( EntityType type, World world, Location location )
    {
        this.world = world;
        this.type = type;
        this.location = location;
        this.nbt.addNBTTag( "CUSTOM", "true" );
        this.entity = ( LivingEntity ) world.spawnEntity( location, type );
        addDefaultAttributes();
    }

    /**
     * used to return a placeholder as an {@link ArmorStand} entity
     */
    public CustomEntityBuilder( World world, Location location  )
    {
        this.world = world;
        this.type = EntityType.ARMOR_STAND;
        this.location = location;
        this.nbt.addNBTTag( "CUSTOM", "true" );
        this.entity = ( LivingEntity ) world.spawnEntity( location, EntityType.ARMOR_STAND );
    }

    /*
    public CustomEntityBuilder( MobTemplate template, World world )
    {
        this.world = world;
        this.type = template.getType();
        this.attributes = template.getAttributes();
        this.location = new Location( world, 0, 2000, 0 );
        this.NBT.addNBTTag( "CUSTOM", "true" );
    }
     */

    public CustomEntityBuilder attribute( Attribute attribute, int value )
    {
        if ( this.attributes.containsKey( attribute ) )
        {
            this.attributes.replace( attribute, value );
        } else
            this.attributes.put( attribute, value );
        return this;
    }

    public CustomEntityBuilder name( String name )
    {
        this.nameReplacement = name;
        return this;
    }

    public CustomEntityBuilder health( int v )
    {
        attribute( Attribute.HEALTH, v );
        this.health = v;
        return this;
    }

    public CustomEntityBuilder maxHealth( int v )
    {
        attribute( Attribute.MAX_HEALTH, v );
        this.max_health = v;
        return this;
    }

    public CustomEntityBuilder strength( int v )
    {
        attribute( Attribute.STRENGTH, v );
        return this;
    }

    public CustomEntityBuilder antiKB()
    {
        entity.getAttribute( org.bukkit.attribute.Attribute.GENERIC_KNOCKBACK_RESISTANCE ).setBaseValue( 1.0 );
        return this;
    }

    public EntityMask build()
    {
        entity = nbt.parseAllEntityNBTTags( entity );

        entity.getEquipment().clear();
        entity.setCustomName( "§c" + nameReplacement + ChatColor.of( "#18ff03" ) + " " + health + "§f/" +  ChatColor.of( "#18ff03" ) + max_health + " §c❤" );
        entity.setCustomNameVisible( true );

        EntityMask mask = new EntityMask( nameReplacement, entity, CustomType.MOB, 0, null, null, attributes );
        connector.add( mask );
        return mask;
    }

    public ArmorStand getPlaceHolder()
    {
        ArmorStand placeHolder = ( ArmorStand ) entity;
        placeHolder.setGravity( false );
        placeHolder.setInvulnerable( true );
        placeHolder.setVisible( false );

        return placeHolder;
    }

}
