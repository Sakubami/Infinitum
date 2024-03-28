package xyz.sakubami.infinitum.player.skills;

import xyz.sakubami.infinitum.player.level.PlayerConfig;
import xyz.sakubami.infinitum.player.skills.crafting.Experience;

import java.util.UUID;

public class Leveling {

    PlayerConfig cache = PlayerConfig.get();

    public int calculateLevel( int experience )
    {
        int total = 0;

        for ( SkillLevel level : SkillLevel.values() ) {

            if ( total > experience )
                return level.getNormal() -1;

            total += level.getExperience();
        }

        if ( total >= 156597425 ) {
            return 70;
        }

        return 0;
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
    }
}
