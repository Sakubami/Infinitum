package xyz.sakubami.infinitum.items;

public enum Tier {
    O( "§6" ),
    I( "§9" ),
    II( "§3" ),
    III( "§b" ),
    IV( "§d" ),
    V( "§5" );

    private final String color;

    Tier( String color )
    {
        this.color = color;
    }

    public String getColor() { return color; }
}
