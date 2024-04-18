package xyz.sakubami.infinitum.rpg.world.entities.loot;

import org.bukkit.Location;
import org.bukkit.World;
import xyz.sakubami.infinitum.Infinitum;
import xyz.sakubami.infinitum.rpg.utils.builder.item.ItemBuilder;
import xyz.sakubami.infinitum.rpg.world.functionality.items.components.CustomItemTemplate;

import java.util.Map;

public class LootController{

    public void generateItem( Location location, CustomItemTemplate item )
    {
        World world = location.getWorld();
        world.dropItem( location, new ItemBuilder( item ).setGlowing().build() );
    }

    public void generateLoot( Location location, CustomLootTable lootTable )
    {
        if ( lootTable == null )
            return;

        Map<CustomItemTemplate, Integer > contents = lootTable.get();

        for ( CustomItemTemplate item : contents.keySet() )
            if ( contents.get( item ) > Infinitum.getInstance().getRandomGenerator().nextInt( 100 ) )
                generateItem( location, item );
    }
}
