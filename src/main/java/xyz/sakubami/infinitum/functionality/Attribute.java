package xyz.sakubami.infinitum.functionality;

import xyz.sakubami.infinitum.functionality.items.ItemClass;

public enum Attribute
{
    DAMAGE("Schaden" ),
    HEALTH("Vitalität" ),
    DEFENSE("Rüstung" ),
    INTELLIGENCE("Intelligenz" ),
    CRITICAl_CHANCE("Krit. Chance" ),
    CRITICAL_DAMAGE("Krit. Schaden" ),
    STRENGTH( "Stärke" );

    private final String translation;

    Attribute( String translation )
    {
        this.translation = translation;
    }

    public String getTranslation() { return translation; }
}
