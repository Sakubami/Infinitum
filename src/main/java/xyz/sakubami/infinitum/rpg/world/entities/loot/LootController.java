package xyz.sakubami.infinitum.rpg.world.entities.loot;

import org.bukkit.Location;
import org.bukkit.World;
import xyz.sakubami.infinitum.Infinitum;
import xyz.sakubami.infinitum.rpg.utils.builder.item.ItemBuilder;
import xyz.sakubami.infinitum.rpg.world.functionality.items.components.CustomItem;

import java.util.Map;

public class LootController{

    public void generateItem( Location location, CustomItem item )
    {
        World world = location.getWorld();
        world.dropItemNaturally( location, new ItemBuilder( item ).setGlowing().build() );
    }

    public void generateLoot( Location location, CustomLootTable lootTable )
    {
        Map< CustomItem, Integer > contents = lootTable.get();

        for ( CustomItem item : contents.keySet() )
            if ( contents.get( item ) > Infinitum.getInstance().getRandomGenerator().nextInt( 100 ) )
                generateItem( location, item );
    }
}
