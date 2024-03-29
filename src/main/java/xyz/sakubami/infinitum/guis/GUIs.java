package xyz.sakubami.infinitum.guis;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import xyz.sakubami.infinitum.builder.InventoryBuilder;
import xyz.sakubami.infinitum.builder.item.ItemBuilder;

public class GUIs {

    public Inventory getInventory()
    {
        return new InventoryBuilder( 6 )
                .setItem( new ItemBuilder( Material.WRITABLE_BOOK, "§0ABC" ).addNBTTag( "link", "second" ).build(), 22 )
                .fillEmpty( new ItemBuilder( Material.LIGHT_GRAY_STAINED_GLASS_PANE ).addNBTTag( "protected", "true" ).build() )
                .buildBottomRow()
                .build();
    }
}
