package xyz.sakubami.infinitum.rpg.world.functionality.items.enchanting;

import xyz.sakubami.infinitum.rpg.world.functionality.Attribute;
import xyz.sakubami.infinitum.rpg.world.functionality.items.enchanting.enchants.group.AttributeEnchantment;
import xyz.sakubami.infinitum.rpg.world.functionality.items.enchanting.enchants.group.DamageEnchantment;
import xyz.sakubami.infinitum.rpg.world.functionality.spells.Element;

public class EnchantmentLibrary {
    private static final String e = "ENCHANTMENT_";
    public static final CustomEnchantment DAMAGE_ALL;
    public static final CustomEnchantment DAMAGE_UNDEAD;
    public static final CustomEnchantment DAMAGE_ARACHNID;
    public static final CustomEnchantment ATTRIBUTE_STRENGTH;
    public static final CustomEnchantment ATTRIBUTE_CRITICAL_DAMAGE;

    static {
        DAMAGE_ALL = EnchantmentManager.register( e + "DAMAGE_ALL", new DamageEnchantment( Element.ALL ) );
        DAMAGE_UNDEAD = EnchantmentManager.register( e + "DAMAGE_UNDEAD", new DamageEnchantment( Element.UNDEAD ) );
        DAMAGE_ARACHNID = EnchantmentManager.register( e + "DAMAGE_ARACHNID", new DamageEnchantment( Element.ARACHNID ) );
        ATTRIBUTE_STRENGTH = EnchantmentManager.register( e + "ATTRIBUTE_STRENGTH", new AttributeEnchantment( Attribute.STRENGTH ) );
        ATTRIBUTE_CRITICAL_DAMAGE = EnchantmentManager.register( e + "ATTRIBUTE_CRITICAL_DAMAGE", new AttributeEnchantment( Attribute.CRITICAL_DAMAGE ) );
    }
}
