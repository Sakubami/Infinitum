package xyz.sakubami.infinitum.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.sakubami.infinitum.player.level.PlayerConfig;

import java.net.http.WebSocket;

public class PlayerInteractServer implements Listener {

    @EventHandler
    public void onServerJoin( PlayerJoinEvent e )
    {
        PlayerConfig config = PlayerConfig.get();
        config.addNewPlayer( e.getPlayer().getUniqueId() );
    }
}
