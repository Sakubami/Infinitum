package xyz.sakubami.infinitum.functionality;

public enum Attribute
{
    DAMAGE("Schaden" ),
    MAX_HEALTH("Maximale Vitalität"),
    HEALTH("Vitalität" ),
    DEFENSE("Rüstung" ),
    INTELLIGENCE("Intelligenz" ),
    CRITICAl_CHANCE("Krit. Chance" ),
    CRITICAL_DAMAGE("Krit. Schaden" ),
    STRENGTH( "Stärke" ),
    MULTIPLICATIVE( "" ),
    ADDITIVE( "" );

    private final String translation;

    Attribute( String translation )
    {
        this.translation = translation;
    }

    public String getTranslation() { return translation; }
}
