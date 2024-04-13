package xyz.sakubami.infinitum.rpg.utils;

import org.bukkit.Location;

public class Loc {

    public static Location normalize( Location location )
    {
        return new Location( location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ() );
    }

    public static Location centralize( Location location, double high )
    {
        return new Location( location.getWorld(), location.getBlockX() + 0.5 , location.getBlockY() + high, location.getBlockZ() + 0.5 );
    }
}
