package xyz.sakubami.infinitum.world.functionality.items.enchanting;

import xyz.sakubami.infinitum.world.functionality.items.enchanting.enchants.DamageEnchantment;

public class Enchantments
{
    public static final CustomEnchantment SHARPNESS;

    static {
        SHARPNESS = register( "SHARPNESS", new DamageEnchantment() );
    }

    public Enchantments() {}
    private static CustomEnchantment register( String id, CustomEnchantment customEnchantment ) {
        CustomEnchantment.registerEnchantment( id, customEnchantment );
        return customEnchantment;
    }
}
