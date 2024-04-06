package xyz.sakubami.infinitum.functionality.items;

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
