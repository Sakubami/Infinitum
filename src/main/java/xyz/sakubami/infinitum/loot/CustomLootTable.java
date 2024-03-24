package xyz.sakubami.infinitum.loot;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import xyz.sakubami.infinitum.items.ItemBuilder;

import java.util.ArrayList;

import static xyz.sakubami.infinitum.loot.CustomLoot.*;

public enum CustomLootTable
{
    ENDER_DRAGON( HEART_OF_THE_END, RUNE_OF_POWER ),
    WITHER( END_CRYSTAL, INFINITA_SCIENTIA, NETHER_STAR );

    private final ArrayList<ItemStack> contents = new ArrayList<>();

    CustomLootTable( CustomLoot... loot )
    {
        for ( CustomLoot item : loot )
        {
            this.contents.add( item.getLootItem() );
        }
    }

    public ArrayList<ItemStack> getLootTable() { return contents; }
}
