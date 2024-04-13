package xyz.sakubami.infinitum.rpg.world.functionality.items.enchanting.enchants.group;

import xyz.sakubami.infinitum.rpg.world.functionality.items.enchanting.CustomEnchantment;
import xyz.sakubami.infinitum.rpg.world.functionality.items.enchanting.EnchantmentCategory;
import xyz.sakubami.infinitum.rpg.world.functionality.spells.Element;

public class DamageEnchantment extends CustomEnchantment {

    private final Element element;
    public DamageEnchantment( Element element ) {
        super( EnchantmentCategory.WEAPON );
        this.element = element;
    }

    @Override
    public int getMinLevel() {
        return 3;
    }

    @Override
    public int getAdditiveBonus( int level ) {
        int value = 0;
        switch ( element )
        {
            case ALL -> value = 100;
            case FIRE -> value = 200;
            case ICE -> value = 300;
            case EARTH -> value = 400;
            case UNDEAD -> value = 500;
            case ARACHNID -> value = 600;
        }
        return value;
    }

    @Override
    public void run()
    {

    }
}
