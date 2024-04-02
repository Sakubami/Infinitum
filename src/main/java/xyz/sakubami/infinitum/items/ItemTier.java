package xyz.sakubami.infinitum.items;

public enum ItemTier {
    O( "§6" ),
    I( "§9" ),
    II( "§3" ),
    III( "§b" ),
    IV( "§d" ),
    V( "§5" );

    private final String color;

    ItemTier(String color )
    {
        this.color = color;
    }

    public String getColor() { return color; }
}