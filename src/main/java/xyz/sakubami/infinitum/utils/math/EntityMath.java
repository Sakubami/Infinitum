package xyz.sakubami.infinitum.utils.math;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import xyz.sakubami.infinitum.utils.builder.mob.CustomEntityBuilder;

import java.util.List;

public class EntityMath {

    public List<Entity> getRadius( Location location, float radius )
    {
        ArmorStand placeHolder = new CustomEntityBuilder( location.getWorld(), location ).getPlaceHolder();
        List<Entity> entities = placeHolder.getNearbyEntities( radius, radius, radius );
        placeHolder.remove();
        return entities;
    }
}
