package xyz.sakubami.infinitum.rpg.world.functionality.items.enchanting.enchants.group;

import xyz.sakubami.infinitum.rpg.world.functionality.Attribute;
import xyz.sakubami.infinitum.rpg.world.functionality.items.enchanting.CustomEnchantment;
import xyz.sakubami.infinitum.rpg.world.functionality.items.enchanting.EnchantmentCategory;
import xyz.sakubami.infinitum.rpg.world.functionality.spells.Element;

public class ProtectionEnchantment extends CustomEnchantment {

    private final Element element;

    public ProtectionEnchantment( Element element ) {
        super( EnchantmentCategory.WEAPON );
        this.element = element;
    }

    @Override
    public int getAttributeBonus( int level, Attribute attribute ) {
        int value = 0;
        switch ( element )
        {
            case ALL -> value = level * 15;
            case UNDEAD, ARACHNID -> value = level * 20;
        }
        return value;
    }

    @Override
    public String getDisplayName()
    {
        String v = "NULL";
        switch ( element )
        {
            case ALL -> v = "Schutz";
            case UNDEAD -> v = "Schutz$der$Untoten";
            case ARACHNID -> v = "Schutz$der$Spinnen";
        }
        return v;
    }
}
