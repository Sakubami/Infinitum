package xyz.sakubami.infinitum.world.entities.mob;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Listener;
import xyz.sakubami.infinitum.Infinitum;

import java.net.http.WebSocket;
import java.util.HashMap;
import java.util.UUID;

// active mobs

// private HashMap<CustomMob, UUID> cache = new ArrayList<>();

// on spawn cache . put new mobs

// on kill delete bla bla

// update on change etc

public class MobController implements Listener {

    private final HashMap<UUID, CustomMob> cache;

    public MobController()
    {
        this.cache = new HashMap<>();
    }

    public void addMob( CustomMob entity )
    {
        cache.put( entity.getUuid(), entity );
    }

    public void removeMob( CustomMob entity )
    {
        UUID uuid = entity.getUuid();
        if ( cache.get( uuid ) != null )
        cache.remove( uuid );
    }

    public void updateMob( CustomMob entity )
    {
        UUID uuid = entity.getUuid();
        if ( cache.get( uuid ) != null )
            cache.replace( uuid, entity );
    }

    public void setHealth( UUID uuid, int amount )
    {
        CustomMob mob = cache.get( uuid );
        mob.setHealth( amount );
        updateMob( mob );
    }

    public void damage( UUID uuid, int amount )
    {
        CustomMob mob = cache.get( uuid );
        int math = mob.getHealth() - amount;
        if ( math <= 0 ) {
            mob.getEntity().remove();
            removeMob( mob );
        }
        else
            updateMob( mob );
    }

    public void heal( UUID uuid, int amount )
    {

    }

    public HashMap<UUID, CustomMob> getCache()
    {
        return cache;
    }

    public static MobController get() { return Infinitum.getInstance().getMobController(); }
}
