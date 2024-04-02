package xyz.sakubami.infinitum.items;

import org.bukkit.Material;

public enum CustomItem
{
    NULL(
            Material.POPPED_CHORUS_FRUIT,
            ItemCategory.MATERIAL,
            ItemAttributes.NONE,
            ItemTier.O,
            "Verfluchte Frucht",
            "&o&7Erschaffen aus einem Fehlgeschlagenen Ritual" ),

    INFINITA_SCIENTIA(
            Material.WRITABLE_BOOK,
            ItemCategory.QUEST_ITEM,
            ItemAttributes.INFINITA_SCIENTIA,
            ItemTier.II,
            "Infinita Scientia",
            "&o&7Eine starke Ausstrahlung geht von diesem Wälzer aus..." ),

    HEART_OF_THE_END(
            Material.PITCHER_PLANT,
            ItemCategory.MATERIAL,
            ItemAttributes.NONE,
            ItemTier.O,
            "Herz des Endes",
            "&o&7Es pulsiert mit &o&7Dunkler Energie..." ),

    RUNE_OF_POWER(
            Material.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE,
            ItemCategory.MATERIAL,
            ItemAttributes.NONE,
            ItemTier.O,
            "Rune der Macht",
            "&7Stufe &6I"),

    END_CRYSTAL(
            Material.END_CRYSTAL,
            ItemCategory.MATERIAL,
            ItemAttributes.NONE,
            ItemTier.O,
            "End Crystal",
            "null" ),

    NETHER_STAR(
            Material.NETHER_STAR,
            ItemCategory.MATERIAL,
            ItemAttributes.NONE,
            ItemTier.O,
            "Nether Star",
            "das ist ein test string ich hoffe das das ich frage mich was passiert wenn ich einfach weiter schreibe sio lol sollte eigenltihch nicht viel passieren?? §c§lfunktioniert§7 so wie es soll §c§laber§7 ich habe ehrlich gesagt keine ahnung ob ja oder nein lolz"),

    FRAGMENT_OF_REALITY(
            Material.ECHO_SHARD,
            ItemCategory.MATERIAL,
            ItemAttributes.NONE,
            ItemTier.II,
            "Fragment der Realität",
            "&7Stufe &6II" );

    private final Material material;
    private final String name;
    private final String lore;
    private final ItemTier itemTier;
    private final int damage;
    private final int intelligence;
    private final int strength;
    private final int def;
    private final int health;
    private final int critChance;
    private final int critDamage;
    private final ItemClass itemClass;
    private final ItemCategory itemCategory;

    CustomItem ( Material material, ItemCategory itemCategory, ItemAttributes attributes, ItemTier itemTier, String name, String lore )
    {
        this.material = material;
        this.name = name;
        this.lore = lore;
        this.itemTier = itemTier;
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
    public ItemTier getTier() { return itemTier; }
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
