package xyz.sakubami.infinitum.functionality.items;

import org.bukkit.Material;
import xyz.sakubami.infinitum.functionality.Attribute;

import java.util.HashMap;

public enum ItemTemplates
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
            "das ist ein test string ich hoffe das das ich frage mich was passiert wenn ich einfach weiter schreibe sio lol sollte eigenltihch nicht viel passieren?? §c§lfunktioniert§7 so wie es soll §c§laber§7 ich habe ehrlich gesagt keine ahnung ob ja oder nein lolz\""),

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
    private final ItemAttributes attributes;
    private final ItemClass itemClass;
    private final ItemCategory itemCategory;

    ItemTemplates( Material material, ItemCategory itemCategory, ItemAttributes attributes, ItemTier itemTier, String name, String lore )
    {
        this.material = material;
        this.name = name;
        this.lore = lore;
        this.itemTier = itemTier;
        this.attributes = attributes;
        this.itemClass = attributes.getItemClass();
        this.itemCategory = itemCategory;
    }

    public Material getMaterial() { return material; }
    public String getName() { return name; }
    public String getLore() { return lore; }
    public ItemTier getTier() { return itemTier; }
    public HashMap<Attribute, Integer> getAttributes() { return attributes.getAttributes(); }
    public ItemClass getItemClass() { return itemClass; }
    public ItemCategory getItemCategory() { return itemCategory; }
}
