package xyz.sakubami.infinitum.rpg.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import xyz.sakubami.infinitum.rpg.utils.NBTUtils;
import xyz.sakubami.infinitum.rpg.world.entities.player.skills.combat.CombatExperience;

public class EntityKill implements Listener {

    NBTUtils nbt = new NBTUtils();

    @EventHandler
    public void onEntityDeath( EntityDeathEvent e )
    {
        Entity entity = e.getEntity();

        if ( e.getEntity().getKiller() != null )
        {
            Player player = e.getEntity().getKiller();
            CombatExperience combatExperience = new CombatExperience();
            combatExperience.gainExperience( player.getUniqueId(), entity );
        }

        if ( nbt.checkForEntityNBTData( e.getEntity() ) )
        {
            e.getDrops().clear();
        }
    }
}
