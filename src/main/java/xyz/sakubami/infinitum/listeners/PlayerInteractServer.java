package xyz.sakubami.infinitum.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.sakubami.infinitum.world.entities.player.PlayerController;

public class PlayerInteractServer implements Listener {

    @EventHandler
    public void onServerJoin( PlayerJoinEvent e )
    {
        PlayerController config = PlayerController.get();
        config.addNewPlayer( e.getPlayer().getUniqueId() );
    }
}
