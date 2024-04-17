package xyz.sakubami.infinitum.rpg.world.entities.loot;

import xyz.sakubami.infinitum.rpg.world.functionality.items.components.CustomItem;

import java.util.HashMap;
import java.util.Map;

public enum CustomLootTable {

    ENDER_DRAGON {
        public Map< CustomItem, Integer > get() {
            Map< CustomItem, Integer > lootTable = new HashMap<>();
            lootTable.put( CustomItem.RUNE_OF_POWER, 50 );
            lootTable.put( CustomItem.HEART_OF_THE_END, 10 );
            return lootTable;
        }
    },
    WITHER {
        public Map< CustomItem, Integer > get() {
            Map< CustomItem, Integer > lootTable = new HashMap<>();
            lootTable.put( CustomItem.END_CRYSTAL, 100 );
            lootTable.put( CustomItem.NETHER_STAR, 75 );
            lootTable.put( CustomItem.INFINITA_SCIENTIA, 100 );
            return lootTable;
        }
    };

    CustomLootTable() {}

    public abstract Map< CustomItem, Integer > get();
}
