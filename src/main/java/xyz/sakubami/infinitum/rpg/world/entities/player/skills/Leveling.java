package xyz.sakubami.infinitum.rpg.world.entities.player.skills;

import xyz.sakubami.infinitum.Infinitum;
import xyz.sakubami.infinitum.rpg.world.entities.control.EntityConnector;

import java.util.UUID;

public class Leveling {

    EntityConnector cache = EntityConnector.get();

    public int calculateLevel( int experience )
    {
        int total = 0;
        int result = 1;

        for ( SkillLevel level : SkillLevel.values() ) {

            if ( total > experience )
                result = level.getNormal() -1;

            total += level.getExperience();
        }

        if ( result == 0 )
        {
            return 70;
        }

        /*
        if ( total >= 156597425 ) {
            return 70;
        }
         */

        return result;
    }

    public boolean checkLevelUp( UUID uuid, int experience )
    {
        int a = calculateLevel( experience );
        int b = cache.get( uuid ).getLevel();
        return a > b;
    }

    public void gainExperience( UUID uuid, ExperienceType type, int amount )
    {
        // list.iterate == action == xp
        // calculate bonuses and shit
        if ( checkLevelUp( uuid, amount ) )
            Infinitum.getInstance().getServer().broadcastMessage("u leveled up lol");
        int present = cache.get( uuid ).getExperience().get( type );
        cache.get( uuid ).getExperience().replace( type, amount + present );
    }
}
