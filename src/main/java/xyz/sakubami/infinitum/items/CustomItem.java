package xyz.sakubami.infinitum.items;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public enum CustomItem
{
    NULL(
            Material.POPPED_CHORUS_FRUIT,
            Tier.O,
            "&5Verfluchte Frucht",
            "&o&7Erschaffen aus einem",
            "&o&7Fehlgeschlagenen Ritual"),

    INFINITA_SCIENTIA(
            Material.WRITABLE_BOOK,
            Tier.II,
            "&bInfinita Scientia",
            "&o&7Eine starke Ausstrahlung geht",
            "&o&7von diesem Buch aus..."),

    HEART_OF_THE_END(
            Material.PITCHER_PLANT,
            Tier.O,
            "&6Herz des Endes",
            "&o&7Es pulsiert mit",
            "&o&7Dunkler Energie..." ),

    RUNE_OF_POWER(
            Material.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE,
            Tier.O,
            "&6Rune der Macht",
            "&7Stufe &6I"),

    END_CRYSTAL(
            Material.END_CRYSTAL,
            Tier.O,
            "&bEnd Crystal" ),

    NETHER_STAR(
            Material.NETHER_STAR,
            Tier.O,
            "&eNether Star" ),

    FRAGMENT_OF_REALITY(
            Material.ECHO_SHARD,
            Tier.II,
            "&5Fragment der Realität",
            "&7Stufe &6II" );

    private final Material material;
    private final String name;
    private final List<String> loreList;
    private final Tier tier;

    CustomItem ( Material material, Tier tier, String name, String... lore )
    {
        this.material = material;
        this.name = name;
        this.loreList = Arrays.asList( lore );
        this.tier = tier;
    }

    public Material getMaterial()
    {
        return material;
    }
    public String getName() { return name; }
    public List<String> getLoreList() { return loreList; }
    public Tier getTier() { return tier; }
}
