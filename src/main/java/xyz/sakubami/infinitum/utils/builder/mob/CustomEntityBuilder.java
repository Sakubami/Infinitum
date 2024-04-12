package xyz.sakubami.infinitum.utils.builder.mob;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import xyz.sakubami.infinitum.utils.NBTUtils;
import xyz.sakubami.infinitum.world.functionality.Attribute;
import xyz.sakubami.infinitum.world.entities.control.CustomType;
import xyz.sakubami.infinitum.world.entities.control.EntityConnector;
import xyz.sakubami.infinitum.world.entities.control.EntityMask;


import java.util.HashMap;

public class CustomEntityBuilder {

    private final EntityConnector connector = EntityConnector.get();
    private final NBTUtils nbt = new NBTUtils();
    private final EntityType type;
    private final HashMap<Attribute, Integer> attributes = new HashMap<>();
    private final World world;
    private final Location location;
    private String name = "§c" + "DEFAULT " + "§a" + 100 + "§f/§a" + 100 + "§7hp";

    // spawn at default location but teleport to desired location afterwards

    public CustomEntityBuilder( EntityType type, World world )
    {
        this.world = world;
        this.type = type;
        this.location = new Location( world, 0, 2000, 0 );
        this.nbt.addNBTTag( "CUSTOM", "true" );
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
        this.attributes.put( attribute, value );
        this.nbt.addNBTTag( "ATTRIBUTE_" + attribute.name(), value );
        return this;
    }

    public CustomEntityBuilder name( String name )
    {
        this.name = "§c" + name + " §a" + 100 + "§f/§a" + 100 + "§7hp";
        return this;
    }

    public EntityMask build()
    {
        LivingEntity entity = ( LivingEntity ) world.spawnEntity( location, type );

        entity.getEquipment().clear();

        nbt.addNBTTag( "ID", "true");
        entity = nbt.parseAllEntityNBTTags( entity );

        entity.setCustomName( name );
        entity.setCustomNameVisible( true );
        entity.getAttribute( org.bukkit.attribute.Attribute.GENERIC_KNOCKBACK_RESISTANCE ).setBaseValue( 1.0 );

        EntityMask mask = new EntityMask( entity, CustomType.MOB, 0, null, null, attributes );
        connector.add( mask );
        return mask;
    }

    public ArmorStand getPlaceHolder()
    {
        ArmorStand placeHolder = ( ArmorStand ) world.spawnEntity( location, EntityType.ARMOR_STAND );
        placeHolder.setGravity( false );
        placeHolder.setInvulnerable( true );
        placeHolder.setVisible( false );

        return placeHolder;
    }

}
