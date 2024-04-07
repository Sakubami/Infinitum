package xyz.sakubami.infinitum.world.entities.mob;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Listener;
import xyz.sakubami.infinitum.Infinitum;

import java.util.HashMap;
import java.util.UUID;

// active mobs

// private HashMap<MobMask, UUID> cache = new ArrayList<>();

// on spawn cache . put new mobs

// on kill delete bla bla

// update on change etc

public class MobConnector implements Listener {

    private final HashMap<UUID, MobMask> cache;

    public MobConnector()
    {
        this.cache = new HashMap<>();
    }

    public void add(MobMask mask )
    {
        cache.put( mask.getUuid(), mask );
    }

    public void remove(MobMask mask )
    {
        UUID uuid = mask.getUuid();
        if ( cache.get( uuid ) != null )
        cache.remove( uuid );
    }

    public void update(MobMask mask )
    {
        UUID uuid = mask.getUuid();
        if ( cache.get( uuid ) != null )
            cache.replace( uuid, mask );
    }

    public boolean contains( MobMask mask )
    {
        UUID uuid = mask.getUuid();
        return ( cache.get( uuid ) != null );
    }

    public MobMask get( LivingEntity entity )
    {
        UUID uuid = entity.getUniqueId();
        return ( cache.get( uuid ) );
    }

    public static MobConnector get() { return Infinitum.getInstance().getMobConnector(); }
}
