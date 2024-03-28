package xyz.sakubami.infinitum.loot;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import xyz.sakubami.infinitum.builder.item.ItemBuilder;

import java.util.ArrayList;
import java.util.Random;

public class LootController{

    public void generateItem( Location location, ItemStack loot )
    {
        World world = location.getWorld();
        int random = new Random().nextInt( 100 );

        String[] oldName = loot.getItemMeta().getDisplayName().split("/" );
        int chance = Integer.parseInt( oldName[1] );
        ItemStack newLoot = new ItemBuilder( loot.getType() ).displayname( oldName[0] ).setLore( loot.getItemMeta().getLore() ).setGlowing().build();

        if ( random < chance )
        {
            world.dropItemNaturally( location, newLoot );
        }
    }

    public void generateLoot( Location location, CustomLootTable lootTable )
        {
            ArrayList<ItemStack> contents = lootTable.getLootTable();

            for ( ItemStack item : contents )
            {
                generateItem( location, item );
            }
        }
}
