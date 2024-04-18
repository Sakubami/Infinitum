package xyz.sakubami.infinitum.rpg.world.entities.loot;

import xyz.sakubami.infinitum.rpg.world.functionality.items.components.CustomItemTemplate;

import java.util.HashMap;
import java.util.Map;

public enum CustomLootTable {

    ENDER_DRAGON {
        public Map<CustomItemTemplate, Integer > get() {
            Map<CustomItemTemplate, Integer > lootTable = new HashMap<>();
            lootTable.put( CustomItemTemplate.RUNE_OF_POWER, 50 );
            lootTable.put( CustomItemTemplate.HEART_OF_THE_END, 10 );
            return lootTable;
        }
    },
    WITHER {
        public Map<CustomItemTemplate, Integer > get() {
            Map<CustomItemTemplate, Integer > lootTable = new HashMap<>();
            lootTable.put( CustomItemTemplate.END_CRYSTAL, 100 );
            lootTable.put( CustomItemTemplate.NETHER_STAR, 75 );
            lootTable.put( CustomItemTemplate.INFINITA_SCIENTIA, 100 );
            return lootTable;
        }
    };

    CustomLootTable() {}

    public abstract Map<CustomItemTemplate, Integer > get();
}
