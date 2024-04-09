package xyz.sakubami.infinitum.world.functionality.items.enchanting;

import org.bukkit.inventory.ItemStack;
import xyz.sakubami.infinitum.world.functionality.spells.Element;

import java.util.HashMap;

public abstract class CustomEnchantment {

    private final EnchantmentCategory category;
    private static final HashMap<String, CustomEnchantment> byName = new HashMap<>();

    protected CustomEnchantment( EnchantmentCategory category )
    {
        this.category = category;
    }

    public abstract String getName();

    public int getMinLevel() {
        return 1;
    }

    public int getMaxLevel() {
        return 1;
    }

    public int getCost( int value ) {
        return 1 + value * 10;
    }

    public int getDamageBonus( int value, Element element) { return 0; }

    public boolean canEnchant( ItemStack itemStack ) { return this.category.canEnchant( itemStack ); }

    public static void registerEnchantment( String id, CustomEnchantment customEnchantment ) {
        if ( !byName.containsKey( customEnchantment.getName() ) )
        {
            byName.put( id, customEnchantment );
        } else {
            throw new IllegalArgumentException( "Cannot set already-set enchantment" );
        }
    }

}
