package xyz.sakubami.infinitum.world.entities.player.skills.combat;

import org.bukkit.entity.Entity;
import xyz.sakubami.infinitum.world.entities.player.skills.ExperienceType;
import xyz.sakubami.infinitum.world.entities.player.skills.Leveling;

import java.util.UUID;

public class CombatExperience {

    Leveling leveling = new Leveling();

    public void gainExperience( UUID uuid, Entity entity )
    {
        int amount = 0;
        try {
            amount = EntityExperienceYield.valueOf( entity.getName().toUpperCase() ).getYield();
            leveling.gainExperience( uuid, ExperienceType.COMBAT, amount );
        } catch ( Exception ignored ) { }
    }
}
