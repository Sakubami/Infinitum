package xyz.sakubami.infinitum.player.skills.combat;

import org.bukkit.entity.Entity;
import xyz.sakubami.infinitum.player.skills.ExperienceType;
import xyz.sakubami.infinitum.player.skills.Leveling;

import java.util.UUID;

public class CombatExperience {

    Leveling leveling = new Leveling();

    public void gainExperience( UUID uuid, Entity entity )
    {
        int amount = 0;
        try {
            amount = EntityExperienceYield.valueOf( entity.getName().toUpperCase() ).getYield();
        } catch ( Exception ignored ) { }

        leveling.gainExperience( uuid, ExperienceType.COMBAT, amount );
    }
}
