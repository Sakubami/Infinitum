package xyz.sakubami.infinitum.rpg.world.functionality.items.reforging;

public enum Reforge {
    NONE( "" ),
    LEGENDARY( "Legendär" );

    private final String translation;

    Reforge( String translation )
    {
        this.translation = translation;
    }

    public String getTranslation() { return translation; }
}
