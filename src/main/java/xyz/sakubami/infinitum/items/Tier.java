package xyz.sakubami.infinitum.items;

public enum Tier {
    O( "§c" ),
    I( "§c" ),
    II( "§c" ),
    III( "§c" ),
    IV( "§c" ),
    V( "§c" );

    private final String color;

    Tier( String color )
    {
        this.color = color;
    }

    public String getColor() { return color; }
}
