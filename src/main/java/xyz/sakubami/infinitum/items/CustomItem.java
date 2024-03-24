package xyz.sakubami.infinitum.items;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public enum CustomItem
{
    NULL(
            Material.POPPED_CHORUS_FRUIT,
            1,
            "&5Verfluchte Frucht",
            "&o&7Erschaffen aus einem",
            "&o&7Fehlgeschlagenen Ritual"),

    INFINITA_SCIENTIA(
            Material.WRITABLE_BOOK,
            2,
            "&bInfinita Scientia",
            "&o&7Eine starke Ausstrahlung geht",
            "&o&7von diesem Buch aus..."),

    HEART_OF_THE_END(
            Material.PITCHER_PLANT,
            3,
            "&6Herz des Endes",
            "&o&7Es pulsiert mit",
            "&o&7Dunkler Energie..." ),

    RUNE_OF_POWER(
            Material.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE,
            4,
            "&6Rune der Macht",
            "&7Stufe &6I"),

    END_CRYSTAL(
            Material.END_CRYSTAL,
            5,
            "&bEnd Crystal" ),

    NETHER_STAR(
            Material.NETHER_STAR,
            6,
            "&eNether Star" ),

    FRAGMENT_OF_REALITY(
            Material.ECHO_SHARD,
            7,
            "&5Fragment der Realität",
            "&7Stufe &6II" );

    private final Material material;
    private final int id;
    private final String name;
    private final List<String> loreList;

    CustomItem ( Material material, int id, String name, String... lore )
    {
        this.material = material;
        this.id = id;
        this.name = name;
        this.loreList = Arrays.asList( lore );
    }

    public Material getMaterial()
    {
        return material;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public List<String> getLoreList() { return loreList; }
}
