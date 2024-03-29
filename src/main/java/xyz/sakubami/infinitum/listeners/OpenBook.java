package xyz.sakubami.infinitum.listeners;

import com.google.gson.Gson;
import net.leonardo_dgs.interactivebooks.CommandIBooks;
import net.leonardo_dgs.interactivebooks.IBook;
import net.leonardo_dgs.interactivebooks.InteractiveBooks;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import xyz.sakubami.infinitum.utils.InteractHelper;

public class OpenBook implements Listener {

    @EventHandler
    public void onOpenBook( PlayerInteractEvent e )
    {
        InteractHelper helper = new InteractHelper();
        Player player = e.getPlayer();

        if ( player.getItemInHand().hasItemMeta() )
        {
            if ( helper.rightClick( e ) )
            {
                if ( player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase( "§bInfinita Scientia" ) )
                {
                    IBook iBook = InteractiveBooks.getBook( "start" );
                    iBook.open( player );
                }
            }
        }
    }
}
