package xyz.sakubami.infinitum.world.functionality.items.enchanting;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import xyz.sakubami.infinitum.Infinitum;
import xyz.sakubami.infinitum.utils.builder.item.nbt.ItemNBTUtils;
import xyz.sakubami.infinitum.world.functionality.items.enchanting.enchants.group.DamageEnchantment;
import xyz.sakubami.infinitum.world.functionality.spells.Element;

public class EnchantmentManager {

    private static NamespacedKey key( String key ) { return new NamespacedKey( Infinitum.getInstance(), key ); }

    public static void registerAll() {
        register( "SHARPNESS", new DamageEnchantment( Element.ALL ) );
        register( "SMITE", new DamageEnchantment( Element.UNDEAD ) );
    }

    private static void register( String id, CustomEnchantment customEnchantment ) {
        CustomEnchantment.registerEnchantment( key( id ), customEnchantment );
    }

    private final ItemNBTUtils nbt = new ItemNBTUtils();

    public void runEnchants( ItemStack itemStack )
    {
        for ( CustomEnchantment enchantment : nbt.getEnchantments( itemStack ).keySet() )
            enchantment.run();
    }

    public int getAdditiveBonuses( ItemStack itemStack )
    {
        int value = 0;
        for ( CustomEnchantment enchantment : nbt.getEnchantments( itemStack ).keySet() )
            value += enchantment.getAdditiveBonus( 1 );
        return value;
    }
}
