package xyz.sakubami.infinitum.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.sakubami.infinitum.world.entities.player.PlayerConnector;

public class PlayerInteractServer implements Listener {

    @EventHandler
    public void onServerJoin( PlayerJoinEvent e )
    {
        PlayerConnector config = PlayerConnector.get();
        config.addNew( e.getPlayer().getUniqueId() );
    }
}
