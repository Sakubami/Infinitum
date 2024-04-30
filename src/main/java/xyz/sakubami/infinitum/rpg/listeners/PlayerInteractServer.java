package xyz.sakubami.infinitum.rpg.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.sakubami.infinitum.rpgcore.utils.control.EntityConnector;
import xyz.sakubami.infinitum.rpgcore.utils.control.Saving;

import java.util.UUID;

public class PlayerInteractServer implements Listener {

    Saving saving = new Saving();

    @EventHandler
    public void onServerJoin( PlayerJoinEvent e )
    {
        UUID uuid = e.getPlayer().getUniqueId();
        if ( saving.loadFromConfig( uuid ) )
            saving.addNew( uuid );
    }

    @EventHandler
    public void onPlayerLeave( PlayerQuitEvent e )
    {
        EntityConnector connector = EntityConnector.get();

        saving.saveToConfig( e.getPlayer().getUniqueId() );
        connector.remove( connector.get( e.getPlayer().getUniqueId() ) );
    }
}
