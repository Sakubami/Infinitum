package xyz.sakubami.infinitum.items;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public enum CustomItem
{
    NULL(
            Material.POPPED_CHORUS_FRUIT,
            ItemCategory.MATERIAL,
            ItemAttributes.NONE,
            Tier.O,
            "&5Verfluchte Frucht",
            "&o&7Erschaffen aus einem Fehlgeschlagenen Ritual" ),

    INFINITA_SCIENTIA(
            Material.WRITABLE_BOOK,
            ItemCategory.TOME,
            ItemAttributes.INFINITA_SCIENTIA,
            Tier.II,
            "&bInfinita Scientia",
            "&o&7Eine starke Ausstrahlung geht &o&7Dunkler Energie..." ),

    HEART_OF_THE_END(
            Material.PITCHER_PLANT,
            ItemCategory.MATERIAL,
            ItemAttributes.NONE,
            Tier.O,
            "&6Herz des Endes",
            "&o&7Es pulsiert mit &o&7Dunkler Energie..." ),

    RUNE_OF_POWER(
            Material.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE,
            ItemCategory.MATERIAL,
            ItemAttributes.NONE,
            Tier.O,
            "&6Rune der Macht",
            "&7Stufe &6I"),

    END_CRYSTAL(
            Material.END_CRYSTAL,
            ItemCategory.MATERIAL,
            ItemAttributes.NONE,
            Tier.O,
            "&bEnd Crystal",
            ""),

    NETHER_STAR(
            Material.NETHER_STAR,
            ItemCategory.MATERIAL,
            ItemAttributes.NONE,
            Tier.O,
            "&eNether Star",
            "das ist ein test string ich hoffe das das funktioniert so wie es soll aber ich habe ehrlich gesagt keine ahnung ob ja oder nein lolz"),

    FRAGMENT_OF_REALITY(
            Material.ECHO_SHARD,
            ItemCategory.MATERIAL,
            ItemAttributes.NONE,
            Tier.II,
            "&5Fragment der Realität",
            "&7Stufe &6II" );

    private final Material material;
    private final String name;
    private final String lore;
    private final Tier tier;
    private final int damage;
    private final int intelligence;
    private final int strength;
    private final int def;
    private final int health;
    private final int critChance;
    private final int critDamage;
    private final ItemClass itemClass;
    private final ItemCategory itemCategory;

    CustomItem ( Material material, ItemCategory itemCategory, ItemAttributes attributes, Tier tier, String name, String lore )
    {
        this.material = material;
        this.name = name;
        this.lore = lore;
        this.tier = tier;
        this.damage = attributes.getDamage();
        this.intelligence = attributes.getIntelligence();
        this.strength = attributes.getStrength();
        this.def = attributes.getDefense();
        this.health = attributes.getHealth();
        this.critChance = attributes.getCritChance();
        this.critDamage = attributes.getCritDamage();
        this.itemClass = attributes.getItemClass();
        this.itemCategory = itemCategory;
    }

    public Material getMaterial()
    {
        return material;
    }
    public String getName() { return name; }
    public String getLore() { return lore; }
    public Tier getTier() { return tier; }
    public int getDamage()
    {
        return damage;
    }
    public int getIntelligence() { return intelligence; }
    public int getStrength() { return strength; }
    public int getDefense() { return def; }
    public int getHealth() { return health; }
    public int getCritDamage()
    {
        return critDamage;
    }
    public int getCritChance()
    {
        return critChance;
    }
    public ItemClass getItemClass() { return itemClass; }
    public ItemCategory getItemCategory() { return itemCategory; }
}
