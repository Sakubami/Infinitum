package xyz.sakubami.infinitum.rpg.world.functionality.items.components;

public enum ItemClass
{
    GENERIC( "Alle Klassen"),
    MAGE("Magier"),
    BERSERK("Berserker"),
    ARCHER("Schütze"),
    TANK("Verteidiger"),
    HEALER("Heiler");

    private final String translation;

    ItemClass( String translation )
    {
        this.translation = translation;
    }

    public String getTranslation() { return translation; }
}
