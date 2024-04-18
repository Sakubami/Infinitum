package xyz.sakubami.infinitum.rpg.world.functionality.items.enchanting.enchants.group;

import xyz.sakubami.infinitum.Infinitum;
import xyz.sakubami.infinitum.rpg.world.functionality.Attribute;
import xyz.sakubami.infinitum.rpg.world.functionality.items.enchanting.CustomEnchantment;
import xyz.sakubami.infinitum.rpg.world.functionality.items.enchanting.EnchantmentCategory;

import java.util.UUID;

public class AttributeEnchantment extends CustomEnchantment {

    Attribute attribute;

    public AttributeEnchantment( Attribute attribute ) {
        super( EnchantmentCategory.ARMOR, EnchantmentCategory.WEAPON, EnchantmentCategory.SHIELD, EnchantmentCategory.TOOL );
        this.attribute = attribute;
    }

    @Override
    public int getAttributeBonus( int value, Attribute attribute )
    {
        return value * 15;
    }

    @Override
    public void run( UUID uuid )
    {
        Infinitum.getInstance().getServer().broadcastMessage( "FOUND ENCHANTMENT ATTRIBUTE LOLZ");
    }

    @Override
    public String getDisplayName()
    {
        String v = "NULL";
        switch ( attribute )
        {
            case STRENGTH -> v = "Stärke";
            case CRITICAL_DAMAGE -> v =  "Kritisch";
        }
        return v;
    }
}
