package xyz.sakubami.infinitum.world.functionality.items.enchanting.enchants;

import xyz.sakubami.infinitum.world.functionality.items.enchanting.CustomEnchantment;
import xyz.sakubami.infinitum.world.functionality.items.enchanting.EnchantmentCategory;

public class DamageEnchantment extends CustomEnchantment {

    public DamageEnchantment() {
        super( EnchantmentCategory.WEAPON );
    }

    @Override
    public String getName() {
        return "null";
    }

    @Override
    public int getMinLevel() {
        return 2;
    }
}
