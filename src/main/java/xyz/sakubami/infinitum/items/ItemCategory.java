package xyz.sakubami.infinitum.items;

public enum ItemCategory {
    LOOT( "LOOT"),
    MATERIAL( "MATERIAL"),
    STAFF("STAB"),
    TOME("WÄLZER"),
    WAND("ZAUBERSTAB"),
    SWORD("SCHWERT"),
    BOW("BOGEN"),
    TOOL("WERKZEUG");

    private final String translation;

    ItemCategory( String translation )
    {
        this.translation = translation;
    }

    public String getTranslation() { return translation; }
}
