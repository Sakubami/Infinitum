package xyz.sakubami.infinitum.utils.builder.mob;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import xyz.sakubami.infinitum.functionality.Attribute;
import xyz.sakubami.infinitum.utils.NBTUtils;
import xyz.sakubami.infinitum.world.entities.mob.MobMask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class MobBuilder {

    private final MobNBTApi NBT = new MobNBTApi();
    private final EntityType type;
    private final UUID uuid = UUID.randomUUID();
    private final HashMap<Attribute, Integer> attributes = new HashMap<>();
    private final World world;
    private final Location location;
    private String name = "NULL";

    // spawn at default location but teleport to desired location afterwards

    public MobBuilder( EntityType type, World world )
    {
        this.world = world;
        this.type = type;
        this.location = new Location( world, 0, 2000, 0 );
    }

    public MobBuilder attribute( Attribute attribute, int value )
    {
        this.attributes.put( attribute, value );
        this.NBT.addNBTTag( attribute.name(), String.valueOf( value ) );
        return this;
    }

    public MobBuilder name( String name )
    {
        this.name = name;
        return this;
    }

    public MobMask build()
    {
        LivingEntity entity = ( LivingEntity ) world.spawnEntity( location, type );

        if ( !attributes.isEmpty() )
            entity = NBT.parseAllNBTTags( entity );

        entity.setCustomName( name );
        entity.setCustomNameVisible( true );

        return new MobMask( entity, attributes );
    }
}
