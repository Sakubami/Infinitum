package xyz.sakubami.infinitum.world.entities.loot;

import xyz.sakubami.infinitum.utils.builder.item.ItemTemplates;

public enum CustomLoot {

    HEART_OF_THE_END( ItemTemplates.HEART_OF_THE_END ,10),
    RUNE_OF_POWER( ItemTemplates.RUNE_OF_POWER,50 ),
    END_CRYSTAL( ItemTemplates.END_CRYSTAL,100 ),
    NETHER_STAR( ItemTemplates.NETHER_STAR, 75),
    INFINITA_SCIENTIA( ItemTemplates.INFINITA_SCIENTIA, 100 );

    private final int chance;
    private final ItemTemplates item;

    CustomLoot(ItemTemplates item, int chance )
    {
        this.chance = chance;
        this.item = item;
    }

    public ItemTemplates getItem() {
        return item;
    }

    public int getChance() { return chance; }
}
