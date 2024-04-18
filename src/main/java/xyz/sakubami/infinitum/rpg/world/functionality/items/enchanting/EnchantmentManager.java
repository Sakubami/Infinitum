package xyz.sakubami.infinitum.rpg.world.functionality.items.enchanting;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import xyz.sakubami.infinitum.Infinitum;
import xyz.sakubami.infinitum.rpg.utils.NBTUtils;

import java.util.UUID;

public class EnchantmentManager {

    private static NamespacedKey key( String key ) { return new NamespacedKey( Infinitum.getInstance(), key ); }

    private static void registerPrivate( String id, CustomEnchantment customEnchantment ) {
        CustomEnchantment.registerEnchantment( key( id ), customEnchantment );
    }

    public static CustomEnchantment register( String id, CustomEnchantment customEnchantment ) {
        CustomEnchantment.registerEnchantment( key( id ), customEnchantment );
        return customEnchantment;
    }

    private final NBTUtils nbt = new NBTUtils();

    public void runEnchants( ItemStack itemStack, UUID uuid )
    {
        for ( CustomEnchantment enchantment : nbt.getItemEnchantments( itemStack ).keySet() )
            enchantment.run( uuid );
    }

    public int getAdditiveBonuses( ItemStack itemStack )
    {
        int value = 0;
        for ( CustomEnchantment enchantment : nbt.getItemEnchantments( itemStack ).keySet() )
            value += enchantment.getAdditiveBonus( nbt.getItemEnchantmentValue( itemStack, enchantment ) );
        return value;
    }

    public int getMultiplicativeBonuses( ItemStack itemStack )
    {
        int value = 0;
        for ( CustomEnchantment enchantment : nbt.getItemEnchantments( itemStack ).keySet() )
            value += enchantment.getAdditiveBonus( nbt.getItemEnchantmentValue( itemStack, enchantment ) );
        return value;
    }
}
