package xyz.sakubami.infinitum.rpg.utils.builder.mob;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import xyz.sakubami.infinitum.Infinitum;

import java.util.ArrayList;
import java.util.Random;

public class CustomEntityBuilderUtils {

    private ArrayList< String > getColorList()
    {
        ArrayList< String > colorList = new ArrayList<>();
        colorList.add( "§f");
        colorList.add( "§f");
        colorList.add( "§e");
        colorList.add( "§6");
        colorList.add( "§c");
        colorList.add( "§c");
        colorList.add( "§f");
        colorList.add( "§f");
        colorList.add( "§e");
        colorList.add( "§6");
        colorList.add( "§c");
        colorList.add( "§c");
        return colorList;
    }

    public ArmorStand createPlaceholderAt( World world, Location location )
    {
        return new CustomEntityBuilder( EntityType.ARMOR_STAND, world, location )
                .getPlaceHolder();
    }

    public void createDamageTag( boolean critical, int v, World world, Location location )
    {
        double newX;
        double newY;
        double newZ;
        Location loc;

        for ( int i = 0; true; i++ )
        {
            Random random = new Random();
            newX = ( location.getX() - 0.5 ) + ( ( double ) random.nextInt( 10 ) / 10 );
            newY = ( location.getY() -1.5 ) + ( 0.35 + ( ( double ) random.nextInt( 8 ) / 10 ) );
            newZ = ( location.getZ() - 0.5 ) + ( ( double ) random.nextInt( 10 ) / 10 );

            if ( world.getBlockAt( location ).getType().equals( Material.AIR ) )
            {
                loc = new Location( world, newX, newY, newZ );
                break;
            }
        }

        String uncolored = "✵" + v + "✵";
        StringBuilder criticalString = new StringBuilder();
        String damageString = "§7" + v;
        if ( critical )
        {
            int i = 0;

            for ( char character : uncolored.toCharArray() )
            {
                criticalString.append( getColorList().get( i ) ).append( character );
                i++;
            }
            damageString = criticalString.toString();
        }

        ArmorStand armorStand = new CustomEntityBuilder( EntityType.ARMOR_STAND, world, loc )
                .placeHolderName( damageString )
                .getPlaceHolder();

        Bukkit.getScheduler().runTaskLater( Infinitum.getInstance(), armorStand::remove, 20 );
    }
}
