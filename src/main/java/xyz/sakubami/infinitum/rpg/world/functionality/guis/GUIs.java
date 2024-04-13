package xyz.sakubami.infinitum.rpg.world.functionality.guis;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import xyz.sakubami.infinitum.rpg.utils.builder.inventory.InventoryBuilder;
import xyz.sakubami.infinitum.rpg.utils.builder.item.ItemBuilder;

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
