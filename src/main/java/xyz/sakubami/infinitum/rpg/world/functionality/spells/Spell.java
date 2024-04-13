package xyz.sakubami.infinitum.rpg.world.functionality.spells;

public enum Spell {
    NONE( 1, 1 ),
    FIRE( 2 , 1);

    private final int additive;
    private final int multiplicative;

    Spell( int additive, int multiplicative )
    {
        this.additive = additive;
        this.multiplicative = multiplicative;
    }

    public int getAdditive() { return additive; }
    public int getMultiplicative() { return multiplicative; }

}
