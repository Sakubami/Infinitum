package xyz.sakubami.infinitum.world.entities.player.skills;

import xyz.sakubami.infinitum.world.entities.player.PlayerConfig;

import java.util.UUID;

public class Leveling {

    PlayerConfig cache = PlayerConfig.get();

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
        int b = cache.getPlayerLevel( uuid );
        return a > b;
    }

    public void gainExperience( UUID uuid, ExperienceType type, int amount )
    {
        // list.iterate == action == xp
        // calculate bonuses and shit
        cache.addExperience( uuid, type, amount );
        cache.scheduleSave();
    }
}
