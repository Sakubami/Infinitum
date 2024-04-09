package xyz.sakubami.infinitum.world.functionality.items.components;

public enum ItemCategory {
    LOOT( "Loot"),
    MATERIAL( "Material"),
    TOOL("Werkzeug"),
    WEAPON( "Waffe"),
    ARMOR("Rüstung");

    private final String translation;

    ItemCategory( String translation )
    {
        this.translation = translation;
    }

    public String getTranslation() { return translation; }
}
