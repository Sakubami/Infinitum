package xyz.sakubami.infinitum.loot;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import xyz.sakubami.infinitum.builder.item.ItemBuilder;
import xyz.sakubami.infinitum.items.CustomItem;

import java.util.ArrayList;
import java.util.Random;

public class LootController{

    public void generateItem( Location location, CustomLoot loot )
    {
        World world = location.getWorld();
        int random = new Random().nextInt( 100 );

        int chance = loot.getChance();
        ItemStack newLoot = new ItemBuilder( loot.getItem() ).setGlowing().build();

        if ( random < chance )
        {
            world.dropItemNaturally( location, newLoot );
        }
    }

    public void generateLoot( Location location, CustomLootTable lootTable )
    {
        ArrayList<CustomLoot> contents = lootTable.getLootTable();

        for ( CustomLoot item : contents )
        {
            generateItem( location, item );
        }
    }
}
