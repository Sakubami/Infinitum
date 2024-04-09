package xyz.sakubami.infinitum.world.functionality.items.enchanting;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import xyz.sakubami.infinitum.world.functionality.Attribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class CustomEnchantment {

    private final ArrayList<EnchantmentCategory> categories = new ArrayList<>();

    private static final HashMap<NamespacedKey, CustomEnchantment> enchants = new HashMap<>();

    protected CustomEnchantment( EnchantmentCategory category )
    {
        this.categories.add( category );
    }

    protected CustomEnchantment( EnchantmentCategory... categories )
    {
        this.categories.addAll( List.of( categories ) );
    }

    public static CustomEnchantment getByID( NamespacedKey id ) { return enchants.get( id ); }

    public static NamespacedKey getID( CustomEnchantment enchantment )
    {
        for ( NamespacedKey key : enchants.keySet() )
        {
            return enchants.get( key ).equals( enchantment ) ? key : null ;
        }
        return null;
    }

    public static boolean contains( NamespacedKey id )
    {
        return enchants.get( id ) != null ;
    }

    public void run() {}

    public int getMinLevel() {
        return 1;
    }

    public int getMaxLevel() {
        return 1;
    }

    public int getCost( int level ) { return 1 + level * 10; }

    public int getAdditiveBonus( int level ) { return 0; }

    public int getAttributeBonus( int level, Attribute attribute ) { return 0; }

    public int getMultiplicativeBonus( int level ) { return 0; }

    public boolean canEnchant( ItemStack itemStack ) { return categories.stream().anyMatch( category -> category.canEnchant( itemStack ) ); }

    public static void registerEnchantment( NamespacedKey id, CustomEnchantment customEnchantment ) {
        if ( !enchants.containsKey( id ) )
        {
            enchants.put( id, customEnchantment );
        } else {
            throw new IllegalArgumentException( "Cannot set already-set enchantment" );
        }
    }
}
