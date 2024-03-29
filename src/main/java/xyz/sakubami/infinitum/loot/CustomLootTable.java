package xyz.sakubami.infinitum.loot;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

import static xyz.sakubami.infinitum.loot.CustomLoot.*;

public enum CustomLootTable
{
    ENDER_DRAGON( HEART_OF_THE_END, RUNE_OF_POWER ),
    WITHER( END_CRYSTAL, INFINITA_SCIENTIA, NETHER_STAR );

    private final ArrayList<CustomLoot> contents = new ArrayList<>();

    CustomLootTable( CustomLoot... loot )
    {
        this.contents.addAll( Arrays.asList( loot ) );
    }

    public ArrayList<CustomLoot> getLootTable() { return contents; }
}
