package xyz.sakubami.infinitum.loot;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import xyz.sakubami.infinitum.items.CustomItem;
import xyz.sakubami.infinitum.builder.item.ItemBuilder;

import java.util.Collections;
import java.util.List;

public enum CustomLoot {

    HEART_OF_THE_END( CustomItem.HEART_OF_THE_END ,10),
    RUNE_OF_POWER( CustomItem.RUNE_OF_POWER,50 ),
    END_CRYSTAL( CustomItem.END_CRYSTAL,100 ),
    NETHER_STAR( CustomItem.NETHER_STAR, 75),
    INFINITA_SCIENTIA( CustomItem.INFINITA_SCIENTIA, 100 );

    private final int chance;
    private final CustomItem item;

    CustomLoot( CustomItem item, int chance )
    {
        this.chance = chance;
        this.item = item;
    }

    public CustomItem getItem() {
        return item;
    }

    public int getChance() { return chance; }
}
