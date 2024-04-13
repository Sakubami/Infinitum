package xyz.sakubami.infinitum.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.sakubami.infinitum.world.entities.control.EntityConnector;
import xyz.sakubami.infinitum.world.entities.control.Saving;

import java.util.UUID;

public class PlayerInteractServer implements Listener {

    @EventHandler
    public void onServerJoin( PlayerJoinEvent e )
    {
        Saving saving = new Saving();

        UUID uuid = e.getPlayer().getUniqueId();
        if ( saving.loadFromConfig( uuid ) )
            saving.addNew( uuid );
    }

    @EventHandler
    public void onPlayerLeave( PlayerQuitEvent e )
    {
        Saving saving = new Saving();
        EntityConnector connector = EntityConnector.get();

        saving.saveToConfig( e.getPlayer().getUniqueId() );
        connector.remove( connector.get( e.getPlayer().getUniqueId() ) );
    }
}
