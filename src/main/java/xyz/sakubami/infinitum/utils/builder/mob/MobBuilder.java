package xyz.sakubami.infinitum.utils.builder.mob;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import xyz.sakubami.infinitum.functionality.Attribute;
import xyz.sakubami.infinitum.utils.builder.mob.nbt.MobNBTApi;
import xyz.sakubami.infinitum.world.entities.control.CustomType;
import xyz.sakubami.infinitum.world.entities.control.EntityConnector;
import xyz.sakubami.infinitum.world.entities.control.EntityControl;
import xyz.sakubami.infinitum.world.entities.control.EntityMask;


import java.util.HashMap;

public class MobBuilder {

    private final EntityConnector connector = EntityConnector.get();
    private final MobNBTApi NBT = new MobNBTApi();
    private final EntityType type;
    private final HashMap<Attribute, Integer> attributes = new HashMap<>();
    private final World world;
    private final Location location;
    private String name = "§c" + "DEFAULT " + "§a" + 100 + "§f/§a" + 100 + "§7hp";

    // spawn at default location but teleport to desired location afterwards

    public MobBuilder( EntityType type, World world )
    {
        this.world = world;
        this.type = type;
        this.location = new Location( world, 0, 2000, 0 );
        this.NBT.addNBTTag( "CUSTOM", "true" );
    }

    /*
    public MobBuilder( MobTemplate template, World world )
    {
        this.world = world;
        this.type = template.getType();
        this.attributes = template.getAttributes();
        this.location = new Location( world, 0, 2000, 0 );
        this.NBT.addNBTTag( "CUSTOM", "true" );
    }
     */

    public MobBuilder attribute( Attribute attribute, int value )
    {
        this.attributes.put( attribute, value );
        this.NBT.addNBTTag( attribute.name(), String.valueOf( value ) );
        return this;
    }

    public MobBuilder name( String name )
    {
        this.name = "§c" + name + " §a" + 100 + "§f/§a" + 100 + "§7hp";
        return this;
    }

    public EntityMask build()
    {
        LivingEntity entity = ( LivingEntity ) world.spawnEntity( location, type );

        entity.getEquipment().clear();

        entity = NBT.parseAllNBTTags( entity );

        entity.setCustomName( name );
        entity.setCustomNameVisible( true );
        entity.getAttribute( org.bukkit.attribute.Attribute.GENERIC_KNOCKBACK_RESISTANCE ).setBaseValue( 1.0 );

        EntityMask mask = new EntityMask( entity, CustomType.MOB, 0, null, null, attributes );
        connector.add( mask );
        return mask;
    }
}
