package xyz.sakubami.infinitum.rpg.world.functionality.items.enchanting;

import xyz.sakubami.infinitum.rpg.world.functionality.Attribute;
import xyz.sakubami.infinitum.rpg.world.functionality.items.enchanting.enchants.group.AttributeEnchantment;
import xyz.sakubami.infinitum.rpg.world.functionality.items.enchanting.enchants.group.DamageEnchantment;
import xyz.sakubami.infinitum.rpg.world.functionality.spells.Element;

public class EnchantmentLibrary {
    private static final String e = "ENCHANTMENT_";
    public static final CustomEnchantment DAMAGE_ALL;
    public static final CustomEnchantment ATTRIBUTE_STRENGTH;

    static {
        DAMAGE_ALL = EnchantmentManager.register( e + "DAMAGE_ALL", new DamageEnchantment( Element.ALL ) );
        ATTRIBUTE_STRENGTH = EnchantmentManager.register( e + "ATTRIBUTE_STRENGTH", new AttributeEnchantment( Attribute.STRENGTH ) );
    }
}
