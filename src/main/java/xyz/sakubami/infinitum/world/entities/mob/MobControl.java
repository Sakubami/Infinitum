package xyz.sakubami.infinitum.world.entities.mob;

import org.bukkit.Location;
import org.bukkit.World;
import xyz.sakubami.infinitum.functionality.Attribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class MobControl {

    private CustomMob mob;
    private ArrayList<CustomMob> crowd = new ArrayList<>();
    private final MobController controller = MobController.get();
    private final HashMap<UUID, CustomMob> cache = controller.getCache();
    private final HashMap<CustomMob, Boolean> queue = new HashMap<>();

    public MobControl( CustomMob mob )
    {
        this.mob = mob;
    }

    public MobControl( ArrayList<CustomMob> crowd )
    {
        this.crowd = crowd;
    }

    public MobControl spawnSingle( World world, Location location )
    {
        queue.put( mob, true );
        return this;
    }

    public MobControl spawnCrowdAtLocations( World world, double spread )
    {
        if ( !crowd.isEmpty() )
            for ( CustomMob mob : crowd ) {
                queue.put( mob, true );
            }
        return this;
    }

    public MobControl modify( int amount, Attribute attribute )
    {

        return this;
    }

    public MobControl update()
    {
        for ( CustomMob mob : queue.keySet() ) {
            if ( cache.containsValue( mob ) )
                controller.updateMob( mob );
            else if ( queue.get( mob ) )
                controller.addMob( mob );
            else if ( cache.containsValue( mob ) )
                controller.removeMob( mob );
        }
        return this;
    }
}
