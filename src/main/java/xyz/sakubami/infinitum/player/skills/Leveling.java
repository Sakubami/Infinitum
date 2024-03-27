package xyz.sakubami.infinitum.player.skills;

import xyz.sakubami.infinitum.player.level.PlayerConfig;
import xyz.sakubami.infinitum.player.skills.crafting.Experience;

import java.util.UUID;

public class Leveling {

    PlayerConfig cache = PlayerConfig.get();

    public int calculateLevel( int experience )
    {
        int total = 0;
        int output = 0;

        for ( SkillLevel level : SkillLevel.values() ) {
            if ( total > experience )
                return level.getNormal() -1 ;
            total += level.getExperience();
        }

        return output;
    }

    public void gainExperience( UUID uuid, ExperienceType type, int amount )
    {
        // list.iterate == action == xp
        // calculate bonuses and shit
        cache.addExperience( uuid, type, amount );
    }
}
