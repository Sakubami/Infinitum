package xyz.sakubami.infinitum.world.functionality.items.enchanting;

import xyz.sakubami.infinitum.world.functionality.items.enchanting.enchants.group.DamageEnchantment;
import xyz.sakubami.infinitum.world.functionality.spells.Element;

public class EnchantmentLibrary {
     public static final CustomEnchantment DAMAGE_ALL;

     static {
         DAMAGE_ALL = EnchantmentManager.register( "abc", new DamageEnchantment( Element.ALL ) );
     }
}
