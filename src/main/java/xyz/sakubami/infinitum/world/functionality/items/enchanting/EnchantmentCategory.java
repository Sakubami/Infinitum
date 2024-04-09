package xyz.sakubami.infinitum.world.functionality.items.enchanting;

import org.bukkit.inventory.ItemStack;
import xyz.sakubami.infinitum.utils.builder.item.nbt.ItemNBTUtils;
import xyz.sakubami.infinitum.world.functionality.items.components.ItemCategory;
import xyz.sakubami.infinitum.world.functionality.items.components.ItemType;

public enum EnchantmentCategory {
    ARMOR {
        public boolean canEnchant( ItemStack itemStack ) { return nbt.getCategory( itemStack ).equals( ItemCategory.ARMOR ) ; }
    },
    ARMOR_HEAD {
        public boolean canEnchant( ItemStack itemStack ) { return nbt.getType( itemStack ).equals( ItemType.HELMET ) ; }
    },
    ARMOR_CHEST {
        public boolean canEnchant( ItemStack itemStack ) { return nbt.getType( itemStack ).equals( ItemType.CHESTPLATE ) ; }
    },
    ARMOR_LEGGINGS {
        public boolean canEnchant( ItemStack itemStack ) { return nbt.getType( itemStack ).equals( ItemType.LEGGINGS ) ; }
    },
    ARMOR_FEET {
        public boolean canEnchant( ItemStack itemStack ) { return nbt.getType( itemStack ).equals( ItemType.BOOTS ) ; }
    },
    TOOL {
        public boolean canEnchant( ItemStack itemStack ) { return nbt.getCategory( itemStack ).equals( ItemCategory.TOOL ) ; }
    },
    WEAPON {
        public boolean canEnchant( ItemStack itemStack ) { return nbt.getCategory( itemStack ).equals( ItemCategory.WEAPON ) ; }
    },
    SHIELD {
        public boolean canEnchant( ItemStack itemStack ) { return nbt.getType( itemStack ).equals( ItemType.SHIELD ) ; }
    };

final ItemNBTUtils nbt = new ItemNBTUtils();

EnchantmentCategory() {}

public abstract boolean canEnchant( ItemStack itemStack );
}
