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
    public int getAdditiveBonus( int level ) {
        int value = 0;
        switch ( element )
        {
            case ALL -> value = level * 10;
            case UNDEAD, ARACHNID -> value = level * 15;
        }
        return value;
    }

    @Override
    public String getDisplayName()
    {
        String v = "NULL";
        switch ( element )
        {
            case ALL -> v = "Schärfe";
            case UNDEAD -> v = "Bann";
            case ARACHNID -> v = "Nemesis$der$Spinnen";
        }
        return v;
    }
}
