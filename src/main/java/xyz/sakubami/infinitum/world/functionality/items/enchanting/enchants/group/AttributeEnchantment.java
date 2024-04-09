package xyz.sakubami.infinitum.world.functionality.items.enchanting.enchants.group;

import xyz.sakubami.infinitum.world.functionality.items.enchanting.CustomEnchantment;
import xyz.sakubami.infinitum.world.functionality.items.enchanting.EnchantmentCategory;

public class AttributeEnchantment extends CustomEnchantment {

    public AttributeEnchantment() {
        super( EnchantmentCategory.ARMOR, EnchantmentCategory.WEAPON, EnchantmentCategory.SHIELD, EnchantmentCategory.TOOL );
    }

    @Override
    public int getAttributeBonus( int value )
    {
        return value * 15;
    }
}
