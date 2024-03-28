package xyz.sakubami.infinitum.player.skills;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import xyz.sakubami.infinitum.builder.InventoryBuilder;
import xyz.sakubami.infinitum.builder.item.ItemBuilder;

public class SkillsGUI {

    public Inventory getInventory()
    {
        return new InventoryBuilder( 6 )
                .fillEmpty( new ItemBuilder( Material.LIGHT_GRAY_STAINED_GLASS_PANE ).addNBTTag( "protected", "true" ).build() )
                .buildBottomRow()
                .build();
    }
}
