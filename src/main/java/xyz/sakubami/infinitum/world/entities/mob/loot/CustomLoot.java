package xyz.sakubami.infinitum.world.entities.mob.loot;

import xyz.sakubami.infinitum.functionality.items.CustomItem;

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
