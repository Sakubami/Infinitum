package xyz.sakubami.infinitum.rpg.world.functionality.crafting.stations;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import xyz.sakubami.infinitum.Infinitum;

import java.io.File;
import java.util.ArrayList;

public class LocationConfig {
    private record LocEntry( Location location, StationType type  ) { }

    private final String path = "plugins/Infinitum/Crafting/Locations.yml";

    private ArrayList<LocEntry> locEntries;

    public LocationConfig()
    {
        locEntries = new ArrayList<>();
        loadLocations();
    }

    public void deleteLoc ( Location formattedLoc )
    {
        locEntries.removeIf ( locEntry -> locEntry.location().equals( formattedLoc ) );
        saveLocations();
    }

    public void addLocation( Location loc, StationType type )
    {
        if ( !locEntries.contains( new LocEntry( loc, type  ) ) )
        {
            locEntries.add( new LocEntry( loc, type  ) );
            saveLocations();
        }
    }

    public ArrayList<Location> getLocations()
    {
        ArrayList<Location> list = new ArrayList<>();
        for ( LocEntry loc : locEntries ) {
            list.add( loc.location() );
        }
        return list;
    }

    public void loadLocations()
    {
        locEntries = new ArrayList<>();

        FileConfiguration config = YamlConfiguration.loadConfiguration( new File( path ) );

        for (int i = 0; true; i++)
        {
            if ( !config.contains( "stations." + i ) ) break;

            String str = config.getString("stations." +i+ ".location" );
            Location loc = stringToLoc( str );
            StationType type = StationType.valueOf( config.getString( "stations." +i+ ".type" ) );

            locEntries.add( new LocEntry( loc, type  ) );
        }
    }

    public void saveLocations()
    {

        FileConfiguration config = new YamlConfiguration();

        for ( int i = 0; locEntries.size() > i; i++)
        {
            config.set("stations."+i+".location", locToString( locEntries.get( i ).location() ) );
            config.set("stations."+i+".type", locEntries.get( i ).type().toString() );
        } try
        {
            config.save( new File( path ) );
        } catch ( Exception ignored ) { }
    }

    public boolean compareClick( Location loc )
    {
        return locEntries.stream().anyMatch( locEntry -> locEntry.location().equals( loc ) );
    }

    public String locToString( Location loc )
    {
        int x = loc.getBlockX();
        int y = loc.getBlockY();
        int z = loc.getBlockZ();
        return x+"/"+y+"/"+z+"/"+loc.getWorld().getName();
    }

    public Location stringToLoc( String str )
    {
        String[] parts = str.split("/");
        return new Location( Bukkit.getServer().getWorld( parts[3] ), Integer.parseInt( parts[0] ), Integer.parseInt( parts[1] ), Integer.parseInt( parts[2] ) );
    }

    public static LocationConfig get() {
        return Infinitum.getInstance().getLocationConfig();
    }
}
