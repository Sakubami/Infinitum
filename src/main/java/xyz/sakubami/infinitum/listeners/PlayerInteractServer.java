package xyz.sakubami.infinitum.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.sakubami.infinitum.world.entities.control.PlayerSaving;

public class PlayerInteractServer implements Listener {

    @EventHandler
    public void onServerJoin( PlayerJoinEvent e )
    {
        PlayerSaving config = PlayerSaving.get();
        config.addNew( e.getPlayer().getUniqueId() );
    }
}
