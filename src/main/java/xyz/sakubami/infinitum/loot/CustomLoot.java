package xyz.sakubami.infinitum.loot;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.sakubami.infinitum.items.CustomItem;
import xyz.sakubami.infinitum.items.ItemBuilder;

import java.util.Collections;
import java.util.List;

public enum CustomLoot {

    HEART_OF_THE_END( CustomItem.HEART_OF_THE_END ,10),
    RUNE_OF_POWER( CustomItem.RUNE_OF_POWER,50 ),
    END_CRYSTAL( CustomItem.END_CRYSTAL,100 ),
    NETHER_STAR( CustomItem.NETHER_STAR, 75),
    INFINITA_SCIENTIA( CustomItem.INFINITA_SCIENTIA, 100 );

    private final int chance;
    private final Material material;
    private final String name;
    private final List<String> loreList;
    private final int id;

    CustomLoot( CustomItem item, int chance )
    {
        this.chance = chance;
        this.material = item.getMaterial();
        this.name = item.getName();
        this.loreList = item.getLoreList();
        this.id = item.getId();
    }

    public ItemStack getLootItem()
    {
        return new ItemBuilder( material )
                .displayname( name + "/" + chance )
                .setLore( loreList != null ? loreList : Collections.emptyList() )
                .addToLore( "§0" + id )
                .build();
    }
}
