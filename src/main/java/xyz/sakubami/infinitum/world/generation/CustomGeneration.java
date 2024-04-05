package xyz.sakubami.infinitum.world.generation;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import xyz.sakubami.infinitum.Infinitum;
import xyz.sakubami.infinitum.utils.worldedit.InfinitumSchematic;
import xyz.sakubami.infinitum.utils.worldedit.WorldEditHelper;

import java.util.*;


public class CustomGeneration {

    private static final HashMap<String , Chunk > chunks = new HashMap<>();
    private static double radius = -3125;
    private static int taskID;
    private static int counter;
    private static int taskID2;
    private static final List<String> locations = new ArrayList<>();

    public static void generateRandomChunks( Player player, Material suitable, int bounds )
    {
        World world = player.getWorld();
        Random random = new Random();

        if ( bounds == 0 )
            bounds = 125;

        int finalBounds = bounds;

        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask( Infinitum.getInstance(), () ->
                {
                    int x = ( int ) radius + random.nextInt( finalBounds );
                    int convert = -3125;
                    int z = convert + random.nextInt( 6250 );

                    int newX = x * 16;
                    int newZ = z * 16;
                    boolean invalid = true;

                    Chunk chunk = world.getChunkAt( x, z);

                    for ( int i2 = 63; i2 < 78; i2 ++ )
                    {
                        Block block = chunk.getBlock( 8, i2, 8 );
                        if ( block.getType().equals( suitable ) )
                        {
                            chunks.put(newX + "/" + i2 + "/" + newZ, chunk);
                            locations.add(newX + "/" + i2 + "/" + newZ);
                            player.sendMessage("valid Chunk at -> " + newX + "." + i2 + "." + newZ) ;
                            radius += 48.828125;
                            invalid = false;
                            break;
                        }
                    }

                    if ( invalid )
                        Infinitum.getInstance().getServer().broadcastMessage( "skipping... " );

                    if ( radius >= 3125 )
                        cancelTask( taskID );
                },0, 10);
    }

    private static void cancelTask( int taskID )
    {
        Bukkit.getScheduler().cancelTask( taskID );
    }

    public static void abort()
    {
        Bukkit.getScheduler().cancelTask( taskID );
        Bukkit.getScheduler().cancelTask( taskID2 );
    }

    public static void generateTowers()
    {
        taskID2 = Bukkit.getScheduler().scheduleSyncRepeatingTask( Infinitum.getInstance(), () ->
        {
            String key = locations.get( 0 );
            Chunk chunk = chunks.get( key );
            String[] split = locations.get( 0 ).split( "/" );

            int x = Integer.parseInt( split[0] );
            int y = Integer.parseInt( split[1] );
            int z = Integer.parseInt( split[2] );

            WorldEditHelper.pasteSchematic( new Location( chunk.getWorld(), x +8, y +1, z -8 ), InfinitumSchematic.TOWER.getPath() );
            locations.remove( 0 );
            Infinitum.getInstance().getServer().broadcastMessage( "placing tower -> " + counter );
            counter++;

            if ( locations.isEmpty() )
                cancelTask( taskID2 );
        },0,2);
    }

    public void generateOres()
    {

    }
}
