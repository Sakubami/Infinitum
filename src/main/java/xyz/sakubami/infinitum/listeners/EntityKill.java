package xyz.sakubami.infinitum.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import xyz.sakubami.infinitum.loot.CustomLootTable;
import xyz.sakubami.infinitum.loot.LootController;
import xyz.sakubami.infinitum.player.skills.combat.CombatExperience;

public class EntityKill implements Listener {

    LootController lootController = new LootController();

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

        if ( entity.getType().equals( EntityType.ENDER_DRAGON ) )
        {
            lootController.generateLoot( entity.getLocation(), CustomLootTable.ENDER_DRAGON );
        }

        if ( entity.getType().equals( EntityType.WITHER ) )
        {
            e.getDrops().set( 0 , null );
            lootController.generateLoot( entity.getLocation(), CustomLootTable.WITHER );
        }
    }
}
