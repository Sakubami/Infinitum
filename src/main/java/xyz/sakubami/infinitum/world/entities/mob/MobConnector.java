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

    public void addMob( MobMask mask )
    {
        UUID uuid = mask.getUuid();
        cache.put( uuid, mask );
    }

    public void removeMob( MobMask mask )
    {
        UUID uuid = mask.getUuid();
        if ( cache.get( uuid ) != null )
        cache.remove( uuid );
    }

    public void updateMob( MobMask mask )
    {
        UUID uuid = mask.getUuid();
        if ( cache.get( uuid ) != null )
            cache.replace( uuid, mask );
    }

    public HashMap<UUID, MobMask> getCache()
    {
        return cache;
    }

    public static MobConnector get() { return Infinitum.getInstance().getMobController(); }
}
