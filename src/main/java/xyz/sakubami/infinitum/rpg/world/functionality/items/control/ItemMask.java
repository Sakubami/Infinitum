package xyz.sakubami.infinitum.rpg.world.functionality.items.control;

import org.bukkit.inventory.ItemStack;
import xyz.sakubami.infinitum.rpg.world.functionality.Attribute;
import xyz.sakubami.infinitum.rpg.world.functionality.items.components.ItemCategory;
import xyz.sakubami.infinitum.rpg.world.functionality.items.components.ItemClass;
import xyz.sakubami.infinitum.rpg.world.functionality.items.components.ItemTier;
import xyz.sakubami.infinitum.rpg.world.functionality.items.enchanting.CustomEnchantment;
import xyz.sakubami.infinitum.rpg.world.functionality.items.reforging.Reforge;

import java.util.Map;
import java.util.UUID;

public class ItemMask {

    // declare place where it is later

    private final String id;
    private final ItemStack itemStack;
    private final UUID uuid;
    private String displayName;
    private Reforge reforge;
    private ItemTier tier;
    private final Map< CustomEnchantment, Integer > enchantments;
    private final Map< Attribute, Integer > attributes;
    private final String description;
    private final ItemClass itemClass;
    private final ItemCategory itemCategory;

    public ItemMask( ItemStack itemStack, UUID uuid, String id, ItemTier tier, Reforge reforge, Map< Attribute, Integer > attributes, Map< CustomEnchantment, Integer > enchantments, String description, ItemClass itemClass, ItemCategory itemCategory )
    {
        this.id = id;
        this.itemClass = itemClass;
        this.itemCategory = itemCategory;
        this.description = description;
        this.itemStack = itemStack;
        this.uuid = uuid;
        this.displayName = itemStack.getItemMeta().getDisplayName();
        this.reforge = reforge;
        this.tier = tier;
        this.enchantments = enchantments;
        this.attributes = attributes;
    }

    public String getId() { return id; }
    public ItemStack getItem() { return itemStack; }
    public UUID getUuid() { return uuid; }
    public String getDisplayName() { return displayName; }
    public Reforge getReforge() { return reforge; }
    public ItemTier getTier() { return tier; }
    public Map< CustomEnchantment, Integer > getEnchantments() { return enchantments;}
    public Map< Attribute, Integer > getAttributes() { return attributes; }
    public String getDescription() { return description; }
    public ItemClass getItemClass() { return itemClass; }
    public ItemCategory getItemCategory() { return itemCategory; }

    public void setReforge( Reforge reforge ) { this.reforge = reforge; }
    public void setDisplayName( String displayName ) { this.displayName = displayName; }
    public void setTier( ItemTier tier ) { this.tier = tier; }
}
